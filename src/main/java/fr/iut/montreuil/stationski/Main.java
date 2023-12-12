package fr.iut.montreuil.stationski;


import fr.iut.montreuil.stationski.Controleur.Connect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
   /* @Override
    public void start(Stage stage) throws IOException {
       Font.loadFont(Main.class.getResource("flash/Flash.ttf").toExternalForm(), 50);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),  1000, 800);
        stage.setTitle("StationSki");
        stage.setScene(scene);
        stage.show();
        new Connect();
    }*/

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("grille.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("MapMenu");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}