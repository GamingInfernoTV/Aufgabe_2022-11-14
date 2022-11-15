package de.medieninformatik.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Implementiert eine {@link Application}
 * zum Reservieren von Sitzen in einem Theater
 *
 * @author Malte Kasolowsky <code>m30114</code>
 * @author Aaron Pöhlmann <code>m30115</code>
 */
public class ReservationsApplication extends Application {

    /**
     * Startet die Application
     *
     * @param args nicht benutzt
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Überschreit die {@link Application#start(Stage)}-Methode der Application
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws IOException Wenn ein Fehler beim Laden der FXL-Datei auftritt
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                ReservationsApplication.class.getResource("reservations-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Theater Reservation");
        stage.setScene(scene);
        stage.show();
    }
}