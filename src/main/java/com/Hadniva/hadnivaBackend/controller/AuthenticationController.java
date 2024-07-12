package com.Hadniva.hadnivaBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Hadniva.hadnivaBackend.entity.User;
import com.Hadniva.hadnivaBackend.service.AuthenticationService;

@RestController
@RequestMapping
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = authenticationService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String username, @RequestParam String password) {
        User user = authenticationService.authenticateUser(username, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).body(null);
    }
}
