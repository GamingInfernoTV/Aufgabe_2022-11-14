package de.medieninformatik.client;

import de.medieninformatik.common.Reservations;
import de.medieninformatik.common.Seat;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ReservationsClient implements Reservations {

    private final Reservations reservations;

    public ReservationsClient() throws MalformedURLException, NotBoundException, RemoteException {
        reservations = ReservationsFactory.getReservations();
    }

    @Override
    public boolean hasReservation(Seat seat) throws RemoteException {
        return reservations.hasReservation(seat);
    }

    @Override
    public String getReservation(Seat seat) throws RemoteException {
        return reservations.getReservation(seat);
    }

    @Override
    public void makeReservation(Seat seat, String name) throws RemoteException {
        reservations.makeReservation(seat, name);
    }
}
