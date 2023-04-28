package com.example.demo.booking.service;

import com.example.demo.booking.Booking;
import com.example.demo.booking.dto.BookingDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface BookingService {
    BookingDTO getBooking(Long id);
    BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO updateBooking(Long id, BookingDTO bookingDTO);
    void deleteBooking(Long id);	
    // 모든 예약 데이터 조회
    List<Booking> getAllBookings();
    
    // 특정 날짜에 해당하는 모든 예약 데이터 조회
    List<BookingDTO> getBookingsByDate(LocalDate date);

    // 특정 날짜와 시간 범위에 해당하는 예약 데이터 조회
    List<BookingDTO> getBookingsByDateAndTimeRange(LocalDate date, LocalTime startTime, LocalTime endTime);

    // 특정 main_code, 날짜, 시간 범위에 해당하는 예약 데이터 조회
    List<Booking> getBookingsByMainCodeAndDateAndTimeRange(Long mainCode, LocalDateTime date, LocalTime startTime, LocalTime endTime);

    // 특정 main_code, 날짜, 시간에 해당하는 예약 데이터 조회
    Booking getBookingByMainCodeAndDateAndTime(Long mainCode, LocalDateTime date, LocalTime time);

    // 예약 가능한 시간대 조회
    List<LocalDateTime> getAvailableTimes(LocalDate date, LocalTime startTime, LocalTime endTime);

	List<BookingDTO> findByMainCodeAndUseDateAndTimeBetween(Long mainCode, LocalDate useDate, LocalTime startTime,
			LocalTime endTime);
}

