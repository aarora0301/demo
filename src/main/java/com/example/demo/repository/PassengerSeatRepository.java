package com.example.demo.repository;

import com.example.demo.model.PassengerSeat;
import org.springframework.data.repository.CrudRepository;

public interface PassengerSeatRepository extends CrudRepository<PassengerSeat, Long> {

    PassengerSeat save(PassengerSeat passengerSeat);
}
