package com.example.demo.booking.service;

public enum BookingState {
    BOOKED(1),
    CANCELLED(2);

    private final int value;

    BookingState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BookingState valueOf(int value) {
        for (BookingState state : BookingState.values()) {
            if (state.value == value) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid BookingState value: " + value);
    }

	public Integer getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
}


