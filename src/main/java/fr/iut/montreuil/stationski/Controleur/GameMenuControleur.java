package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.ChoixMap;
import fr.iut.montreuil.stationski.JeuApplication;
import fr.iut.montreuil.stationski.Modele.Ambiance.SoundGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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


        this.map0.setOnMouseClicked(e -> {
            chooseNLaunch(0);});

        this.map1.setOnMouseClicked(e -> {
            chooseNLaunch(1);});

        this.map2.setOnMouseClicked(e -> {
            chooseNLaunch(2);});

        this.map3.setOnMouseClicked(e -> {
            chooseNLaunch(3);});


        this.mapaleatoire.setOnMouseClicked(e -> {
           chooseNLaunch(4);});

    }

    public void chooseNLaunch(int i){
        SoundGame.playSoundEffect(1);
        ChoixMap.setChoixMap(i);
        JeuApplication.launchGame();

        SoundGame.stop();
        window.getScene().getWindow().hide();
    }


}
