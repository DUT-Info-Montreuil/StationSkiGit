package fr.iut.montreuil.stationski.Controleur;


import fr.iut.montreuil.stationski.Modele.Tours.Allier;
import fr.iut.montreuil.stationski.Vue.Sprite;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

import javafx.scene.layout.Pane;


public class ObsDirectionAllier implements ChangeListener<String> {

    private Allier a;
    private Pane panneauJeu;

    public ObsDirectionAllier(Allier a, Pane panneauJeu) {
        this.a = a;
        this.panneauJeu = panneauJeu;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        Sprite sprite = (Sprite) panneauJeu.lookup("#"+a.getId());
        sprite.setImage(new Image(sprite.donnerSourceImage("moniteur", t1)));

    }
}
