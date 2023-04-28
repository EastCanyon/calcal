package com.example.demo.booking.service;

import com.example.demo.booking.Booking;
import com.example.demo.booking.dto.BookingDTO;
import com.example.demo.booking.repository.BookingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    
    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        // bookingDTO에서 booking으로 필드 값을 복사
        BeanUtils.copyProperties(bookingDTO, booking);
        // bookingRepository를 사용하여 booking을 저장
        booking = bookingRepository.save(booking);
        // 저장된 booking을 다시 bookingDTO로 변환하여 반환
        BookingDTO createdBookingDTO = new BookingDTO();
        BeanUtils.copyProperties(booking, createdBookingDTO);
        return createdBookingDTO;
    }
    
    @Override
    public List<BookingDTO> findByMainCodeAndUseDateAndTimeBetween(Long mainCode, LocalDate useDate, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(useDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(useDate, endTime);
        return bookingRepository.findByMainCodeAndUseDateAndTimeBetween(mainCode, useDate, startTime, endTime);
    }
    
    @Override
    public BookingDTO getBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));
        BookingDTO bookingDTO = new BookingDTO();
        BeanUtils.copyProperties(booking, bookingDTO);
        return bookingDTO;
    }
    
    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));
        BeanUtils.copyProperties(bookingDTO, booking);
        booking.setId(id);
        booking = bookingRepository.save(booking);
        BookingDTO updatedBookingDTO = new BookingDTO();
        BeanUtils.copyProperties(booking, updatedBookingDTO);
        return updatedBookingDTO;
    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));
        bookingRepository.delete(booking);
    }
    
    
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    private List<Booking> getBookingsByDateTimeRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return bookingRepository.findByDateRange(startDateTime, endDateTime);
    }

    @Override
    public List<BookingDTO> getBookingsByDate(LocalDate date) {
        List<Booking> bookings = bookingRepository.findByDate(date);
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = new BookingDTO();
            BeanUtils.copyProperties(booking, bookingDTO);
            bookingDTOs.add(bookingDTO);
        }
        return bookingDTOs;
    }
    public List<BookingDTO> getBookingsByDateAndTimeRange(LocalDate date, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(date, endTime);
        List<Booking> bookings = getBookingsByDateTimeRange(startDateTime, endDateTime);
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = new BookingDTO();
            BeanUtils.copyProperties(booking, bookingDTO);
            bookingDTOs.add(bookingDTO);
        }
        return bookingDTOs;
    }


    @Override
    public List<Booking> getBookingsByMainCodeAndDateAndTimeRange(Long mainCode, LocalDateTime date, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(date.toLocalDate(), startTime);
        LocalDateTime endDateTime = LocalDateTime.of(date.toLocalDate(), endTime);
        return bookingRepository.findByMainCodeAndDateRange(mainCode, startDateTime, endDateTime);
    }

    @Override
    public Booking getBookingByMainCodeAndDateAndTime(Long mainCode, LocalDateTime date, LocalTime time) {
        LocalDateTime dateTime = LocalDateTime.of(date.toLocalDate(), time);
        return bookingRepository.findByMainCodeAndDateTime(mainCode, dateTime, time);
    }

    @Override
    public List<LocalDateTime> getAvailableTimes(LocalDate date, LocalTime startTime, LocalTime endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(date, endTime);
        return bookingRepository.findAvailableDateTimes(startDateTime, endDateTime);
    }
}
