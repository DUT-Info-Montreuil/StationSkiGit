package fr.iut.montreuil.stationski.Vue;


import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tours.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.net.URL;


public class VueTour {
    private Pane panneauJeu;
    private Environnement env;

    public VueTour(Pane panneauJeu, Environnement env){
        this.env = env;
        this.panneauJeu = panneauJeu;
    }


    public void construitSpriteTour(Entite e){
        String entiteNom;

        if (e instanceof CanonEau){
            entiteNom = "canonEau";
        }
        else if(e instanceof Teleski){
            entiteNom = "teleski";
        }
        else if (e instanceof CanonNeige){
            entiteNom = "canonNeige";
        }
        else if (e instanceof Biathlon){
            entiteNom = "biathlon";
        }
        else if (e instanceof Telesiege){
            entiteNom = "telesiege";
        }
        else if (e instanceof DoNotCross){
            entiteNom = "donotcross";
        }
        else {
            entiteNom = "cahute";
        }

        Sprite sprite = new Sprite(entiteNom);

        sprite.translateXProperty().bind(e.getPosXP());
        sprite.translateYProperty().bind(e.getPosYP());
        sprite.setId(e.getId());
        panneauJeu.getChildren().add(sprite);

    }
}
