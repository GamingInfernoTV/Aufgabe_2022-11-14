package de.medieninformatik.server;

import de.medieninformatik.common.Reservations;
import de.medieninformatik.common.Seat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ReservationsImpl implements Reservations {
    private static final Map<Seat, String> RESERVATIONS_MAP = Collections.synchronizedMap(new HashMap<>());

    @Override
    public boolean hasReservation(Seat seat) {
        return RESERVATIONS_MAP.containsKey(
                Objects.requireNonNull(seat));
    }

    @Override
    public String getReservation(Seat seat) {
        return RESERVATIONS_MAP.get(
                Objects.requireNonNull(seat));
    }

    @Override
    public void makeReservation(Seat seat, String name) {
        System.out.println("Requesting reservation of seat " + seat + " on name: " + name);
        if (null != RESERVATIONS_MAP.putIfAbsent(
                Objects.requireNonNull(seat),
                Objects.requireNonNull(name)))
            throw new IllegalStateException("Seat already has a reservation");
    }
}
