package fr.iut.montreuil.stationski.Vue;


import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import javafx.scene.layout.Pane;


public class VueAllier extends VueGenerique{


    public VueAllier(Pane panneauJeu, Environnement env){
        super(panneauJeu, env);
    }

    @Override
    public void construitSprite(Acteur e){
        Sprite sprite = new Sprite("moniteur");
        sprite.translateXProperty().bind(e.getPosXP());
        sprite.translateYProperty().bind(e.getPosYP());
        sprite.setId(e.getId());
        super.getPanneauJeu().getChildren().add(sprite);

    }
}