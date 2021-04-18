package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
public class FlightInstance {

    public FlightInstance() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date departureTime;
    private String gate;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    FlightStatus status;

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @OneToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    public boolean cancel() {
        if (status == FlightStatus.SCHEDULED) {
            status = FlightStatus.CANCELLED;
            return true;
        }
        return false;
    }

    public FlightInstance(Date departureTime, Flight flight) {
        this.departureTime = departureTime;
        this.flight = flight;
        this.status = FlightStatus.SCHEDULED;

    }

    public void updateStatus(FlightStatus status) {
        this.status = status;
    }
}
