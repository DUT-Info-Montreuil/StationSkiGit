package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Vue.GameMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GameMenuControleur implements Initializable {

    @FXML
    private Pane window;
    @FXML
    private ImageView map1;
    @FXML
    private ImageView map2;
    @FXML
    private ImageView map3;
    @FXML
    private ImageView mapaleatoire;
    private int Map;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.map1.setOnMouseClicked(e -> { this.launchGame();
            this.Map = 1;
        window.getScene().getWindow().hide();});

        this.map2.setOnMouseClicked(e -> { this.launchGame();
            this.Map = 2;
            window.getScene().getWindow().hide();});
        this.map3.setOnMouseClicked(e -> { this.launchGame();
            this.Map = 3;
            window.getScene().getWindow().hide();});

        //this.mapaleatoire.setOnMouseClicked(e -> { this.launchGame();
       //    this.Map = 0;
         //   window.getScene().getWindow().hide();});
    }



    public void launchGame() {
        //Image selectedImage = imageView.getImage();
        // Lancer le jeu avec l'image sélectionnée
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

    public int getMap(){return Map;}


}
