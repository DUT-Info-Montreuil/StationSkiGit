package fr.iut.montreuil.stationski.Vue;


import fr.iut.montreuil.stationski.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

public class GameMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(Main.class.getResource("flash/Flash.ttf").toExternalForm(), 50);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game_menu.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("MAP");
        stage.setScene(scene);
        URL urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/Logo.png");
        stage.getIcons().add(new Image(String.valueOf(urlIm)));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}