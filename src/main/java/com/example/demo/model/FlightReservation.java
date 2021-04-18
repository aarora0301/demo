package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class FlightReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private FlightInstance flight;


    @OneToMany(mappedBy = "flightReservation")
    private List<PassengerSeat> passengerSeatList;

    private Date creationDate;
    private ReservationStatus status;


    public FlightReservation(Date creationDate, ReservationStatus status, FlightInstance flightInstance) {
        this.creationDate = creationDate;
        this.status = status;
        this.flight = flightInstance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FlightInstance getFlight() {
        return flight;
    }

    public void setFlight(FlightInstance flight) {
        this.flight = flight;
    }

    public List<PassengerSeat> getPassengerSeatList() {
        return passengerSeatList;
    }

    public void setPassengerSeatList(List<PassengerSeat> passengerSeatList) {
        this.passengerSeatList = passengerSeatList;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public FlightReservation() {

    }
    // public static FlightReservation fetchReservationDetails(String reservationNumber);

}
