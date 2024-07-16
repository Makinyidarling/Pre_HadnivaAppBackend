package com.Hadniva.hadnivaBackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/register","/email/","/auth**","/register", "/auth/login", "/public/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated())
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/auth/login"))
            .logout(logout -> logout
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/auth/login?logout")
                .permitAll())
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(googleClientRegistration(), linkedinClientRegistration(), appleClientRegistration());
    }

    @Bean
    public InMemoryOAuth2AuthorizedClientService authorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
            .clientId("YOUR_GOOGLE_CLIENT_ID")
            .clientSecret("YOUR_GOOGLE_CLIENT_SECRET")
            .scope("profile", "email")
            .authorizationUri("https://accounts.google.com/o/oauth2/auth")
            .tokenUri("https://oauth2.googleapis.com/token")
            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
            .redirectUri("{baseUrl}/login/oauth2/code/google")
            .authorizationGrantType(org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE)
            .clientName("Google")
            .build();
    }

    private ClientRegistration linkedinClientRegistration() {
        return ClientRegistration.withRegistrationId("linkedin")
            .clientId("YOUR_LINKEDIN_CLIENT_ID")
            .clientSecret("YOUR_LINKEDIN_CLIENT_SECRET")
            .scope("r_liteprofile", "r_emailaddress")
            .authorizationUri("https://www.linkedin.com/oauth/v2/authorization")
            .tokenUri("https://www.linkedin.com/oauth/v2/accessToken")
            .userInfoUri("https://api.linkedin.com/v2/me")
            .redirectUri("{baseUrl}/login/oauth2/code/linkedin")
            .authorizationGrantType(org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE)
            .clientName("LinkedIn")
            .build();
    }

    private ClientRegistration appleClientRegistration() {
        return ClientRegistration.withRegistrationId("apple")
            .clientId("YOUR_APPLE_CLIENT_ID")
            .clientSecret("YOUR_APPLE_CLIENT_SECRET")
            .scope("name", "email")
            .authorizationUri("https://appleid.apple.com/auth/authorize")
            .tokenUri("https://appleid.apple.com/auth/token")
            .userInfoUri("https://appleid.apple.com/auth/userinfo")
            .redirectUri("{baseUrl}/login/oauth2/code/apple")
            .authorizationGrantType(org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE)
            .clientName("Apple")
            .build();
    }
}
