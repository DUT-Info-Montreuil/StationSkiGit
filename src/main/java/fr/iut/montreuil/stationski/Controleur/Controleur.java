package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;

import fr.iut.montreuil.stationski.Modele.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;


import java.net.URL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private TilePane root;

    @FXML
    private Pane panneauDeJeu;

    private Timeline gameLoop;


    @FXML
    private Label monnaie;

    @FXML
    private Label PV;

    @FXML
    private ImageView imageCanonEau;

    @FXML
    private ImageView imageCanonNeige;

    private Environnement env;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListChangeListener<Entite> listen = new ListObs(panneauDeJeu, env);


        Terrain terrain = new Terrain(32,32,1,  new Sommet(0,24, false), new Sommet(0, 7,false));
        this.env = new Environnement(terrain);
        //Ennemi ennemi = new Ennemi(10, 20, 20, 1, env, 1);
        monnaie.textProperty().bind(env.getArgentP().asString());
        PV.textProperty().bind((env.getPVP().asString()));
        this.env.getVague().getListEnnemis().addListener(listen);
        this.env.getListeTours().addListener(listen);

        imageCanonEau.setOnMouseClicked(e -> creationTourTest());


        //this.env.getListeTours().addListener(listen);


        // ici code pour l'aspect des cases
        root.setStyle("-fx-background-color:blue");
        //root.getChildren().add(imageSnow);
// 1 neige, 0 chemin ,  3 spawn , 4 objectif, 5 tour
        for (int row = 0; row<this.env.getTerrain().getList().size(); row++){
            if(this.env.getTerrain().getList().get(row) == 1 || this.env.getTerrain().getList().get(row) == 3 || this.env.getTerrain().getList().get(row) == 5){
                URL urlIm= Main.class.getResource("snow2.png");
                Image im= new Image(String.valueOf(urlIm));
                ImageView imageSnow = new ImageView();
                imageSnow.setImage(im);

                root.getChildren().add(imageSnow);

            }else if(this.env.getTerrain().getList().get(row) == 0 || this.env.getTerrain().getList().get(row) == 4){
                URL urlIm= Main.class.getResource("snow01.png");
                Image im= new Image(String.valueOf(urlIm));
                ImageView imageSnow = new ImageView();
                imageSnow.setImage(im);

                root.getChildren().add(imageSnow);
            }

        }
        //this.setTile();

        initAnimation();
        gameLoop.play();


    }
/*
    public void dessineDijkstra(){
        for(Sommet s :this.env.getVague().getParcours()){

        }
    }
*/

    public void setTile(){

        URL urlIm=Main.class.getResource("Chalet.png");
        Image flag= new Image(String.valueOf(urlIm));
        ImageView imageFlag = new ImageView();
        imageFlag.setImage(flag);

        imageFlag.setX(this.env.getTerrain().getCible().getX());
        imageFlag.setY(this.env.getTerrain().getCible().getY());

        panneauDeJeu.getChildren().add(imageFlag);

    }







    private void initAnimation(){
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.7),
                (ev ->{
                    env.unTour();
                })
        );

        gameLoop.getKeyFrames().add(kf);
    }

    // methode de cration d'une tour (ATTENTION : ici une tour générique)
    // actuellement la méthode est relié au bouton ET a l'image de watercanon (mais ne fonctionne pas quand on clique)
    // notion de prix non implanté
    // PB : je crois qu'il y a un probleme de x et y, j'ai du raté ma conversion de la liste en ligne et col
    @FXML
    int creationTourTest() {
        System.out.println("click");
        int x=0;
        int y=0;
        // la tour ref est necessaire pour avoir le prix de la tour, ici ref n'est pas placée
        Tour ref = new Tour(1,0,0,2,3,env);
        Tour t;
        if (this.env.getArgent() >= ref.getPrix()) {
            for (int row = 0; row < this.env.getTerrain().getList().size(); row++) {

                if (this.env.getTerrain().getList().get(row) == 1) {
                    t = new Tour(3, x, y, 2, 2, env);
                    env.getTerrain().getList().set(row, 5);
                    env.addTour(t);
                    this.env.retraitArgent(t.getPrix());
                    System.out.println("la tour a été placée en x: "+t.getPosX()+" et en y: "+t.getPosY());
                    return 0;
                }
                if (row % 32 == 0 && row != 0) {
                    y++;
                }

                x++;
                if (x > 32) {
                    x = 0;
                }
            }
        }
        System.out.println("pas assez d'argent pour acheter une tour");
        return 1;
    }



}