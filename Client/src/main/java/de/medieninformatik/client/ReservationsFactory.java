package de.medieninformatik.client;

import de.medieninformatik.common.Reservations;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class ReservationsFactory {
    private static final String HOST = "localhost";
    private static final int PORT = Registry.REGISTRY_PORT;

    private ReservationsFactory() {
    }

    static Reservations getReservations() throws MalformedURLException, NotBoundException, RemoteException {
        return (Reservations) Naming.lookup("//" + HOST + ':' + PORT + "/Reservations");
    }
}
