package com.example.demo.booking.service;

import com.example.demo.booking.Booking;
import com.example.demo.booking.dto.BookingDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    List<Booking> findByMainCodeAndUseDate(Long mainCode, LocalDateTime useDate);
    List<Booking> findByMainCodeAndUseDateBetween(Long mainCode, LocalDateTime start, LocalDateTime end);
    BookingDTO getBooking(Long id);
    BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO updateBooking(Long id, BookingDTO bookingDTO);
    void deleteBooking(Long id);
	List<BookingDTO> getAllBookings();
	List<String> getAvailableTimes(Long mainCode, LocalDateTime useDate);
	
}
