package com.Hadniva.hadnivaBackend.service;

import com.Hadniva.hadnivaBackend.dto.ChartDataDTO;
import com.Hadniva.hadnivaBackend.entity.Booking;
import com.Hadniva.hadnivaBackend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    // Method to get bookings for chart
    public List<ChartDataDTO> getBookingsForChart() {
        return bookingRepository.findAll().stream()
                .map(booking -> new ChartDataDTO(
                        booking.getUser().getName(),
                        booking.getService().getName(),
                        booking.getDate(),
                        booking.getPrice()
                ))
                .collect(Collectors.toList());
    }
}
