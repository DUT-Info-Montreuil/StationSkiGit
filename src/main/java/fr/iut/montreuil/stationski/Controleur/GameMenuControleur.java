package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.ChoixMap;
import fr.iut.montreuil.stationski.JeuApplication;
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
    private ImageView map0;
    @FXML
    private ImageView map1;
    @FXML
    private ImageView map2;
    @FXML
    private ImageView map3;
    @FXML
    private ImageView mapaleatoire;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JeuApplication jeu = new JeuApplication();

        this.map0.setOnMouseClicked(e -> {
            ChoixMap.setChoixMap(0);
            JeuApplication.launchGame();
            window.getScene().getWindow().hide();});

        this.map1.setOnMouseClicked(e -> {
            ChoixMap.setChoixMap(1);
            JeuApplication.launchGame();

        window.getScene().getWindow().hide();});

        this.map2.setOnMouseClicked(e -> {

            ChoixMap.setChoixMap(2);
            JeuApplication.launchGame();
            window.getScene().getWindow().hide();});

        this.map3.setOnMouseClicked(e -> {
            ChoixMap.setChoixMap(3);
            JeuApplication.launchGame();


            window.getScene().getWindow().hide();});


        this.mapaleatoire.setOnMouseClicked(e -> {
            ChoixMap.setChoixMap(4);
            JeuApplication.launchGame();


            window.getScene().getWindow().hide();});

    }


}
