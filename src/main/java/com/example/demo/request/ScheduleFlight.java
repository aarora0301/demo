package com.example.demo.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;


public class ScheduleFlight {

    public long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @NonNull
    private long flightNumber;

    @NonNull
    private String departureTime;

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    @NonNull
    private int noOfSeats;
}
