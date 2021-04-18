package com.example.demo.repository;

import com.example.demo.model.Aircraft;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AircraftRepository extends  CrudRepository<Aircraft, Long> {
    Optional<Aircraft> findById(long id);
}
