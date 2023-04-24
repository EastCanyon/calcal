package com.example.demo.convert;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
 
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, LocalDate.class, new LocalDateConverter());
    }
 
    private static class LocalDateConverter implements Converter<String, LocalDate> {
 
        @Override
        public LocalDate convert(String source) {
            try {
                return LocalDate.parse(source);
            } catch (DateTimeParseException e) {
                // 날짜 형식이 맞지 않는 경우 null 반환
                return null;
            }
        }
    }
}
