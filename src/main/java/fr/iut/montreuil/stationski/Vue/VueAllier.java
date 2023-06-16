package fr.iut.montreuil.stationski.Vue;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Ennemis.SkieurBasique;
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
        URL urlIm;
        urlIm = Main.class.getResource("skieur1.png");

        Image im= new Image(String.valueOf(urlIm));
        ImageView imageEn = new ImageView();
        imageEn.setImage(im);

        imageEn.translateXProperty().bind(e.getPosXP());
        imageEn.translateYProperty().bind(e.getPosYP());

        imageEn.setId(e.getId());
        panneauJeu.getChildren().add(imageEn);

    }
}