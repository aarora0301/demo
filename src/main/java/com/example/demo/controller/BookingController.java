package com.example.demo.controller;


import com.example.demo.exception.NotFoundException;
import com.example.demo.model.PassengerSeat;
import com.example.demo.request.BookSeatRequest;
import com.example.demo.response.BookingResponse;
import com.example.demo.service.FlightReservationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    FlightReservationService flightReservationService;

    @PostMapping(path = "/bookSeat", consumes = "application/json", produces = "application/json")
    public BookingResponse createBooking(@RequestBody BookSeatRequest bookSeatRequest) {


        BookingResponse bookingResponse;
        PassengerSeat passengerSeat = null;
        try {

            passengerSeat = flightReservationService.createBooking(bookSeatRequest.getUserName(), bookSeatRequest.getFlightNumber());
        } catch (NotFoundException ex) {
            bookingResponse = new BookingResponse("Failure", "Tickets Full", 0);
            return bookingResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            bookingResponse = new BookingResponse("Failure", "Something Unexpected happened", 0);
            return bookingResponse;
        }
        bookingResponse = new BookingResponse("Success", "", passengerSeat.getFlightSeat().getId());
        return bookingResponse;

    }
}
