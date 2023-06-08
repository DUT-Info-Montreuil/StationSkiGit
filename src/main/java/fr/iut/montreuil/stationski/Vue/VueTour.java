package fr.iut.montreuil.stationski.Vue;

import java.awt.*;
// get the mouse's position
import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tours.*;
import fr.iut.montreuil.stationski.Modele.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import javafx.scene.layout.Pane;


import java.net.URL;



public class VueTour {
    private Pane panneauJeu;
    private Environnement env;

    public VueTour(Pane panneauJeu, Environnement env){
        this.env = env;
        this.panneauJeu = panneauJeu;
        rendCapableDeVendreTours();
    }

    public void rendCapableDeVendreTours(){
        this.panneauJeu.setOnMouseClicked(
                event -> {
                    int mouseX = (((int)event.getX()) - ((int)event.getX()%16)) / 16;
                    int mouseY = (((int)event.getY()) - ((int)event.getY()%16)) / 16;
                    if(this.env.getTerrain().getList().get(mouseX + mouseY*45)==5){
                        this.panneauJeu.setOnKeyPressed(
                                eventRoot -> {
                                    if(eventRoot.getCode() == KeyCode.S){
                                        ObservableList<Tour> listeDesTours = this.env.getListeTours();
                                        int i=0;
                                        while (i<listeDesTours.size() && (listeDesTours.get(i).getPosX()!=mouseX*16 || listeDesTours.get(i).getPosY()!=mouseY*16)) // Recherche la tour correspondante
                                            i++;
                                        if (i<listeDesTours.size()) {
                                            this.env.ajoutArgent((int)(0.75*listeDesTours.get(i).getPrix()));
                                            listeDesTours.remove(i);
                                        }
                                    }
                                }
                        );
                    }
                }
        );

    }

    public void afficheTour(Entite e){
        URL urlIm;

        if (e instanceof CanonEau){
            urlIm = Main.class.getResource("watertower.png");
        }
        if(e instanceof Teleski){
            urlIm = Main.class.getResource("teleski2.png");
        }
        else if (e instanceof CanonNeige){
            urlIm = Main.class.getResource("canonNeige2.png");
        }
        else if (e instanceof Biathlon){
            urlIm = Main.class.getResource("biathlon2.png");
        }
        else if (e instanceof Telesiege){
            urlIm = Main.class.getResource("telesiege2.png");
        }
        else if (e instanceof DoNotCross){
            urlIm = Main.class.getResource("DoNotCross2.png");
        }
        else if (e instanceof Cahute){
            urlIm = Main.class.getResource("cahute2.png");
        }
        else{
        urlIm = Main.class.getResource("watertower.png");
        }

        Image im= new Image(String.valueOf(urlIm));
        ImageView imageEn = new ImageView();
        imageEn.setImage(im);
        imageEn.translateXProperty().bind(e.getPosXP());
        imageEn.translateYProperty().bind(e.getPosYP());
        imageEn.setId(e.getId());
        panneauJeu.getChildren().add(imageEn);

    }
}
