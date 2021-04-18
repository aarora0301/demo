package com.example.demo.providers;

import com.example.demo.exception.SeatTemporaryUnavailableException;
import com.example.demo.model.FlightInstance;
import com.example.demo.model.Seat;
import com.example.demo.model.SeatLock;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemorySeatLockProvider implements SeatLockProvider {

    private final Integer lockTimeout; // Bonus feature.
    private final Map<FlightInstance, Map<Seat, SeatLock>> locks;

    public InMemorySeatLockProvider(@NonNull final Integer lockTimeout) {
        this.locks = new HashMap<>();
        this.lockTimeout = lockTimeout;
    }


    public InMemorySeatLockProvider(){
        this.locks = new HashMap<>();
        this.lockTimeout = 1;
    }
    @Override
    public void lockSeats(FlightInstance flightInstance, List<Seat> seats, String user) {
        for (Seat seat : seats) {
            if (isSeatLocked(flightInstance, seat)) {
                throw new SeatTemporaryUnavailableException();
            }
        }

        for (Seat seat : seats) {
            lockSeat(flightInstance, seat, user, lockTimeout);
        }


    }

    @Override
    public void unlockSeats(FlightInstance flightInstance, List<Seat> seats, String user) {
        for (Seat seat: seats) {
            if (validateLock(flightInstance, seat, user)) {
                unlockSeat(flightInstance, seat);
            }
        }
    }

    @Override
    public boolean validateLock(FlightInstance flightInstance, Seat seat, String user) {
        return isSeatLocked(flightInstance, seat) && locks.get(flightInstance).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(FlightInstance flightInstance) {
        if (!locks.containsKey(flightInstance)) {
            return new ArrayList<>();
        }
        final List<Seat> lockedSeats = new ArrayList<>();

        for (Seat seat : locks.get(flightInstance).keySet()) {
            if (isSeatLocked(flightInstance, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private boolean isSeatLocked(final FlightInstance flightInstance, final Seat seat) {
        return locks.containsKey(flightInstance) && locks.get(flightInstance).containsKey(seat) && !locks.get(flightInstance).get(seat).isLockExpired();
    }

    private void lockSeat(final FlightInstance flightInstance, final Seat seat, final String user, final Integer timeoutInSeconds) {
        if (!locks.containsKey(flightInstance)) {
            locks.put(flightInstance, new HashMap<>());
        }
        final SeatLock lock = new SeatLock(seat, flightInstance, timeoutInSeconds, new Date(), user);
        locks.get(flightInstance).put(seat, lock);
    }

    private void unlockSeat(final FlightInstance flightInstance, final Seat seat) {
        if (!locks.containsKey(flightInstance)) {
            return;
        }
        locks.get(flightInstance).remove(seat);
    }
}
