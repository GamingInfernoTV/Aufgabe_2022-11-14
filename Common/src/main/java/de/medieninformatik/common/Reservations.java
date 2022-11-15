package de.medieninformatik.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface zum Reservieren und Überprüfen von Reservierungen
 * über eine {@link Remote}-Verbindung
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
public interface Reservations extends Remote {

    /**
     * Überprüft, ob ein ausgewählter {@link Seat} schon reserviert wurde
     *
     * @param seat Der zu prüfende Sitz
     * @return true, wenn der Sitz schon reserviert wurde, sonst false
     * @throws RemoteException Wenn bei der {@link Remote}-Verbindung ein Fehler auftritt
     */
    boolean hasReservation(Seat seat) throws RemoteException;

    /**
     * Gibt den Namen, auf den ein Sitz reserviert wurde, zurück
     *
     * @param seat Der Sitz, dessen Reservierung betrachtet werden soll
     * @return Der Name, auf welchem der Sitz reserviert wurde
     * @throws RemoteException Wenn bei der {@link Remote}-Verbindung ein Fehler auftritt
     */
    String getReservation(Seat seat) throws RemoteException;

    /**
     * Reserviert einen gewählten {@link Seat} auf einen bestimmten Namen
     *
     * @param seat Der Sitz, welcher reserviert werden soll
     * @param name Der Name, auf welchen der Sitz reserviert werden soll
     * @throws RemoteException Wenn bei der {@link Remote}-Verbindung ein Fehler auftritt
     */
    void makeReservation(Seat seat, String name) throws RemoteException;
}
