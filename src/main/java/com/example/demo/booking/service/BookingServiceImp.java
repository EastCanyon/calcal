package com.example.demo.booking.service;

import com.example.demo.booking.Booking;
import com.example.demo.booking.dto.BookingDTO;
import com.example.demo.booking.repository.BookingRepository;

import mapper.BookingMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImp implements BookingService {
    private final BookingRepository bookingRepository;

    private final SqlSessionFactory sqlSessionFactory;

    @Autowired
    public BookingServiceImp(BookingRepository bookingRepository, SqlSessionFactory sqlSessionFactory) {
        this.bookingRepository = bookingRepository;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public BookingDTO getBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(BookingDTO::new).orElse(null);
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking(bookingDTO);
        Booking savedBooking = bookingRepository.save(booking);
        return new BookingDTO(savedBooking);
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setMainCode(bookingDTO.getMainCode());
            booking.setUseDate(LocalDateTime.of(bookingDTO.getUseDate(), bookingDTO.getStartTime()));
            booking.setStartTime(LocalDateTime.of(bookingDTO.getUseDate(), bookingDTO.getStartTime()));
            booking.setEndTime(LocalDateTime.of(bookingDTO.getUseDate(), bookingDTO.getEndTime()));
            Booking savedBooking = bookingRepository.save(booking);
            return new BookingDTO(savedBooking);
        }
        return null;
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findByMainCodeAndUseDate(Long mainCode, LocalDateTime useDate) {
        return bookingRepository.findByMainCodeAndUseDateOrderByStartTimeAsc(mainCode, useDate);
    }

    @Override
    public List<Booking> findByMainCodeAndUseDateBetween(Long mainCode, LocalDateTime start, LocalDateTime end) {
        return bookingRepository.findByMainCodeAndUseDateBetween(mainCode, start, end);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(BookingDTO::new).collect(Collectors.toList());
    }
    
    public List<String> getAvailableTimes(Long mainCode, LocalDateTime useDate) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BookingMapper bookingMapper = sqlSession.getMapper(BookingMapper.class);
            List<Booking> bookings = bookingMapper.selectByMainCodeAndUseDate(mainCode, useDate.toLocalDate());
            Set<String> bookedTimes = bookings.stream().map(booking -> booking.getUseDate().toLocalTime().toString().substring(0, 5)).collect(Collectors.toSet());
            List<String> availableTimes = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                String time = String.format("%02d", i) + ":00";
                if (!bookedTimes.contains(time)) {
                    availableTimes.add(time);
                }
            }
            return availableTimes;
        }
    }

	@Override
	public List<BookingDTO> getBookings(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}
    }
