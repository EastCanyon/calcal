package com.example.demo.booking;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.*;

import com.example.demo.booking.service.BookingState;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "booking_code")
    private Long bookingCode;

    @Column(name = "main_code")
    private Long mainCode;

    @Column(name = "user_code")
    private Long userCode;

    @Column(name = "use_date")
    private String useDate;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;
    
    @Column(name = "time")
    private LocalTime time;

    @Column(name = "booking_state", columnDefinition = "integer default 0")
    private Integer bookingState;
    
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }
    
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;

    public void setCancelDate(LocalDateTime cancelDate) {
        this.cancelDate = cancelDate;
    }
    
    public Booking(Long mainCode, Long userCode, LocalDateTime startDateTime, LocalDateTime endDateTime, BookingState bookingState) {
        this.mainCode = mainCode;
        this.userCode = userCode;
        this.useDate = startDateTime.toLocalDate().toString();
        this.startTime = startDateTime.toLocalTime().toString();
        this.endTime = endDateTime.toLocalTime().toString();
        this.bookingState = bookingState.getLabel();
    }

    public Booking(Long mainCode, Long userCode, String useDate, String startTime, String endTime, Integer bookingState) {
        this.mainCode = mainCode;
        this.userCode = userCode;
        this.useDate = useDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookingState = bookingState;
    }

    public Booking(Long facilityCode, Long userCode, LocalDateTime startDateTime, LocalDateTime endDateTime, int bookingState) {
        this.mainCode = facilityCode;
        this.userCode = userCode;
        this.useDate = startDateTime.toLocalDate().toString();
        this.startTime = startDateTime.toLocalTime().toString();
        this.endTime = endDateTime.toLocalTime().toString();
        this.bookingState = bookingState;
    }

}
