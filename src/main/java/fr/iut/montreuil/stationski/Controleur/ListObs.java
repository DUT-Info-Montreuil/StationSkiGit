package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Vue.VueEnnemi;
import fr.iut.montreuil.stationski.Vue.VueTour;
import javafx.collections.ListChangeListener;
import fr.iut.montreuil.stationski.Modele.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class ListObs implements ListChangeListener<Entite> {
    private Pane panneauJeu;
    private Environnement env;

    public ListObs(Pane panneauJeu, Environnement env){
        this.env = env;
        this.panneauJeu = panneauJeu;
    }

    @Override
    public void onChanged(Change<? extends Entite> c){

        while(c.next()){

            for(Entite e : c.getAddedSubList()){

                creerSprite(e);
            }

            for(Entite  e : c.getRemoved()){
                Node sprite = panneauJeu.lookup("#" + e.getId());
                panneauJeu.getChildren().remove(sprite);
            }

        }
    }

    public void creerSprite(Entite e){

        if(e instanceof Ennemi){

            VueEnnemi vueEnnemi = new VueEnnemi(panneauJeu, env);
            vueEnnemi.afficheEnnemi(e);


        }else if (e instanceof Tour){
            VueTour vueTour = new VueTour(panneauJeu, env);
            vueTour.afficheTour(e);
        }

    }
}