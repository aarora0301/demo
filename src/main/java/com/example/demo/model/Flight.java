package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airportFrom", referencedColumnName = "id")
    private Airport departureAirport;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airportTo", referencedColumnName = "id")
    private Airport arrivalAirport;

    private int durationInMinutes;


    public Flight() {
    }

    public Flight(int flightNumber, Airport departure,
                  Airport arrival, int durationInMinutes) {
        this.id = flightNumber;
        this.departureAirport = departure;
        this.arrivalAirport = arrival;
        this.durationInMinutes = durationInMinutes;

    }

    //    private List<WeeklySchedule> weeklySchedules;
//    private List<CustomSchedule> customSchedules;
    @OneToMany(mappedBy = "flight")
    private List<FlightInstance> flightInstances;
}
