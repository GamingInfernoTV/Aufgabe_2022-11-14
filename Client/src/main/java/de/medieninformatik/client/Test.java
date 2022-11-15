package de.medieninformatik.client;

import de.medieninformatik.common.Reservations;
import de.medieninformatik.common.Seat;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Test {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        Reservations r = new ReservationsClient();
        r.makeReservation(new Seat(1, 2), "Werner");
        r.makeReservation(new Seat(2, 2), "Werner");
        r.makeReservation(new Seat(2, 2), "Werner");
    }
}
