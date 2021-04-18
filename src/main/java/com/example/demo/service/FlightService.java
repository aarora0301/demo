package com.example.demo.service;

import com.example.demo.exception.FlightAlreadyScheduled;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Aircraft;
import com.example.demo.model.Flight;
import com.example.demo.model.FlightInstance;
import com.example.demo.model.Seat;
import com.example.demo.providers.InMemorySeatLockProvider;
import com.example.demo.repository.AircraftRepository;
import com.example.demo.repository.FlightInstanceRepository;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.SeatRepository;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@NoArgsConstructor
public class FlightService {

    @Autowired
    FlightInstanceRepository flightInstanceRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    InMemorySeatLockProvider inMemorySeatLockProvider;

    @Autowired
    AircraftRepository aircraftRepository;


    public FlightInstance schedule(@NonNull final long flightNumber, @NonNull final String date, @NonNull final int noOfSeats) throws NotFoundException, ParseException {
        Date departureTime = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").parse(date);

        Optional<Flight> flight = flightRepository.getById(flightNumber);
        if (!flight.isPresent()) {
            throw new NotFoundException("No such Flight exists");
        }

        Optional<FlightInstance> flightInstanceOptional = flightInstanceRepository.getByFlight(flight.get());

        if (flightInstanceOptional.isPresent()) {
            throw new FlightAlreadyScheduled();
        }
        FlightInstance flightInstance = new FlightInstance(departureTime, flight.get());
        Optional<Aircraft> aircraft = aircraftRepository.findById(1);
        flightInstance.setAircraft(aircraft.get());
        for (int i = 1; i <= noOfSeats; i++) {
            Seat seat = new Seat();
            seat.setId(i);
            seat.setAvailable(true);
            seat.setAircraft(aircraft.get());
            seatRepository.save(seat);
        }
        return flightInstanceRepository.save(flightInstance);
    }

    public long getAvailableSeats(@NonNull final long flightNumber) {

        Optional<FlightInstance> flightInstance = flightInstanceRepository.getById(flightNumber);
        if (!flightInstance.isPresent()) {
            throw new NotFoundException("No Such flight exists");
        }
        return (flightInstance.get().getAircraft().getSeats().stream().filter(seat -> seat.isAvailable()).count() - inMemorySeatLockProvider.getLockedSeats(flightInstance.get()).size());
    }
}
