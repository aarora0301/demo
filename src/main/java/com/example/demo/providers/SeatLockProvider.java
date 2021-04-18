package com.example.demo.providers;

import com.example.demo.model.FlightInstance;
import com.example.demo.model.Seat;

import java.util.List;

public interface SeatLockProvider {

    void lockSeats(FlightInstance flightInstance, List<Seat> seat, String user);
    void unlockSeats(FlightInstance flightInstance, List<Seat> seat, String user);
    boolean validateLock(FlightInstance flightInstance, Seat seat, String user);

    List<Seat> getLockedSeats(FlightInstance flightInstance);
}