package com.Hadniva.hadnivaBackend.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Hadniva.hadnivaBackend.entity.User;
import com.Hadniva.hadnivaBackend.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AuthenticationService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new UsernameNotFoundException("Invalid password");
        }
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        switch (registrationId) {
            case "google":
                return handleGoogleUser(oAuth2User);
            case "linkedin":
                return handleLinkedInUser(oAuth2User);
            case "apple":
                return handleAppleUser(oAuth2User);
            default:
                throw new OAuth2AuthenticationException("Unsupported OAuth2 provider");
        }
    }

    private OAuth2User handleGoogleUser(OAuth2User oAuth2User) {
        User user = userRepository.findByEmail(oAuth2User.getAttribute("email"))
                .orElseGet(() -> registerNewUser(oAuth2User));

        user.setProfilePictureUrl(oAuth2User.getAttribute("picture"));
        return new DefaultOAuth2User(user.getAuthorities(), oAuth2User.getAttributes(), "email");
    }

    private OAuth2User handleLinkedInUser(OAuth2User oAuth2User) {
        User user = userRepository.findByEmail(oAuth2User.getAttribute("email"))
                .orElseGet(() -> registerNewUser(oAuth2User));

        return new DefaultOAuth2User(user.getAuthorities(), oAuth2User.getAttributes(), "email");
    }

    private OAuth2User handleAppleUser(OAuth2User oAuth2User) {
        User user = userRepository.findByEmail(oAuth2User.getAttribute("email"))
                .orElseGet(() -> registerNewUser(oAuth2User));

        user.setProfilePictureUrl("default_profile_picture_url");
        user.setName(oAuth2User.getAttribute("name"));
        user.setEmail(oAuth2User.getAttribute("email"));
        return new DefaultOAuth2User(user.getAuthorities(), oAuth2User.getAttributes(), "email");
    }

    private User registerNewUser(OAuth2User oAuth2User) {
        User user = new User();
        user.setEmail(oAuth2User.getAttribute("email"));
        user.setName(oAuth2User.getAttribute("name"));
        user.setPassword(passwordEncoder.encode("oauth2user"));
        user.setAttributes(oAuth2User.getAttributes());

        return userRepository.save(user);
    }

    public User updateProfilePicture(Long userId, MultipartFile file) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Path directory = Paths.get("profile-pictures");
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
        Path filePath = directory.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath);

        user.setProfilePictureUrl(filePath.toString());
        return userRepository.save(user);
    }
}