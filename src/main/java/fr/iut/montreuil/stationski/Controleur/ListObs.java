package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;

import fr.iut.montreuil.stationski.Modele.Tours.Allier;
import fr.iut.montreuil.stationski.Vue.VueAllier;
import fr.iut.montreuil.stationski.Vue.VueEnnemi;
import fr.iut.montreuil.stationski.Vue.VueTour;


import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import fr.iut.montreuil.stationski.Modele.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class ListObs implements ListChangeListener<Entite> {
    private Pane panneauJeu;
    private Environnement env;
    private TilePane root;
    public ListObs(Pane panneauJeu, TilePane root, Environnement env){
        this.env = env;
        this.panneauJeu = panneauJeu;
    }

    @Override
    public void onChanged(Change<? extends Entite> c){

        while(c.next()){

            for(Entite e : c.getAddedSubList()){
                ChangeListener<Number> listenPV = (
                        (obs, old, nouv) -> {if (nouv.intValue()>0 && old.intValue()==e.getPVMax()) creerBarreDeVie(e);}
                );
                e.pvProperty().addListener(listenPV);
                creerSprite(e);

            }

            for(Entite  e : c.getRemoved()) {
                Node sprite = panneauJeu.lookup("#" + e.getId());
                panneauJeu.getChildren().remove(sprite);

                sprite = panneauJeu.lookup("#" + e.getId() + "R");
                panneauJeu.getChildren().remove(sprite);
            }
        }
    }
    public void creerBarreDeVie (Entite e){
        Rectangle rectangle = new Rectangle();
        ChangeListener<Number> listenerX = (
                (obs, old, nouv) -> {
                    rectangle.setTranslateX(nouv.floatValue());
                }
        );
        ChangeListener<Number> listenerY = (
                (obs, old, nouv) -> {
                    rectangle.setTranslateY((nouv.floatValue()) -5);
                }
        );
        ChangeListener<Number> listenPVActeur = (
                (obs, old, nouv) -> {rectangle.setWidth(Math.floor(nouv.floatValue()*12));}
        );
        e.getPosXP().addListener(listenerX);
        e.getPosYP().addListener(listenerY);
        if(e instanceof Tour) {
            rectangle.setTranslateX(e.getPosX());
            rectangle.setTranslateY(e.getPosY()-5);
        }

        e.pourcentPVProperty().addListener(listenPVActeur);
        rectangle.setId(e.getId()+"R");
        rectangle.setWidth(12);
        if (e instanceof Tour){
            rectangle.setHeight(2);
        }
        else{
            rectangle.setHeight(1);
        }

        rectangle.setFill(Color.GREEN);
        panneauJeu.getChildren().add(rectangle);
    }

    public void creerSprite(Entite e){

        if(e instanceof Ennemi){

            VueEnnemi vueEnnemi = new VueEnnemi(panneauJeu, env);
            vueEnnemi.construitSpriteEnnemi(e);
            ChangeListener<String> obs = new ObsDirectionEnnemi((Ennemi) e,panneauJeu);
            ((Ennemi) e).getDirectionP().addListener(obs);


        }else if (e instanceof Tour){
            VueTour vueTour = new VueTour(panneauJeu, env);
            vueTour.construitSpriteTour(e);
        }
        else {
            VueAllier vueAllier = new VueAllier(panneauJeu, env);
            vueAllier.construitSpriteAllier(e);
            ChangeListener<String> obsa = new ObsDirectionAllier((Allier) e,panneauJeu);
            ((Allier) e).getDirectionP().addListener(obsa);
        }

    }
}