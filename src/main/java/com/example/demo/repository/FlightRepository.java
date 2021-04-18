package com.example.demo.repository;

import com.example.demo.model.Flight;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface FlightRepository extends CrudRepository<Flight, Long> {

    Optional<Flight> getById(long id);
}
