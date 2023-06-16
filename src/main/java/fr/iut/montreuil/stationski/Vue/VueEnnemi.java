package fr.iut.montreuil.stationski.Vue;


import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Ennemis.*;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;


public class VueEnnemi {
    private Pane panneauJeu;
    private Environnement env;

    public VueEnnemi(Pane panneauJeu, Environnement env){
        this.env = env;
        this.panneauJeu = panneauJeu;
    }

    public void construitSpriteEnnemi(Entite e){
        URL urlIm;

        if (e instanceof SkieurBasique) {
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/skieur1.png");
        }
        else if(e instanceof Yeti){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/yeti3.png");
        }
        else if(e instanceof Bobsleigh){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/bobsleigh2.png");
        }
        else if(e instanceof Snowboarder){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/snowboarder.png");
        }
        else if(e instanceof Luge){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/luge2.png");
        }
        else {
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/snowboarder.png");
        }


            int skin = (int) (Math.random()*50);
            if(skin == 0){
                urlIm = Main.class.getResource("crazyFrog.png");
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
