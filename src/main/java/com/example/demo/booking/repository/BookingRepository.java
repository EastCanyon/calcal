package com.example.demo.booking.repository;

import com.example.demo.booking.Booking;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUseDateOrderByStartTimeAsc(LocalDateTime useDate);

    List<Booking> findByMainCodeAndUseDateOrderByStartTimeAsc(Long mainCode, LocalDateTime useDate);

    List<Booking> findByMainCodeAndUseDateAndStartTimeBetween(Long mainCode, LocalDateTime useDate, LocalDateTime start, LocalDateTime end);

    List<Booking> findByMainCodeAndUseDateAndEndTimeBetween(Long mainCode, LocalDateTime useDate, LocalDateTime start, LocalDateTime end);

    List<Booking> findByMainCodeAndUseDateAndStartTimeBeforeAndEndTimeAfter(Long mainCode, LocalDateTime useDate, LocalDateTime start, LocalDateTime end);

	List<Booking> findByMainCodeAndUseDateBetween(Long mainCode, LocalDateTime start, LocalDateTime end);
	
	@Query("select b.startTime from Booking b where b.useDate = :useDate order by b.startTime")
    List<LocalDateTime> getAvailableTimes(@Param("useDate") LocalDate useDate);
	
    List<Booking> findByUseDate(LocalDate date);

	List<Booking> findByUseDateBetween(LocalDateTime start, LocalDateTime end);
}
