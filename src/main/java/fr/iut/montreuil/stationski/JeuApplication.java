package fr.iut.montreuil.stationski;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class JeuApplication {

    public static void launchGame() {

        try {
            Font.loadFont(Main.class.getResource("frozbite/FROZBITE.ttf").toExternalForm(), 50);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),  1000, 800);
            Stage stage = new Stage();
            stage.setTitle("StationSki");
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
