package com.example.demo.booking.service;

import com.example.demo.booking.Booking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface bookingMapper {
    List<Booking> selectByMainCodeAndUseDate(@Param("mainCode") Long mainCode, @Param("useDate") LocalDate useDate);
}

