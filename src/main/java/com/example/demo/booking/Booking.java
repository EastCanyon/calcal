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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "main_code")
    private Long mainCode;

    @Column(name = "use_date")
    private LocalDateTime useDate;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    public Booking(BookingDTO bookingDTO) {
        this.id = bookingDTO.getId();
        this.mainCode = bookingDTO.getMainCode();
        this.useDate = LocalDateTime.of(bookingDTO.getUseDate(), bookingDTO.getStartTime());
        this.startTime = LocalDateTime.of(bookingDTO.getUseDate(), bookingDTO.getStartTime());
        this.endTime = LocalDateTime.of(bookingDTO.getUseDate(), bookingDTO.getEndTime());
    }

    public List<LocalTime> getAvailableTimes() {
        List<LocalTime> availableTimes = new ArrayList<>();

        // Implement logic to get available times for this booking
        // ...

        return availableTimes;
    }
}
