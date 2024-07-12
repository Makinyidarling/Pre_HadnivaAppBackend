package com.Hadniva.hadnivaBackend.service;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.Hadniva.hadnivaBackend.entity.User;
import com.Hadniva.hadnivaBackend.repository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // Extract user information from OAuth2 attributes
        String email = (String) attributes.get("email");

        // Find or create user
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> new User(email, (String) attributes.get("name")));

        // Update user details from OAuth2 attributes
        user.setProfilePictureUrl((String) attributes.get("picture"));

        userRepository.save(user);

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                attributes,
                userNameAttributeName);
    }
}
