package de.medieninformatik.server;

import de.medieninformatik.common.Reservations;
import de.medieninformatik.common.Seat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Implementiert die serverseitige Verbindung von {@link Reservations},
 * welche in einer statischen {@link Map} die reservierten {@link Seat Seats} speichert
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
public class ReservationsImpl implements Reservations {
    private static final Map<Seat, String> RESERVATIONS_MAP = Collections.synchronizedMap(new HashMap<>());

    /**
     * Überprüft, ob ein ausgewählter {@link Seat} schon reserviert wurde
     *
     * @param seat Der zu prüfende Sitz
     * @return true, wenn der Sitz schon reserviert wurde, sonst false
     */
    @Override
    public boolean hasReservation(Seat seat) {
        return RESERVATIONS_MAP.containsKey(
                Objects.requireNonNull(seat));
    }

    /**
     * Gibt den Namen, auf den ein Sitz reserviert wurde, zurück
     *
     * @param seat Der Sitz, dessen Reservierung betrachtet werden soll
     * @return Der Name, auf welchem der Sitz reserviert wurde
     */
    @Override
    public String getReservation(Seat seat) {
        String name = RESERVATIONS_MAP.get(
                Objects.requireNonNull(seat));
        if (name == null) throw new IllegalStateException("no reservation for seat found");
        return name;
    }

    /**
     * Reserviert einen gewählten {@link Seat} auf einen bestimmten Namen
     *
     * @param seat Der Sitz, welcher reserviert werden soll
     * @param name Der Name, auf welchen der Sitz reserviert werden soll
     */
    @Override
    public void makeReservation(Seat seat, String name) {
        if (null != RESERVATIONS_MAP.putIfAbsent(
                Objects.requireNonNull(seat),
                Objects.requireNonNull(name)))
            throw new IllegalStateException("seat already has a reservation");
        else System.out.println("Made reservation of seat " + seat + " on name '" + name + '\'');
    }
}
