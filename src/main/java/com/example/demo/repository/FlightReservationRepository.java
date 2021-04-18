package com.example.demo.repository;

import com.example.demo.model.Flight;
import com.example.demo.model.FlightReservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FlightReservationRepository extends CrudRepository<FlightReservation, Long> {

    FlightReservation save(FlightReservation flightReservation);
    Optional<FlightReservation> getFlightReservationById(long id);

}
