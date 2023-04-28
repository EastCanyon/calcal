package com.example.demo.booking.repository;

import com.example.demo.booking.Booking;
import com.example.demo.booking.dto.BookingDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	Booking findByMainCodeAndDateAndTime(Long mainCode, LocalDateTime date, LocalTime time);

	    @Query("SELECT b.startTime FROM Booking b WHERE b.mainCode = :mainCode AND b.date = :date AND ((b.startTime BETWEEN :startTime AND :endTime) OR (b.endTime BETWEEN :startTime AND :endTime))")
	    List<LocalTime> findAvailableStartTimes(@Param("mainCode") Long mainCode, @Param("date") LocalDate date, @Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime);

	    @Query("SELECT b.endTime FROM Booking b WHERE b.mainCode = :mainCode AND b.date = :date AND ((b.startTime BETWEEN :startTime AND :endTime) OR (b.endTime BETWEEN :startTime AND :endTime))")
	    List<LocalTime> findAvailableEndTimes(@Param("mainCode") Long mainCode, @Param("date") LocalDate date, @Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime);

	
    // 모든 예약 데이터 조회
    List<Booking> findAll();

    // 특정 날짜에 해당하는 모든 예약 데이터 조회
    List<Booking> findByDate(LocalDate date);

    // 특정 날짜와 시간 범위에 해당하는 예약 데이터 조회
    List<Booking> findByDateAndTimeBetween(LocalDate date, LocalTime startTime, LocalTime endTime);

    // 특정 main_code, 날짜, 시간 범위에 해당하는 예약 데이터 조회
    List<BookingDTO> findByMainCodeAndUseDateAndTimeBetween(Long mainCode, LocalDateTime useDate, LocalTime startTime, LocalTime endTime);


    // 특정 main_code, 날짜, 시간에 해당하는 예약 데이터 조회
    Booking findByMainCodeAndDateTime(Long mainCode, LocalDateTime dateTime, LocalTime time);

    // 예약 가능한 시간대 조회
    @Query("SELECT b.startTime FROM Booking b WHERE b.useDate = ?1 AND (b.endTime <= ?2 OR b.startTime >= ?3) ORDER BY b.startTime")
    List<LocalDateTime> findAvailableDateTimes(LocalDate date, LocalTime startTime, LocalTime endTime);

    // 특정 기간 내에 예약된 모든 데이터 조회
    List<Booking> findByUseDateBetween(LocalDateTime start, LocalDateTime end);

    // 특정 main_code, 날짜, 시간 범위에 해당하는 예약 데이터 조회
    List<BookingDTO> findByMainCodeAndUseDateAndTimeBetween(Long mainCode, LocalDate useDate, LocalTime startTime, LocalTime endTime);


    // 특정 main_code, 날짜, 시간에 해당하는 예약 데이터 조회
    Booking findByMainCodeAndStartTime(Long mainCode, LocalDateTime dateTime);

	List<Booking> findByDateRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

	List<Booking> findByMainCodeAndDateRange(Long mainCode, LocalDateTime startDateTime, LocalDateTime endDateTime);

	List<LocalDateTime> findAvailableDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
