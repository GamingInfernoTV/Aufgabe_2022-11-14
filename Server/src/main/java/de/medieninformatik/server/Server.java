package de.medieninformatik.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Server-Klasse; setzt einen Server mittels RMI zur Anfrage von
 * {@link de.medieninformatik.common.Reservations}-Objekten
 *
 * @author Malte Kasolowsk <code>m30114</code>
 */
public class Server {
    private static final int PORT = Registry.REGISTRY_PORT;

    /**
     * Private Konstruktor, da keine Instanziierung vonnöten ist
     */
    private Server() {
    }

    /**
     * Statische Methode zum Starten des Servers am {@link Registry#REGISTRY_PORT},
     * welcher eine Instanz von {@link ReservationsImpl} an eingehende Verbindungen sendet
     */
    static void start() {
        try {
            System.out.println("Starting Server...");
            LocateRegistry.createRegistry(PORT);
            Remote remoteObject = new ReservationsImpl();
            UnicastRemoteObject.exportObject(remoteObject, 50000);
            Naming.rebind("//:" + PORT + "/Reservations", remoteObject);
            System.out.println("Server started at port: " + PORT);
        } catch (MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Startet den Server
     *
     * @param args Nicht benutzt
     */
    public static void main(String[] args) {
        Server.start();
    }
}
