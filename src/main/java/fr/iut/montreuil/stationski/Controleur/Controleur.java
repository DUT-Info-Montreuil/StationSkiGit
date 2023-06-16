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
import fr.iut.montreuil.stationski.Vue.VueTerrainAléatoire;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private TilePane root;

    @FXML
    private Pane panneauDeJeu;

    private Timeline gameLoop;

    @FXML
    private BorderPane panePrincipal;

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
    private StackPane victoire;
    @FXML private StackPane defaite;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creationEtAffichageMap();

        FinirPartie();
        GagnerPartie();
        vueDesProjectiles();
        vueDesEntites();
        AffichageStatistiques();

        ajouterCapacitesEnvironnement();
        rendCapableDeVendreTours();

        initAnimation();
        gameLoop.play();


    }

    private void GagnerPartie() {
        ChangeListener<Number> envPvListen = (((observable, oldValue, newValue) -> {if ((Integer)newValue > 10){
            this.victoire.setVisible(true);
            gameLoop.stop();}
        }));
        this.env.getVague().numeroVagueProperty().addListener(envPvListen);

    }

    public void creationEtAffichageMap(){
        root.setFocusTraversable(true);
        Terrain terrain;
        if(ChoixMap.getChoix() == 4){


            terrain = new TerrainAléatoire(45, 45);
            this.env = new Environnement(terrain);
            VueTerrain vueTerrain = new VueTerrainAléatoire(this.env, root);
            vueTerrain.construitMap();

        }else {
            VueTerrain vueTerrain = new VueTerrain(env, root, ChoixMap.getChoix());
            terrain = new Terrain(45, 45, ChoixMap.getChoix(), vueTerrain.créerListeTerrain());
            vueTerrain.construitMap();
            this.env = new Environnement(terrain);

        }

    }

    public void FinirPartie(){
        ChangeListener<Number> envPvListen = (((observable, oldValue, newValue) -> {if ((Integer)newValue <=0){
            this.defaite.setVisible(true);
            gameLoop.stop();

        }
        }));
        this.env.getPVP().addListener(envPvListen);
    }
    public void vueDesProjectiles(){
        ListChangeListener<Projectile> listenProj = new ListObsProj(panneauDeJeu, env);
        this.env.getListeProj().addListener(listenProj);
    }
    public void vueDesEntites(){
        ListChangeListener<Entite> listenEntite = new ListObs(panneauDeJeu, env);
        this.env.getVague().getListEnnemis().addListener(listenEntite);
        this.env.getListeTours().addListener(listenEntite);
        this.env.getListeAllier().addListener(listenEntite);
    }
    public void AffichageStatistiques(){
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

    @FXML
    void home(MouseEvent event){
        this.gameLoop.stop();
        panePrincipal.getScene().getWindow().hide();
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
        urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/canoneauv3.png");
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
        urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/canonNeige3.png");
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
        urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/teleski3.png");
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
        urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/biathlon3.png");
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
        urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/telesiege3.png");
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
        urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/DoNotCross2.png");
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
        urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/cahute3.png");
        Image im= new Image(String.valueOf(urlIm));
        cb.putImage(im);
        cb.putString("cahute");

        db.setContent(cb);
        event.consume();
    }

    @FXML
    void tourDragOver(DragEvent event) {
        if (event.getDragboard().hasImage() || event.getDragboard().hasString()){
            int x = (int) Math.round(event.getX());
            int y = (int) Math.round(event.getY());
            int ncase = ((y/16)*45+(x/16));
            if(event.getDragboard().getString().equals("cahute")){
                if(positionDeLaCahutePossible(x, y) ){
                    if ( (x/16)<44 && (y/16)<44 && (this.env.getTerrain().getList().get(ncase) == 1 && this.env.getTerrain().getList().get(ncase+1)==1 && this.env.getTerrain().getList().get(ncase+45)==1 && this.env.getTerrain().getList().get(ncase+46)==1 && !event.getDragboard().getString().equals("donotcross")) ^ (event.getDragboard().getString().equals("donotcross") && this.env.getTerrain().getList().get(ncase) == 0)) {
                        event.acceptTransferModes(TransferMode.ANY);
                    }
                }

            }
            else if ( (x/16)<44 && (y/16)<44 && (this.env.getTerrain().getList().get(ncase) == 1 && this.env.getTerrain().getList().get(ncase+1)==1 && this.env.getTerrain().getList().get(ncase+45)==1 && this.env.getTerrain().getList().get(ncase+46)==1 && !event.getDragboard().getString().equals("donotcross")) ^ (event.getDragboard().getString().equals("donotcross") && this.env.getTerrain().getList().get(ncase) == 0)) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        }
    }

    public boolean positionDeLaCahutePossible(int mouseX, int mouseY){
        int xProche =0;
        int yProche =0;
        int numcase0 = 0;
        ArrayList<Integer> List0 = new ArrayList<Integer>();
        for (int i =0; i<this.env.getTerrain().getList().size(); i++){
            if (this.env.getTerrain().getList().get(i) == 0){
                List0.add(i);
            }
        }
        double distance = Math.sqrt((((List0.get(0)%45)*16)-mouseX)*(((List0.get(0)%45)*16)-mouseX) + (((List0.get(0)/45)*16)-mouseY)*(((List0.get(0)/45)*16)-mouseY));
        double distancePlusProche =Math.sqrt((((List0.get(0)%45)*16)-mouseX)*(((List0.get(0)%45)*16)-mouseX) +(((List0.get(0)/45)*16)-mouseY)*(((List0.get(0)/45)*16)-mouseY));

        for (int z =0; z<List0.size(); z++){
            xProche = (List0.get(z)%45)*16;
            yProche = (List0.get(z)/45)*16;
            distance = Math.sqrt((xProche-mouseX)*(xProche-mouseX) + (yProche-mouseY)*(yProche-mouseY));
            if (distance < distancePlusProche){
                numcase0 = List0.get(z);
                distancePlusProche = distance;
                if(distancePlusProche<50) return true;
            }
        }
        return false;
    }

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
                t = new Cahute(x,y,env);
            }

            if (!(t instanceof DoNotCross)){
                env.getTerrain().getList().set(ncase, 5);
                env.getTerrain().getList().set(ncase+1, 5);
                env.getTerrain().getList().set(ncase+45, 5);
                env.getTerrain().getList().set(ncase+46, 5);
            }
            env.addTour(t);
            this.env.retraitArgent(prixTour);
            return 0;

            }
            else System.out.println("pas assez d'argent pour acheter une tour");
        return 1;
    }

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
                        this.panePrincipal.setOnKeyPressed(
                                eventRoot -> {
                                    if(eventRoot.getCode() == KeyCode.S){
                                        ObservableList<Tour> listeDesTours = this.env.getListeTours();
                                        int i=-1;
                                        boolean tourTrouvee=false;
                                        while (i<listeDesTours.size() && !tourTrouvee) {// Recherche la tour correspondante
                                            i++;

                                            if (listeDesTours.get(i).getPosX()<=mouseX*16 && mouseX*16<listeDesTours.get(i).getPosX()+32 && listeDesTours.get(i).getPosY()<=mouseY*16 && mouseY*16<listeDesTours.get(i).getPosY()+32){
                                                tourTrouvee=true;
                                            }
                                        }
                                        if (i<listeDesTours.size()) {
                                            int prixTourVendue;
                                            if(listeDesTours.get(i) instanceof CanonEau){
                                                prixTourVendue=(int)(0.75*this.env.getPrixTours().get("canonEau"));
                                            }
                                            else if (listeDesTours.get(i) instanceof CanonNeige) {
                                                prixTourVendue=(int)(0.75*this.env.getPrixTours().get("canonNeige"));
                                            }else if(listeDesTours.get(i) instanceof Teleski) {
                                                prixTourVendue=(int)(0.75*this.env.getPrixTours().get("teleski"));
                                            }
                                            else if (listeDesTours.get(i) instanceof Biathlon) {
                                                prixTourVendue=(int)(0.75*this.env.getPrixTours().get("biathlon"));
                                            }
                                            else if (listeDesTours.get(i) instanceof Telesiege) {
                                                prixTourVendue=(int)(0.75*this.env.getPrixTours().get("telesiege"));
                                            }
                                            else if (listeDesTours.get(i) instanceof DoNotCross) {
                                                prixTourVendue=(int)(0.75*this.env.getPrixTours().get("donotcross"));
                                            }
                                            else {
                                                prixTourVendue=(int)(0.75*this.env.getPrixTours().get("cahute"));
                                            }

                                            this.env.ajoutArgent(prixTourVendue);
                                            this.env.getTerrain().getTerrain().set((listeDesTours.get(i).getPosX()/16)+(listeDesTours.get(i).getPosY()/16)*45, 1);
                                            this.env.getTerrain().getTerrain().set((listeDesTours.get(i).getPosX()/16)+(listeDesTours.get(i).getPosY()/16)*45 + 1, 1);
                                            this.env.getTerrain().getTerrain().set((listeDesTours.get(i).getPosX()/16)+(listeDesTours.get(i).getPosY()/16)*45 + 45, 1);
                                            this.env.getTerrain().getTerrain().set((listeDesTours.get(i).getPosX()/16)+(listeDesTours.get(i).getPosY()/16)*45 + 46, 1);
                                            listeDesTours.get(i).setPV(0);
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
