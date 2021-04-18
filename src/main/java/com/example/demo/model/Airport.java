package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Airport {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    //private Address address;z
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departureAirport")
    public List<Flight> departed_flights;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "arrivalAirport")
    public List<Flight> arrived_flights;
}
