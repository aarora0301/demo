package com.example.demo.controller;


import com.example.demo.exception.FlightAlreadyScheduled;
import com.example.demo.model.Aircraft;
import com.example.demo.response.GenericResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.request.ScheduleFlight;
import com.example.demo.response.SeatStatusResponse;
import com.example.demo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FlightController {


    @Autowired
    FlightService flightService;


    @PostMapping(path = "/scheduleFlight", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericResponse> schedule(@RequestBody ScheduleFlight scheduleFlight) {

        GenericResponse genericResponse;
        try {
            flightService.schedule(scheduleFlight.getFlightNumber(), scheduleFlight.getDepartureTime(), scheduleFlight.getNoOfSeats());
        } catch (NotFoundException ex) {
            genericResponse = new GenericResponse("Failed", "Check Input Format");
            return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
        }catch (FlightAlreadyScheduled ex){
            genericResponse = new GenericResponse("Failed", "Flight Already Scheduled");
            return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
        }

        catch (Exception ex) {
            ex.printStackTrace();
            genericResponse = new GenericResponse("Failed", "Something Unexpected happened");
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }

        genericResponse = new GenericResponse("Success");
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }


    @GetMapping("/getAvailableSeats")
    public ResponseEntity<SeatStatusResponse> getAircraftById(@RequestParam("flightNumber") long id) {

        long seatCount = 0;
        SeatStatusResponse seatStatusResponse;

        try {
            seatCount = flightService.getAvailableSeats(id);
        } catch (NotFoundException ex) {
            seatStatusResponse = new SeatStatusResponse("Failed", "", 0);
            return new ResponseEntity<>(seatStatusResponse, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            seatStatusResponse = new SeatStatusResponse("Failed", "Something Unexpected happened", 0);
            return new ResponseEntity<>(seatStatusResponse, HttpStatus.NOT_FOUND);
        }

        seatStatusResponse = new SeatStatusResponse("Success", "", seatCount);
        return new ResponseEntity<>(seatStatusResponse, HttpStatus.OK);
    }
}
