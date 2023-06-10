package fr.iut.montreuil.stationski.Controleur;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import fr.iut.montreuil.stationski.Main;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuControleur  implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void playButton() {
        try {

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

    @FXML
    private void settingsButton() {

        System.out.println("Les paramètres ont été ouverts !");
    }

    @FXML
    private void exitButton() {

        System.out.println("L'application a été fermée !");
        System.exit(0);
    }
}
