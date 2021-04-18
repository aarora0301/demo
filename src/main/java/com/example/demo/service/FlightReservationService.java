package com.example.demo.service;


import com.example.demo.exception.NotEnoughSeats;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.*;
import com.example.demo.providers.InMemorySeatLockProvider;
import com.example.demo.repository.FlightInstanceRepository;
import com.example.demo.repository.FlightReservationRepository;
import com.example.demo.repository.PassengerSeatRepository;
import com.example.demo.repository.SeatRepository;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@NoArgsConstructor
public class FlightReservationService {

    @Autowired
    InMemorySeatLockProvider inMemorySeatLockProvider;

    @Autowired
    FlightInstanceRepository flightInstanceRepository;


    @Autowired
    FlightReservationRepository flightReservationRepository;

    @Autowired
    PassengerSeatRepository passengerSeatRepository;


    @Autowired
    SeatRepository seatRepository;


    public PassengerSeat createBooking(@NonNull String user, @NonNull long id) throws NotFoundException {

        Optional<FlightInstance> flightInstance = flightInstanceRepository.getById(id);
        if (!flightInstance.isPresent()) {
            throw new NotFoundException("No Such flight exists");
        }
        FlightInstance tempFlightInstance = flightInstance.get();
        List<Seat> seats = tempFlightInstance.getAircraft().getSeats().stream().filter(Seat::isAvailable).collect(Collectors.toList());
        if (seats.size() < 1) {
            throw new NotEnoughSeats();
        }

        List<PassengerSeat> passengerSeatList = new ArrayList<>();
        List<Seat> lockedSeats = new ArrayList<>();

        PassengerSeat passengerSeat = new PassengerSeat();
        Passenger passenger = new Passenger(user);
        Seat finalSeat = seats.get(0);
        finalSeat.setAvailable(false);
        lockedSeats.add(finalSeat);
        passengerSeat.setPassenger(passenger);
        passengerSeat.setFlightSeat(finalSeat);
        passengerSeatList.add(passengerSeat);

        inMemorySeatLockProvider.lockSeats(tempFlightInstance, lockedSeats, user);
        FlightReservation flightReservation = new FlightReservation();
        flightReservation.setPassengerSeatList(passengerSeatList);
        flightReservation = flightReservationRepository.save(flightReservation);
        passengerSeat.setFlightReservation(flightReservation);
        passengerSeatRepository.save(passengerSeat);
        inMemorySeatLockProvider.unlockSeats(tempFlightInstance, lockedSeats, user);
        seatRepository.save(finalSeat);
        return passengerSeat;
    }


}
