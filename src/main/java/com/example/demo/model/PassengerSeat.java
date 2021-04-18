package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PassengerSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Seat getFlightSeat() {
        return flightSeat;
    }

    public void setFlightSeat(Seat flightSeat) {
        this.flightSeat = flightSeat;
    }

    public FlightReservation getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservation flightReservation) {
        this.flightReservation = flightReservation;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    private Passenger passenger;
    @OneToOne
    private Seat flightSeat;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private FlightReservation flightReservation;
}
