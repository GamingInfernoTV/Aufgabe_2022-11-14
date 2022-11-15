package de.medieninformatik.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Reservations extends Remote {
    boolean hasReservation(Seat seat) throws RemoteException;
    String getReservation(Seat seat) throws RemoteException;
    void makeReservation(Seat seat, String name) throws RemoteException;
}
