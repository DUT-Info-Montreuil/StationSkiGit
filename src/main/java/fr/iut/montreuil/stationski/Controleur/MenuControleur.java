package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Modele.Ambiance.SoundGame;
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
        SoundGame.playSoundEffect(3);
        SoundGame.loop();
    }

    @FXML
    private void playButton() {

        SoundGame.playSoundEffect(1);
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game_menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),  1000, 800);
             stage.setTitle("MapMenu");
             stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    @FXML
    private void settingsButton() {

       SoundGame.playSoundEffect(1);
        System.out.println("Les paramètres ont été ouverts !");
    }

    @FXML
    private void exitButton() {
        SoundGame.playSoundEffect(1);
        System.out.println("L'application a été fermée !");
        System.exit(0);

    }



}
