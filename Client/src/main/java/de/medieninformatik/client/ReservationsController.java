package de.medieninformatik.client;

import de.medieninformatik.common.Reservations;
import de.medieninformatik.common.Seat;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Controller-Klasse für {@link ReservationsApplication}
 *
 * @author Malte Kasolowsky <code>m30114</code>
 * @author Aaron Pöhlmann <code>m30115</code>
 */
public class ReservationsController {
    private final Reservations reservations;
    private final HashMap<Button, Seat> buttonSeatMap = new HashMap<>();
    public Polygon polygon;
    public StackPane stackPane; //Stack, welcher das Polygon und den Text der Bühne beinhaltet
    public Text buehne;

    /**
     * Prüft, ob der {@link Seat Sitz}, welche mit dem {@link Button} verlinkt ist, reserviert ist,
     * und ändert die Hintergrundfarbe des Buttons entsprechend
     *
     * @param button Der Button des Hintergrundfarbe entsprechend der Reservierung aktualisiert werden soll
     */
    private void updateButtonStyle(Button button) {
        try {
            button.setStyle("-fx-background-color: " +
                    (reservations.hasReservation(buttonSeatMap.get(button))
                            ? "orangered"
                            : "aquamarine"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public GridPane gridPane;

    /**
     * Konstruktor; öffnet eine Verbindung über RMI mittels {@link ReservationsClient}
     * und initialisiert die UI
     */
    public ReservationsController() throws MalformedURLException, NotBoundException, RemoteException {
        reservations = new ReservationsClient();
        Platform.runLater(this::init);
    }

    /**
     * Füllt die {@link GridPane} der Oberfläche mit {@link Button Buttons},
     * durch welche die {@link Seat Sitze} des Theaters reserviert werden können
     */
    public void init() {
        buehne.fillProperty().set(Color.WHITE);
        buehne.setStyle("-fx-font: 30px arial");
        final int gap = 10;
        final int seatRows;
        final int seatsPerRow;
        try {
            seatRows = reservations.getNumberOfSeatRows();
            seatsPerRow = reservations.getNumbersOfSeatsPerRow();
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }
        final double width = (gridPane.getWidth() - (seatsPerRow - 1) * gap) / seatsPerRow;
        final double height = (gridPane.getHeight() - (seatRows - 1) * gap) / seatRows;

        for (int i = 0; i < seatRows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                final Seat seat = new Seat(i, j);
                final Button button = new Button(
                        "Seat %02d.%02d".formatted(seat.row() + 1, seat.number() + 1));
                button.setPrefSize(width, height);
                button.setOnAction(this::reservationPrompt);
                button.setCursor(Cursor.HAND);
                gridPane.add(button, j, i);
                buttonSeatMap.put(button, seat);
                updateButtonStyle(button);
            }
        }
    }

    /**
     * Öffnet ein {@link TextInputDialog} zum Reservieren eines
     * {@link Seat Seats} auf einen entsprechenden Namen und zeigt entsprechende Warnungen an,
     * falls der Sitz bereits reserviert oder kein Name angegeben wurde
     *
     * @param event Das {@link Event}, welches den Prompt ausgelöst hat; muss durch einen Button geschehen
     */
    private void reservationPrompt(Event event) {
        if (!(event.getSource() instanceof Button button))
            throw new IllegalArgumentException("event is not of type button");

        final Seat seat = buttonSeatMap.get(button);
        final String dialogTitle = "Reservation";

        try {
            if (reservations.hasReservation(seat)) {
                Alert alert = new Alert(
                        Alert.AlertType.INFORMATION, null, ButtonType.OK);
                alert.setTitle(dialogTitle);
                alert.setHeaderText("Seat already has a reservation on the name '"
                        + reservations.getReservation(seat) + '\'');
                alert.showAndWait();
            } else {
                TextInputDialog inputDialog = new TextInputDialog();
                inputDialog.setTitle(dialogTitle);
                inputDialog.setHeaderText(
                        "Enter name for reservation of seat %02d.%02d:"
                                .formatted(seat.row() + 1, seat.number() + 1));
                var input = inputDialog.showAndWait();
                if (input.isPresent()) {
                    if (input.get().isBlank()) {
                        Alert alert = new Alert(
                                Alert.AlertType.WARNING, null, ButtonType.OK);
                        alert.setTitle(dialogTitle);
                        alert.setHeaderText("Name must not be empty!\nNo reservation has been made.");
                        alert.showAndWait();
                    } else reservations.makeReservation(seat, input.get());
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        updateButtonStyle(button);
    }
}