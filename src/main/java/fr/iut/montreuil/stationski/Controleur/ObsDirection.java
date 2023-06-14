package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Ennemis.Bobsleigh;
import fr.iut.montreuil.stationski.Modele.Ennemis.SkieurBasique;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.net.URL;

public class ObsDirection implements ChangeListener<String> {

    private Ennemi e;
    private Pane panneauJeu;

    public ObsDirection(Ennemi e, Pane panneauJeu) {
        this.e = e;
        this.panneauJeu = panneauJeu;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        URL urlIm = null;
        if (t1.equals("g")){
            if (e instanceof SkieurBasique){
                urlIm = Main.class.getResource("skieur1G.png");
            }
            else{
                urlIm = Main.class.getResource("bobsleigh2G.png");
            }
        }

        if (t1.equals("d")){
            if (e instanceof SkieurBasique){
                urlIm = Main.class.getResource("skieur1.png");
            }
            else{
                urlIm = Main.class.getResource("bobsleigh2.png");
            }
        }

        if (t1.equals("h")){
            urlIm = Main.class.getResource("yeti3Dos.png");
        }

        Image im= new Image(String.valueOf(urlIm));
        ImageView i = (ImageView) panneauJeu.lookup("#"+e.getId());
        i.setImage(im);
    }
}
