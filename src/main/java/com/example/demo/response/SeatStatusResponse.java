package com.example.demo.response;

public class SeatStatusResponse  extends GenericResponse{

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    private long count;

    public SeatStatusResponse(String status, String message, long count){
     super(status, message);
     this.count= count;
    }
}
