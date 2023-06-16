package fr.iut.montreuil.stationski;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JeuApplication {

    public static void launchGame() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),  1000, 800);
            Stage stage = new Stage();
            stage.setTitle("StationSki");
            stage.setScene(scene);
            stage.show();


            // Autres opérations de configuration du jeu


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
