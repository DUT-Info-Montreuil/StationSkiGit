package fr.iut.montreuil.stationski.Vue;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class VueAllier {
    private Pane panneauJeu;
    private Environnement env;

    public VueAllier(Pane panneauJeu, Environnement env){
        this.env = env;
        this.panneauJeu = panneauJeu;
    }

    public void construitSpriteAllier(Entite e){
        Sprite sprite = new Sprite("moniteur");
        sprite.translateXProperty().bind(e.getPosXP());
        sprite.translateYProperty().bind(e.getPosYP());
        sprite.setId(e.getId());
        panneauJeu.getChildren().add(sprite);

    }
}