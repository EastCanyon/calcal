package com.example.demo.booking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.booking.Booking;

public class BookingDTO {
    private Long mainCode;
    private LocalDate useDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int cost;
    private int bookingState;
    private Long userCode;
	private Object availableTimes;
    
    public static BookingDTO toBookingDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setMainCode(booking.getMainCode());
        dto.setUserCode(booking.getUserCode());
        dto.setUseDate(booking.getUseDate().toString());
        dto.setStartTime(booking.getStartTime());
        dto.setEndTime(booking.getEndTime());
        dto.setAvailableTimes(booking.getAvailableTimes());
        return dto;
    }

    public BookingDTO() {}
    public BookingDTO(Booking booking) {
        this.mainCode = booking.getMainCode();
        this.useDate = booking.getUseDate().toLocalDate();
        this.startTime = booking.getStartTime();
        this.endTime = booking.getEndTime();
    }

    public Long getMainCode() {
        return mainCode;
    }

    public void setMainCode(Long mainCode) {
        this.mainCode = mainCode;
    }

    public LocalDate getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = LocalDate.parse(useDate);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getBookingState() {
        return bookingState;
    }

    public void setBookingState(int bookingState) {
        this.bookingState = bookingState;
    }

    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }
    
    public void setAvailableTimes(List<LocalTime> availableTimes) {
        this.availableTimes = availableTimes.stream()
                                             .map(t -> t.format(DateTimeFormatter.ofPattern("HH:mm")))
                                             .collect(Collectors.toList());
    }

}
