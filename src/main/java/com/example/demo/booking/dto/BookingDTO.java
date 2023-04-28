package com.example.demo.booking.dto;

public class BookingDTO {
    
    private Long mainCode; // 시설 고유번호
    private Long bookingCode; // 예약 번호
    private Long userCode; // 사용자 고유번호
    private String useDate; // 예약한 사용날짜
    private String startTime; // 예약한 사용시작시간
    private String endTime; // 예약한 사용종료시간
    private int bookingState; // 예약 상태
    
    public BookingDTO() {}
    
    public BookingDTO(Long mainCode, Long bookingCode, Long userCode, String useDate, String startTime, String endTime, int bookingState) {
        this.mainCode = mainCode;
        this.bookingCode = bookingCode;
        this.userCode = userCode;
        this.useDate = useDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookingState = bookingState;
    }
    
    // Getter & Setter 메소드
    public Long getMainCode() {
        return mainCode;
    }
    public void setMainCode(Long mainCode) {
        this.mainCode = mainCode;
    }
    public Long getBookingCode() {
        return bookingCode;
    }
    public void setBookingCode(Long bookingCode) {
        this.bookingCode = bookingCode;
    }
    public Long getUserCode() {
        return userCode;
    }
    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }
    public String getUseDate() {
        return useDate;
    }
    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public int getBookingState() {
        return bookingState;
    }
    public void setBookingState(int bookingState) {
        this.bookingState = bookingState;
    }
}
