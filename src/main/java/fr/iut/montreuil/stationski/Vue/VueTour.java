package fr.iut.montreuil.stationski.Vue;


import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;
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

    /**
    public void construitSpriteTour1(Entite e){
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

    }**/
    public void construitSpriteTour(Acteur a){
        String entiteNom;
        Acteur aType = a.getType();
        if (aType instanceof CanonEau){
            entiteNom = "canonEau";
        }
        else if(aType instanceof Teleski){
            entiteNom = "teleski";
        }
        else if (aType instanceof CanonNeige){
            entiteNom = "canonNeige";
        }
        else if (aType instanceof Biathlon){
            entiteNom = "biathlon";
        }
        else if (aType instanceof Telesiege){
            entiteNom = "telesiege";
        }
        else if (aType instanceof DoNotCross){
            entiteNom = "donotcross";
        }
        else {
            entiteNom = "cahute";
        }

        Sprite sprite = new Sprite(entiteNom);

        sprite.translateXProperty().bind(a.getPosXP());
        sprite.translateYProperty().bind(a.getPosYP());
        sprite.setId(a.getId());
        panneauJeu.getChildren().add(sprite);

    }
}
