package de.medieninformatik.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationsApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                ReservationsApplication.class.getResource("reservations-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Theater Reservation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}