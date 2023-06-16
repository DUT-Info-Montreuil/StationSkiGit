package fr.iut.montreuil.stationski;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class JeuApplication {

    public static void launchGame() {

        try {
            Font.loadFont(Main.class.getResource("victoire/Victoire.ttf").toExternalForm(), 50);
            Font.loadFont(Main.class.getResource("frozbite/FROZBITE.ttf").toExternalForm(), 50);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),  1000, 800);
            Stage stage = new Stage();
            stage.setTitle("JAVASKI");
            URL urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/Logo.png");
            stage.getIcons().add(new Image(String.valueOf(urlIm)));
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
