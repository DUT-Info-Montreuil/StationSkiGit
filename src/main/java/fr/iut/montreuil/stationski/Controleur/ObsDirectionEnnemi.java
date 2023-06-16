package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Ennemis.Bobsleigh;
import fr.iut.montreuil.stationski.Modele.Ennemis.SkieurBasique;
import fr.iut.montreuil.stationski.Modele.Ennemis.Snowboarder;
import fr.iut.montreuil.stationski.Modele.Ennemis.Snowboarder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class ObsDirectionEnnemi implements ChangeListener<String> {

    private Ennemi e;
    private Pane panneauJeu;

    public ObsDirectionEnnemi(Ennemi e, Pane panneauJeu) {
        this.e = e;
        this.panneauJeu = panneauJeu;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        URL urlIm = null;
        if (t1.equals("g")){
            if (e instanceof SkieurBasique){
                urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/skieur1G.png");
            }
            else if (e instanceof Bobsleigh){
                urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/bobsleigh2G.png");
            }
            else if (e instanceof Snowboarder){
                urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/snowboarderG.png");
            }
            else {
                urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/luge2G.png");
            }
        }

        if (t1.equals("d")){
            if (e instanceof SkieurBasique){
                urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/skieur1.png");
            }
            else if (e instanceof Bobsleigh){
                urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/bobsleigh2.png");
            }
            else if (e instanceof Snowboarder){
                urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/snowboarder.png");
            }
            else {
                urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/luge2.png");
            }
        }

        if (t1.equals("h")){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/yeti3Dos.png");
        }
        if (t1.equals("yb")){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/yeti3.png");
        }

        Image im= new Image(String.valueOf(urlIm));
        ImageView i = (ImageView) panneauJeu.lookup("#"+e.getId());
        i.setImage(im);
    }
}
