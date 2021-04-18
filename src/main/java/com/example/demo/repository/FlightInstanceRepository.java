package com.example.demo.repository;


import com.example.demo.model.Flight;
import com.example.demo.model.FlightInstance;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FlightInstanceRepository extends CrudRepository<FlightInstance, Long> {

    FlightInstance save(FlightInstance flightInstance);
    Optional<FlightInstance> getByFlight(Flight flight);
    Optional<FlightInstance> getById(long id);
}
