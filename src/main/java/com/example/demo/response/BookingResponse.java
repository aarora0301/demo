package com.example.demo.response;

public class BookingResponse extends GenericResponse {
    public long getSeatId() {
        return seatId;
    }

    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }

    public BookingResponse(String status, String message, long seatId) {
        super(status, message);
        this.seatId = seatId;

    }

    private long seatId;

}
