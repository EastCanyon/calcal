package com.example.demo.booking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.demo.booking.Booking;

public class BookingDTO {
    private Long bookingCode;
    private LocalDateTime bookingDate;
    private LocalDateTime cancleDate;
    private LocalDate useDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int cost;
    private int bookingState;
    private Long userCode;
    private Long facilityCode;
	private Long id;
	private Long mainCode;
    
    public BookingDTO(Booking booking) {
        this.id = booking.getId();
        this.mainCode = booking.getMainCode();
        this.useDate = booking.getUseDate().toLocalDate();
        this.startTime = booking.getStartTime().toLocalTime();
        this.endTime = booking.getEndTime().toLocalTime();
    }
    
	public Long getBookingCode() {
		return bookingCode;
	}
	public void setBookingCode(Long bookingCode) {
		this.bookingCode = bookingCode;
	}
	public LocalDateTime getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}
	public LocalDateTime getCancleDate() {
		return cancleDate;
	}
	public void setCancleDate(LocalDateTime cancleDate) {
		this.cancleDate = cancleDate;
	}
	public LocalDate getUseDate() {
		return useDate;
	}
	public void setUseDate(LocalDate useDate) {
		this.useDate = useDate;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
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
	public Long getFacilityCode() {
		return facilityCode;
	}
	public void setFacilityCode(Long facilityCode) {
		this.facilityCode = facilityCode;
	}

	public Long getMainCode() {
		return null;
	}

	public Long getId() {
		return null;
	}
    
}

