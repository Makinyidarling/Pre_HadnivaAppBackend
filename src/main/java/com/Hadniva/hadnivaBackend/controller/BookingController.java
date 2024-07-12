package com.Hadniva.hadnivaBackend.controller;

import com.Hadniva.hadnivaBackend.dto.ChartDataDTO;
import com.Hadniva.hadnivaBackend.entity.Booking;
import com.Hadniva.hadnivaBackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        booking.setId(id);
        return ResponseEntity.ok(bookingService.updateBooking(booking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/chart")
    public ResponseEntity<List<ChartDataDTO>> getBookingsForChart() {
        List<ChartDataDTO> chartData = bookingService.getBookingsForChart();
        return ResponseEntity.ok(chartData);
    }
}
