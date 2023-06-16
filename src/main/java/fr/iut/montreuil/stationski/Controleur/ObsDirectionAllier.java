package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Tours.Allier;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class ObsDirectionAllier implements ChangeListener<String> {

    private Allier a;
    private Pane panneauJeu;

    public ObsDirectionAllier(Allier a, Pane panneauJeu) {
        this.a = a;
        this.panneauJeu = panneauJeu;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        URL urlIm = null;
        if (t1.equals("g")){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/monoG.png");
        }

        if (t1.equals("d")) {
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/mono.png");
        }

        Image im= new Image(String.valueOf(urlIm));
        ImageView i = (ImageView) panneauJeu.lookup("#"+a.getId());
        i.setImage(im);
    }
}
