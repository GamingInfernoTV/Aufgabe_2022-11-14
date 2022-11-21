package de.medieninformatik.client;

import de.medieninformatik.common.Reservations;
import de.medieninformatik.common.Seat;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Implementierung von {@link Reservations}, welche auf ein unterliegendes Objekt, das
 * durch {@link ReservationsFactory#getReservations()} erstellt wurde, aufbaut
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
public class ReservationsClient implements Reservations {

    private final Reservations reservations;

    /**
     * Konstruktor; speichert das von {@link ReservationsFactory#getReservations()} erstellte Objekt
     *
     * @throws MalformedURLException Wenn von der Factory diese Exception geworfen wurde
     * @throws NotBoundException     Wenn von der Factory diese Exception geworfen wurde
     * @throws RemoteException       Wenn von der Factory diese Exception geworfen wurde
     */
    public ReservationsClient() throws MalformedURLException, NotBoundException, RemoteException {
        reservations = ReservationsFactory.getReservations();
    }

    /**
     * Gibt die Anzahl an Sitz-Reihen zurück
     *
     * @return Die Anzahl an Sitz-Reihen
     * @throws RemoteException Wenn bei der {@link Remote}-Verbindung ein Fehler auftritt
     */
    @Override
    public int getNumberOfSeatRows() throws RemoteException {
        return reservations.getNumberOfSeatRows();
    }

    /**
     * Gibt die Anzahl an Sitze per Reihe zurück
     *
     * @return Die Anzahl an Sitze per Reihe
     * @throws RemoteException Wenn bei der {@link Remote}-Verbindung ein Fehler auftritt
     */
    @Override
    public int getNumbersOfSeatsPerRow() throws RemoteException {
        return reservations.getNumbersOfSeatsPerRow();
    }

    /**
     * Überprüft, ob ein ausgewählter {@link Seat} schon reserviert wurde
     *
     * @param seat Der zu prüfende Sitz
     * @return true, wenn der Sitz schon reserviert wurde, sonst false
     * @throws RemoteException Wenn bei der {@link Remote}-Verbindung ein Fehler auftritt
     */
    @Override
    public boolean hasReservation(Seat seat) throws RemoteException {
        return reservations.hasReservation(seat);
    }

    /**
     * Gibt den Namen, auf den ein Sitz reserviert wurde, zurück
     *
     * @param seat Der Sitz, dessen Reservierung betrachtet werden soll
     * @return Der Name, auf welchem der Sitz reserviert wurde
     * @throws RemoteException Wenn bei der {@link Remote}-Verbindung ein Fehler auftritt
     */
    @Override
    public String getReservation(Seat seat) throws RemoteException {
        return reservations.getReservation(seat);
    }

    /**
     * Reserviert einen gewählten {@link Seat} auf einen bestimmten Namen
     *
     * @param seat Der Sitz, welcher reserviert werden soll
     * @param name Der Name, auf welchen der Sitz reserviert werden soll
     * @throws RemoteException Wenn bei der {@link Remote}-Verbindung ein Fehler auftritt
     */
    @Override
    public void makeReservation(Seat seat, String name) throws RemoteException {
        reservations.makeReservation(seat, name);
    }
}
