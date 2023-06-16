package fr.iut.montreuil.stationski.Vue;


import fr.iut.montreuil.stationski.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(Main.class.getResource("flash/Flash.ttf").toExternalForm(), 50);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game_menu.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Game Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}