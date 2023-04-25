package com.example.demo.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.example.demo.booking.dto.BookingDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "main_code")
    private Long mainCode;
    
    @Column(name = "user_code")
    private Long userCode;

    @Column(name = "use_date")
    private LocalDateTime useDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    public Booking(BookingDTO bookingDTO) {
        this.mainCode = bookingDTO.getMainCode();
        this.useDate = LocalDateTime.of(bookingDTO.getUseDate(), bookingDTO.getStartTime());
        this.startTime = bookingDTO.getStartTime();
        this.endTime = bookingDTO.getEndTime();
    }

    public List<String> getAvailableTimes() {
        List<LocalTime> availableTimes = new ArrayList<>();

        // Implement logic to get available times for this booking
        // ...

        return availableTimes.stream().map(LocalTime::toString).collect(Collectors.toList());
    }
}
