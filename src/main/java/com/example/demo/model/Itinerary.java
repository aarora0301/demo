package com.example.demo.model;

import java.util.Date;
import java.util.List;

public class Itinerary {
    private String customerId;
    private Airport startingAirport;
    private Airport finalAirport;
    private Date creationDate;
    private List<FlightReservation> reservations;

   // public List<FlightReservation> getReservations();
    //public boolean makeReservation();

}
