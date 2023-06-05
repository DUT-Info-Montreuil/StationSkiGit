package fr.iut.montreuil.stationski.Vue;


import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tours.Biathlon;
import fr.iut.montreuil.stationski.Modele.Tours.CanonEau;
import fr.iut.montreuil.stationski.Modele.Tours.CanonNeige;
import fr.iut.montreuil.stationski.Modele.Tours.Teleski;
import javafx.collections.ListChangeListener;
import fr.iut.montreuil.stationski.Modele.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;


public class VueTour {
    private Pane panneauJeu;
    private Environnement env;
    public VueTour(Pane panneauJeu, Environnement env){
        this.env = env;
        this.panneauJeu = panneauJeu;
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
            urlIm = Main.class.getResource("watertower.png");
        }
        else if (e instanceof Biathlon){
            urlIm = Main.class.getResource("watertower.png");
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
