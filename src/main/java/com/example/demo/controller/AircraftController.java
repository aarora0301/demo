package com.example.demo.controller;


import com.example.demo.model.Aircraft;
import com.example.demo.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AircraftController {

    @Autowired
    AircraftRepository aircraftRepository;

    @GetMapping("/aircraft/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable("id") long id) {
        Optional<Aircraft> aircraftData = aircraftRepository.findById(id);
        return aircraftData.map(aircraft -> new ResponseEntity<>(aircraft, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
