package de.medieninformatik.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private Server() {
    }
    private static final int PORT = Registry.REGISTRY_PORT;
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
}
