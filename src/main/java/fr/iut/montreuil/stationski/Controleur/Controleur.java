package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;

import fr.iut.montreuil.stationski.Modele.*;
import fr.iut.montreuil.stationski.Vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;


import java.net.URL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.List;
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

    private Environnement env;

    @FXML
    private Label ttNbEnnemis;

    @FXML
    private Label ttNbVague;

    @FXML
    private ImageView ButtonPlay;

    @FXML
    private ImageView ButtonPause;

    @FXML
    private ImageView ButtonQuit;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Terrain terrain = new Terrain(32,32,1,  new Sommet(0,24, false), new Sommet(0, 7,false));
        this.env = new Environnement(terrain);


        ListChangeListener<Entite> listen = new ListObs(panneauDeJeu, env);
        ListChangeListener<Entite> pvListen = (c -> {if(this.env.getPV()==0){
            gameLoop.stop();
            Terrain resetTerrain = new Terrain(32,32,1,  new Sommet(0,24, false), new Sommet(0, 7,false));
            this.env = new Environnement(resetTerrain);
        }});

        monnaie.textProperty().bind(env.getArgentP().asString());
        PV.textProperty().bind((env.getPVP().asString()));
        this.env.getVague().getListEnnemis().addListener(listen);
        this.env.getListeTours().addListener(listen);
        this.env.getVague().getListEnnemis().addListener(pvListen);


        ttNbEnnemis.textProperty().bind(this.env.nbEnnemisProperty().asString());
        ttNbVague.textProperty().bind(this.env.getVague().numeroVagueProperty().asString());
        imageCanonEau.setOnMouseClicked(e -> creationTourTest());
        ButtonPlay.setOnMouseClicked(e -> { this.gameLoop.play();});
        ButtonPause.setOnMouseClicked(( e-> this.gameLoop.pause()));


        //this.env.getListeTours().addListener(listen);


        // ici code pour l'aspect des cases
       VueTerrain vueTerrain = new VueTerrain(env, root);
       vueTerrain.afficheMap();
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
                Duration.seconds(0.010),
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


    // detection du drag sur l'image du canon à eau. Ici le drag stocke l'image
    @FXML
    void CanonEauDragDetection(MouseEvent event) {
        Dragboard db = imageCanonEau.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        URL urlIm;
        urlIm = Main.class.getResource("watertower.png");
        Image im= new Image(String.valueOf(urlIm));
        cb.putImage(im);
        cb.putString("canonEau");

        db.setContent(cb);
        event.consume();
    }

    // pour les 2 méthodes suiv il s'agit du TilePane (et pas le pane) qui est en lien avec ces méthodes
    // quand le drag est au dessus de l'élément cible (ici le Tilepane)
    @FXML
    void tourDragOver(DragEvent event) {
        if (event.getDragboard().hasImage() || event.getDragboard().hasString()){
            int x = (int) Math.round(event.getX());
            int y = (int) Math.round(event.getY());
            int ncase = ((y/16)*32+(x/16));
            if (this.env.getTerrain().getList().get(ncase) == 1) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        }
    }

    // quand le drag est déposé sur le TilePane, il faut donc connaitre la position dans le pane
    @FXML
    int tourDragDrop(DragEvent event) {
        String str = event.getDragboard().getString();
        if (str.equals("canonEau")){
            int x = (int) Math.round(event.getX());
            int y = (int) Math.round(event.getY());
            // ici une tour ref pour le prix. elle doit donc etre la tour en question
            Tour ref = new Tour(1,0,0,2,3,env);
            Tour t;
            if (this.env.getArgent() >= ref.getPrix()){
                int ncase = ((y/16)*32+(x/16));
                t = new Tour(3, x, y, 40, 50, env);
                env.getTerrain().getList().set(ncase, 5);
                env.addTour(t);
                this.env.retraitArgent(t.getPrix());
                System.out.println("la tour a été placée en x: "+t.getPosX()+" et en y: "+t.getPosY());
                return 0;

            }
            else System.out.println("pas assez d'argent pour acheter une tour");

        }

        return 1;
    }


}