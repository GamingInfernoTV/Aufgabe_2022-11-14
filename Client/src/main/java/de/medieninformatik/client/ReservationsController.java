package de.medieninformatik.client;

import de.medieninformatik.common.Reservations;
import de.medieninformatik.common.Seat;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Optional;

/**
 * Controller-Klasse für {@link ReservationsApplication}
 *
 * @author Malte Kasolowsky <code>m30114</code>
 * @author Aaron Pöhlmann <code>m30115</code>
 */
public class ReservationsController {
    private final Reservations reservations;

    @FXML
    private GridPane grid;

    /**
     * Konstruktor; öffnet eine Verbindung über RMI mittels {@link ReservationsClient}
     * und initialisiert die UI
     */
    public ReservationsController() throws MalformedURLException, NotBoundException, RemoteException {
        reservations = new ReservationsClient();
        Platform.runLater(this::init);
    }

    /**
     * // TODO: 15.11.2022
     */
    private void init() {
        for (int i = 0; i < grid.getRowCount(); i++) {
            for (int j = 0; j < grid.getColumnCount(); j++) {
                Seat seat = new Seat(i, j);
                Rectangle rect = new Rectangle(
                        grid.getWidth() / grid.getRowCount() - 10,
                        grid.getHeight() / grid.getColumnCount() - 10);
                rect.fillProperty().set(Color.BLUE);
                rect.setOnMouseClicked(mouseEvent -> reservationPrompt(seat));
                grid.add(rect, i, j);
            }
        }
    }

    /**
     * Öffnet ein {@link TextInputDialog} zum Reservieren eines
     * {@link Seat Seats} auf einen entsprechenden Namen und zeigt entsprechende Warnungen an,
     * falls der Sitz bereits reserviert oder kein Name angegeben wurde
     *
     * @param seat Der Sitz, der reserviert werden soll
     */
    private void reservationPrompt(Seat seat) {
        final String dialogTitle = "Reservation";
        try {
            if (reservations.hasReservation(seat)) {
                Alert alert = new Alert(
                        Alert.AlertType.INFORMATION,
                        "Seat already has a reservation: " + reservations.getReservation(seat),
                        ButtonType.OK);
                alert.setTitle(dialogTitle);
                alert.setHeaderText("Seat already has a reservation");
                alert.showAndWait();
            } else {
                TextInputDialog t = new TextInputDialog();
                t.setTitle(dialogTitle);
                t.setHeaderText("Enter reservation name:");
                Optional<String> input = t.showAndWait();
                if (input.isPresent()) {
                    if (input.get().isBlank()) {
                        Alert alert = new Alert(
                                Alert.AlertType.WARNING,
                                "No name was entered!\nNo reservation has been made.",
                                ButtonType.OK);
                        alert.setTitle(dialogTitle);
                        alert.setHeaderText("Invalid input");
                        alert.showAndWait();
                    } else reservations.makeReservation(seat, input.get());
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}