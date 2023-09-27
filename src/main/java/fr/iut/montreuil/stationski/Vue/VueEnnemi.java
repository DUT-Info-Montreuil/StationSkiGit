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
        String entiteNom;
        if (e instanceof SkieurBasique) {
            entiteNom = "skieur";
        }
        else if(e instanceof Yeti){
            entiteNom = "yeti";
        }
        else if(e instanceof Bobsleigh){
            entiteNom = "bobsleigh";
        }
        else if(e instanceof Snowboarder){
            entiteNom = "snowboarder";
        }
        else{
            entiteNom = "luge";
        }

        Sprite sprite = new Sprite(entiteNom);
        sprite.translateXProperty().bind(e.getPosXP());
        sprite.translateYProperty().bind(e.getPosYP());
        sprite.setId(e.getId());
        panneauJeu.getChildren().add(sprite);

    }

}
