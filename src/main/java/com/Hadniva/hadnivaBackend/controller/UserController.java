package com.Hadniva.hadnivaBackend.controller;

import java.io.IOException;
import java.util.List;

import com.Hadniva.hadnivaBackend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hadniva.hadnivaBackend.dto.ChartDataDTO;
import com.Hadniva.hadnivaBackend.entity.Booking;
import com.Hadniva.hadnivaBackend.entity.User;
import com.Hadniva.hadnivaBackend.service.BookingService;
import com.Hadniva.hadnivaBackend.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final BookingService bookingService;

    public UserController(AuthenticationService authenticationService, UserService userService, BookingService bookingService) {
        super();
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.bookingService = bookingService;
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

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.loadUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/chart")
    public ResponseEntity<List<ChartDataDTO>> getBookingsForChart() {
        List<ChartDataDTO> chartData = bookingService.getBookingsForChart();
        return ResponseEntity.ok(chartData);
    }

    @GetMapping("/{userId}/bookings")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/{userId}/profile-picture")
    public ResponseEntity<User> updateProfilePicture(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        try {
            User user = userService.updateProfilePicture(userId, file);
            return ResponseEntity.ok(user);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}