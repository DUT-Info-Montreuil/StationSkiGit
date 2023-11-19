package fr.iut.montreuil.stationski.Vue;


import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tours.*;
import javafx.scene.layout.Pane;


public class VueTour extends VueGenerique{

    public VueTour(Pane panneauJeu, Environnement env){
        super(panneauJeu, env);
    }

    @Override
    public void construitSprite(Acteur e){
        String entiteNom;
        Acteur a = e.getType();
        if (a instanceof CanonEau){
            entiteNom = "canonEau";
        }
        else if(a instanceof Teleski){
            entiteNom = "teleski";
        }
        else if (a instanceof CanonNeige){
            entiteNom = "canonNeige";
        }
        else if (a instanceof Biathlon){
            entiteNom = "biathlon";
        }
        else if (a instanceof Telesiege){
            entiteNom = "telesiege";
        }
        else if (a instanceof DoNotCross){
            entiteNom = "donotcross";
        }
        else {
            entiteNom = "cahute";
        }

        Sprite sprite = new Sprite(entiteNom);

        sprite.translateXProperty().bind(e.getPosXP());
        sprite.translateYProperty().bind(e.getPosYP());
        sprite.setId(e.getId());
        super.getPanneauJeu().getChildren().add(sprite);

    }
}
