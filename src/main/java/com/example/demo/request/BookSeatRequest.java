package com.example.demo.request;

import lombok.NonNull;

public class BookSeatRequest {

    public long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NonNull
    private long flightNumber;
    @NonNull
    private String userName;
}
