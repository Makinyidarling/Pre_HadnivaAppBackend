package com.Hadniva.hadnivaBackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.stereotype.Component;

@Component
public class OAuth2ClientRegistrationLogger {

    @Autowired
    public OAuth2ClientRegistrationLogger(ClientRegistrationRepository clientRegistrationRepository) {
        if (clientRegistrationRepository instanceof InMemoryClientRegistrationRepository) {
            InMemoryClientRegistrationRepository inMemoryRepo = (InMemoryClientRegistrationRepository) clientRegistrationRepository;
            inMemoryRepo.iterator().forEachRemaining(registration -> {
                System.out.println("Client Registration: " + registration.getClientName());
            });
        }
    }
}
