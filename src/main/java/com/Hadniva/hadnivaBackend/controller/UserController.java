package com.Hadniva.hadnivaBackend.controller;

import java.io.IOException;
import java.util.List;

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

    private final UserService userService;
    private final BookingService bookingService;

    public UserController(UserService userService, BookingService bookingService) {
        super();
        this.userService = userService;
        this.bookingService = bookingService;
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