package fr.iut.montreuil.stationski;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       Font.loadFont(Main.class.getResource("flash/Flash.ttf").toExternalForm(), 50);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),  1000, 800);
        stage.setTitle("JAVASKI");
        URL urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/Logo.png");
        stage.getIcons().add(new Image(String.valueOf(urlIm)));
        stage.setScene(scene);
        stage.show();
    }




    public static void main(String[] args) {
        launch();
    }
}