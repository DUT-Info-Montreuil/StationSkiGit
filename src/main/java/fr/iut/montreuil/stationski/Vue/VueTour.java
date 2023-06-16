package fr.iut.montreuil.stationski.Vue;

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
    }


    public void construitSpriteTour(Entite e){
        URL urlIm;

        if (e instanceof CanonEau){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/canoneauv3.png");
        }
        else if(e instanceof Teleski){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/teleski3.png");
        }
        else if (e instanceof CanonNeige){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/canonNeige3.png");
        }
        else if (e instanceof Biathlon){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/biathlon3.png");
        }
        else if (e instanceof Telesiege){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/telesiege3.png");
        }
        else if (e instanceof DoNotCross){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/DoNotCross2.png");
        }
        else if (e instanceof Cahute){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/cahute3.png");
        }
        else{
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/watertower.png");
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
