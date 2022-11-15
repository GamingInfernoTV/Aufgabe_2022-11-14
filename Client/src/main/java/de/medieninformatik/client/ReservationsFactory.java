package de.medieninformatik.client;

import de.medieninformatik.common.Reservations;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 * Factory-Klasse zum Erstellen von Objekten von {@link Reservations},
 * welche von einem Server geholt werden
 *
 * @author Malte Kasolowsky <code>m30114</code>
 */
public class ReservationsFactory {
    private static final String HOST = "localhost";
    private static final int PORT = Registry.REGISTRY_PORT;

    /**
     * Privater Konstruktor, da eine Instanziierung nicht vonnöten ist
     */
    private ReservationsFactory() {
    }

    /**
     * Stellt eine Verbindung mittels RMI zu einem Server her
     * und holt eine Instanz von {@link Reservations}
     *
     * @return Eine Instanz von Reservation
     * @throws MalformedURLException Wenn die URL der Anfrage ein invalides Format hat
     * @throws NotBoundException     Wenn die Anfrage auf dem Server nicht gefunden werden konnte
     * @throws RemoteException       Wenn keine Verbindung aufgebaut werden konnte
     */
    static Reservations getReservations() throws MalformedURLException, NotBoundException, RemoteException {
        return (Reservations) Naming.lookup("//" + HOST + ':' + PORT + "/Reservations");
    }
}
