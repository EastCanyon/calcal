package com.example.demo.booking.controller;

import com.example.demo.booking.Booking;
import com.example.demo.booking.dto.BookingDTO;
import com.example.demo.booking.repository.BookingRepository;
import com.example.demo.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // 예약 생성 API
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    // 예약 조회 API
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable Long bookingId) {
        BookingDTO bookingDTO = bookingService.getBooking(bookingId);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    // 예약 수정 API
    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long bookingId, @RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBooking = bookingService.updateBooking(bookingId, bookingDTO);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }

    // 예약 삭제 API
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 날짜별 예약 조회 API
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getBookingsByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<BookingDTO> bookings = bookingService.getBookingsByDate(date);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // 시간대별 예약 조회 API
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getBookingsByDateAndTimeRange(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                                          @RequestParam("startTime") @DateTimeFormat(pattern = "HH:mm") LocalTime startTime,
                                                                          @RequestParam("endTime") @DateTimeFormat(pattern = "HH:mm") LocalTime endTime) {
        List<BookingDTO> bookings = bookingService.getBookingsByDateAndTimeRange(date, startTime, endTime);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}


