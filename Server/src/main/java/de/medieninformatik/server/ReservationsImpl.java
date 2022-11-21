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
    private static final int NUMBER_OF_SEAT_ROWS = 6;
    private static final int NUMBER_OF_SEATS_PER_ROW = 10;
    private static final Map<Seat, String> RESERVATIONS_MAP = Collections.synchronizedMap(new HashMap<>());

    /**
     * Testet, ob ein {@link Seat} innerhalb des gesetzten Bereiches der Sitz-Anzahl liegt
     *
     * @param seat Der zu testende Seat
     * @return Den übergebenen Sitz
     * @throws IllegalArgumentException Wenn der Seat außerhalb des definierten Bereiches liegt
     */
    private static Seat test(Seat seat) {
        if (seat.row() >= NUMBER_OF_SEAT_ROWS || seat.number() >= NUMBER_OF_SEATS_PER_ROW)
            throw new IllegalArgumentException("no reservation can be made for seat " + seat);
        return seat;
    }

    /**
     * Überprüft, ob ein ausgewählter {@link Seat} schon reserviert wurde
     *
     * @param seat Der zu prüfende Sitz
     * @return true, wenn der Sitz schon reserviert wurde, sonst false
     */
    @Override
    public boolean hasReservation(Seat seat) {
        return RESERVATIONS_MAP.containsKey(
                test(Objects.requireNonNull(seat)));
    }

    /**
     * Gibt den Namen, auf den ein Sitz reserviert wurde, zurück
     *
     * @param seat Der Sitz, dessen Reservierung betrachtet werden soll
     * @return Der Name, auf welchem der Sitz reserviert wurde
     * @throws IllegalStateException Wenn der Sitz noch nicht reserviert wurde
     */
    @Override
    public String getReservation(Seat seat) {
        String name = RESERVATIONS_MAP.get(
                test(Objects.requireNonNull(seat)));
        if (name == null) throw new IllegalStateException("no reservation for seat found");
        return name;
    }

    /**
     * Reserviert einen gewählten {@link Seat} auf einen bestimmten Namen
     *
     * @param seat Der Sitz, welcher reserviert werden soll
     * @param name Der Name, auf welchen der Sitz reserviert werden soll
     * @throws IllegalStateException Wenn der Sitz bereits reserviert wurde
     */
    @Override
    public void makeReservation(Seat seat, String name) {
        // try putting the passed seat and name into the map
        // the result will be null, if the seat was already put into the map
        if (RESERVATIONS_MAP.putIfAbsent(
                test(Objects.requireNonNull(seat)),
                Objects.requireNonNull(name)) != null)
            throw new IllegalStateException("seat already has a reservation");
        else System.out.println("Made reservation of seat " + seat + " on name '" + name + '\'');
    }

    /**
     * Gibt die Anzahl an Sitz-Reihen zurück
     *
     * @return Die Anzahl an Sitz-Reihen
     */
    @Override
    public int getNumberOfSeatRows() {
        return NUMBER_OF_SEAT_ROWS;
    }

    /**
     * Gibt die Anzahl an Sitze per Reihe zurück
     *
     * @return Die Anzahl an Sitze per Reihe
     */
    @Override
    public int getNumbersOfSeatsPerRow() {
        return NUMBER_OF_SEATS_PER_ROW;
    }
}
