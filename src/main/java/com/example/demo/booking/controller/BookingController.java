package com.example.demo.booking.controller;

import com.example.demo.booking.Booking;
import com.example.demo.booking.dto.BookingDTO;
import com.example.demo.booking.repository.BookingRepository;
import com.example.demo.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable Long id) {
        BookingDTO booking = bookingService.getBooking(id);
        return ResponseEntity.ok(booking);
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok(createdBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBooking = bookingService.updateBooking(id, bookingDTO);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getBookings(@RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                         @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<BookingDTO> bookings;
        if (startDate != null && endDate != null) {
            bookings = bookingService.getBookings(startDate, endDate);
        } else if (startDate != null) {
            bookings = bookingService.getBookings(startDate, endDate);
        } else {
            bookings = bookingService.getBookings(endDate, endDate);
        }
        return ResponseEntity.ok(bookings);
    }


    @PostMapping("/date")
    @ResponseBody
    public String handleDateRequest(@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model) {
        // 날짜 처리 작업 수행
        String result = "This is the result of date processing for " + date.toString();

        // 처리 결과를 모델에 저장하고 view 이름을 반환
        model.addAttribute("result", result);
        return "viewName";
    }
    //예약 데이터를 조회하려고 하는 날짜의 00:00:00부터 23:59:59까지 조회
    @GetMapping("/by-date")
    @ResponseBody
    public List<Booking> getBookingsByDate(@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date != null) {
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);
            return bookingRepository.findByUseDateBetween(start, end);
        } else {
            return bookingRepository.findAll();
        }
    }


}

