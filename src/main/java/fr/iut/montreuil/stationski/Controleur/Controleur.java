package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.ChoixMap;
import fr.iut.montreuil.stationski.Main;

import fr.iut.montreuil.stationski.Modele.*;
import fr.iut.montreuil.stationski.Modele.Competences.CapaciteAffaiblissement;
import fr.iut.montreuil.stationski.Modele.Competences.CapaciteBoost;
import fr.iut.montreuil.stationski.Modele.Competences.CapaciteDegat;
import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;
import fr.iut.montreuil.stationski.Modele.Tours.*;
import fr.iut.montreuil.stationski.Vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;


import java.net.URL;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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

    @FXML
    private ImageView imageTeleski;

    @FXML
    private ImageView imageBiathlon;

    @FXML
    private ImageView imageDonotcross;

    @FXML
    private ImageView imageTelesiege;

    @FXML
    private ImageView imageCahute;

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

    @FXML
    private MediaView media;
    private MediaPlayer mediaPlayer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ici code pour l'aspect des cases
        creationEtAffichageMap();

        creerListenerPvEnvironnement();
        creerListenerProjectileSprite();
        creerListenerEntiteSprite();
        creerBindStatistiques();

        ajouterCapacitesEnvironnement();
        rendCapableDeVendreTours();

        initAnimation();
        gameLoop.play();


    }

    public void creationEtAffichageMap(){
        root.setFocusTraversable(true);
        VueTerrain vueTerrain = new VueTerrain(env, root, ChoixMap.getChoix());
        Terrain terrain = new Terrain(45,45,1, getSommetSource(3), getSommetCible(3), vueTerrain.créerListeTerrain());
        vueTerrain.afficheMap();
        this.env = new Environnement(terrain);
    }

    public void creerListenerPvEnvironnement(){
        javafx.beans.value.ChangeListener<Number> envPvListen = (((observable, oldValue, newValue) -> {if ((Integer)newValue <=0){
        gameLoop.stop();}
        }));
        this.env.getPVP().addListener(envPvListen);
    }
    public void creerListenerProjectileSprite(){
        ListChangeListener<Projectile> listenProj = new ListObsProj(panneauDeJeu, env);
        this.env.getListeProj().addListener(listenProj);
    }
    public void creerListenerEntiteSprite(){
        ListChangeListener<Entite> listenEntite = new ListObs(panneauDeJeu, root, env);
        this.env.getVague().getListEnnemis().addListener(listenEntite);
        this.env.getListeTours().addListener(listenEntite);
        this.env.getListeAllier().addListener(listenEntite);
    }
    public void creerBindStatistiques(){
        monnaie.textProperty().bind(env.getArgentP().asString());
        PV.textProperty().bind((env.getPVP().asString()));
        ttNbEnnemis.textProperty().bind(this.env.nbEnnemisProperty().asString());
        ttNbVague.textProperty().bind(this.env.getVague().numeroVagueProperty().asString());
    }
    public void ajouterCapacitesEnvironnement(){
        Capacite c1 = new CapaciteDegat(env);
        Capacite c2 = new CapaciteAffaiblissement(env);
        Capacite c3 = new CapaciteBoost(env);
        this.env.addCapacite(c1);
        this.env.addCapacite(c2);
        this.env.addCapacite(c3);
    }


    @FXML
    void pause(MouseEvent event) {
        this.gameLoop.pause();
    }

    @FXML
    void play(MouseEvent event) {
        this.gameLoop.play();
    }
    public Sommet getSommetSource(int intMapSelect){
        if(intMapSelect==0){
            return new Sommet(13,0, false);
        }
        else if (intMapSelect==1){
            return new Sommet(37, 0, false);
        }
        else if(intMapSelect==2){
            return new Sommet(5, 0, false);
        }
        else {//if (intMapSelect==3)
            return new Sommet(40, 0, false);
        }
    }
    public Sommet getSommetCible(int intMapSelect){
        if(intMapSelect==0){
            return new Sommet(25,44, false);
        }
        else if (intMapSelect==1){
            return new Sommet(31, 44, false);
        }
        else if(intMapSelect==2){
            return new Sommet(24, 44, false);
        }
         else {//if (intMapSelect==3)
            return new Sommet(36, 44, false);
        }
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

    // detection du drag sur l'image du canon à eau. Ici le drag stocke l'image
    @FXML
    void CanonEauDragDetection(MouseEvent event) {
        Dragboard db = imageCanonEau.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        URL urlIm;
        urlIm = Main.class.getResource("canoneauv3.png");
        Image im= new Image(String.valueOf(urlIm));
        cb.putImage(im);
        cb.putString("canonEau");

        db.setContent(cb);
        event.consume();
    }
    @FXML
    void CanonNeigeDragDetection(MouseEvent event) {
        Dragboard db = imageCanonNeige.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        URL urlIm;
        urlIm = Main.class.getResource("canonNeige3.png");
        Image im= new Image(String.valueOf(urlIm));
        cb.putImage(im);
        cb.putString("canonNeige");

        db.setContent(cb);
        event.consume();
    }


    @FXML
    void TeleskiDragDetection(MouseEvent event) {
        Dragboard db = imageTeleski.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        URL urlIm;
        urlIm = Main.class.getResource("teleski3.png");
        Image im= new Image(String.valueOf(urlIm));
        cb.putImage(im);
        cb.putString("teleski");

        db.setContent(cb);
        event.consume();
    }

    @FXML
    void BiathlonDragDetection(MouseEvent event) {
        Dragboard db = imageBiathlon.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        URL urlIm;
        urlIm = Main.class.getResource("biathlon3.png");
        Image im= new Image(String.valueOf(urlIm));
        cb.putImage(im);
        cb.putString("biathlon");

        db.setContent(cb);
        event.consume();
    }

    @FXML
    void TelesiegeDragDetection(MouseEvent event) {
        Dragboard db = imageTelesiege.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        URL urlIm;
        urlIm = Main.class.getResource("telesiege3.png");
        Image im= new Image(String.valueOf(urlIm));
        cb.putImage(im);
        cb.putString("telesiege");

        db.setContent(cb);
        event.consume();
    }

    @FXML
    void DonotcrossDragDetection(MouseEvent event) {
        Dragboard db = imageDonotcross.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        URL urlIm;
        urlIm = Main.class.getResource("DoNotCross2.png");
        Image im= new Image(String.valueOf(urlIm));
        cb.putImage(im);
        cb.putString("donotcross");

        db.setContent(cb);
        event.consume();
    }

    @FXML
    void CahuteDragDetection(MouseEvent event) {
        Dragboard db = imageCahute.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        URL urlIm;
        urlIm = Main.class.getResource("cahute3.png");
        Image im= new Image(String.valueOf(urlIm));
        cb.putImage(im);
        cb.putString("cahute");

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
            int ncase = ((y/16)*45+(x/16));
            if ( (x/16)<44 && (y/16)<44 && (this.env.getTerrain().getList().get(ncase) == 1 && this.env.getTerrain().getList().get(ncase+1)==1 && this.env.getTerrain().getList().get(ncase+45)==1 && this.env.getTerrain().getList().get(ncase+46)==1 && !event.getDragboard().getString().equals("donotcross")) ^ (event.getDragboard().getString().equals("donotcross") && this.env.getTerrain().getList().get(ncase) == 0)) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        }
    }

    // quand le drag est déposé sur le TilePane, il faut donc connaitre la position dans le pane
    @FXML
    int tourDragDrop(DragEvent event) {
        String str = event.getDragboard().getString();
        int prixTour;
        if (str.equals("canonEau")) {
           prixTour = this.env.getPrixTours().get(str);
        }else if(str.equals(("teleski"))) {
            prixTour = this.env.getPrixTours().get(str);
        }
        else if (str.equals(("canonNeige"))) {
            prixTour = this.env.getPrixTours().get(str);
        }
        else if (str.equals(("biathlon"))) {
            prixTour = this.env.getPrixTours().get(str);
        }
        else if (str.equals(("telesiege"))) {
            prixTour = this.env.getPrixTours().get(str);
        }
        else if (str.equals("donotcross")){
            prixTour = this.env.getPrixTours().get(str);
        }
        else{ //  (str.equals("cahute"))
            prixTour = this.env.getPrixTours().get(str);
        }

        int x = (int) Math.round(event.getX());
        int y = (int) Math.round(event.getY());

        Tour t;
        if (this.env.getArgent() >= prixTour){
            // (y*32+x)/16 = case dans terrain
            // ou (y%16)*32+(x%16)
            int ncase = (y/16)*45+(x/16);
            y=y-(y%16);
            x=x-(x%16);
            if (str.equals("canonEau")) {
                t = new CanonEau( x, y, env);
            }else if(str.equals(("teleski"))) {
                t = new Teleski(x,y, env);
            }
            else if (str.equals(("canonNeige"))) {
                t = new CanonNeige(x,y,env);
            }
            else if (str.equals(("biathlon"))) {
                t = new Biathlon(x,y,env);
            }
            else if (str.equals(("telesiege"))) {
                t = new Telesiege(x,y,env);
            }
            else if (str.equals(("donotcross"))) {
                t = new DoNotCross(x,y,env);
            }
            else  {//(str.equals(("cahute")))
                t = new Cahute(x,y,env, true);
            }
            //pour pas que les ennemis soit bloqués quand spawn, car changement valeur case quand tour posée
            if (!(t instanceof DoNotCross)){
                env.getTerrain().getList().set(ncase, 5);
                env.getTerrain().getList().set(ncase+1, 5);
                env.getTerrain().getList().set(ncase+45, 5);
                env.getTerrain().getList().set(ncase+46, 5);
            }
            env.addTour(t);
            this.env.retraitArgent(t.getPrix());
            return 0;

            }
            else System.out.println("pas assez d'argent pour acheter une tour");
        return 1;
    }

    // Partie sur les capacités
    @FXML
    void avalancheClicked(MouseEvent event) {
        for(int i =0; i<this.env.getCapacites().size(); i++){
            if ("Avalanche".equals(this.env.getCapacites().get(i).getNom())){
                if (this.env.getArgent()>=this.env.getCapacites().get(i).getCout()) {
                    this.env.getCapacites().get(i).activation();
                }
                else{
                    System.out.println("pas assez d'argent pour activer cette capacité");
                }
            }
        }

    }

    @FXML
    void tempeteClicked(MouseEvent event) {
        for(int i =0; i<this.env.getCapacites().size(); i++){
            if ("Tempete".equals(this.env.getCapacites().get(i).getNom())){
                if (this.env.getArgent()>=this.env.getCapacites().get(i).getCout()) {
                    this.env.getCapacites().get(i).activation();
                }
                else{
                    System.out.println("pas assez d'argent pour activer cette capacité");
                }
            }
        }
    }
    @FXML
    void dopageClicked(MouseEvent event) {
        for(int i =0; i<this.env.getCapacites().size(); i++){
            if ("Dopage".equals(this.env.getCapacites().get(i).getNom())){
                if (this.env.getArgent()>=this.env.getCapacites().get(i).getCout()) {
                    this.env.getCapacites().get(i).activation();
                }
                else{
                    System.out.println("pas assez d'argent pour activer cette capacité");
                }
            }
        }
    }
    public void rendCapableDeVendreTours(){
        this.panneauDeJeu.setOnMouseClicked(
                event -> {
                    int mouseX = (((int)event.getX()) - ((int)event.getX()%16)) / 16;
                    int mouseY = (((int)event.getY()) - ((int)event.getY()%16)) / 16;
                    if(this.env.getTerrain().getList().get(mouseX + mouseY*45)==5){
                        this.panneauDeJeu.setOnKeyPressed(
                                eventRoot -> {
                                    if(eventRoot.getCode() == KeyCode.S){
                                        ObservableList<Tour> listeDesTours = this.env.getListeTours();
                                        int i=0;
                                        while (i<listeDesTours.size() && (listeDesTours.get(i).getPosX()!=mouseX*16 || listeDesTours.get(i).getPosY()!=mouseY*16)) // Recherche la tour correspondante
                                            i++;
                                        if (i<listeDesTours.size()) {
                                            this.env.ajoutArgent((int)(0.75*listeDesTours.get(i).getPrix()));
                                            this.env.getTerrain().getTerrain().set((listeDesTours.get(i).getPosX()/16)+(listeDesTours.get(i).getPosY()/16)*45, 1);
                                            this.env.getTerrain().getTerrain().set((listeDesTours.get(i).getPosX()/16)+(listeDesTours.get(i).getPosY()/16)*45 + 1, 1);
                                            this.env.getTerrain().getTerrain().set((listeDesTours.get(i).getPosX()/16)+(listeDesTours.get(i).getPosY()/16)*45 + 45, 1);
                                            this.env.getTerrain().getTerrain().set((listeDesTours.get(i).getPosX()/16)+(listeDesTours.get(i).getPosY()/16)*45 + 46, 1);
                                            listeDesTours.remove(i);
                                        }
                                    }
                                }
                        );
                    }
                }
        );

    }

}
