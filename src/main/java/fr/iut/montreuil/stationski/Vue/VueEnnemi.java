package fr.iut.montreuil.stationski.Vue;


import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Ennemis.*;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;

import javafx.scene.layout.Pane;



public class VueEnnemi extends VueGenerique{


    public VueEnnemi(Pane panneauJeu, Environnement env){
        super(panneauJeu, env);
    }
    @Override
    public void construitSprite(Acteur e){
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
        this.getPanneauJeu().getChildren().add(sprite);

    }

}
