package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import fr.iut.montreuil.stationski.Modele.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

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
                ChangeListener<Number> listenPV = (
                        (obs, old, nouv) -> {if (nouv.intValue()>0 && old.intValue()==e.getPVMax()) creerBarreDeVie(e);}
                );
                e.pvProperty().addListener(listenPV);
                creerSprite(e);
            }

            for(Entite  e : c.getRemoved()){
                Node sprite = panneauJeu.lookup("#" + e.getId());
                panneauJeu.getChildren().remove(sprite);
                sprite = panneauJeu.lookup("#"+e.getId()+"R");
                panneauJeu.getChildren().remove(sprite);

            }
        }
    }
    public void creerBarreDeVie (Entite e){
        Rectangle rectangle = new Rectangle();
        ChangeListener<Number> listenerX = (
                (obs, old, nouv) -> {rectangle.setTranslateX(nouv.floatValue());}
        );
        ChangeListener<Number> listenerY = (
                (obs, old, nouv) -> {rectangle.setTranslateY((nouv.floatValue()) -5);}
        );
        ChangeListener<Number> listenPVActeur = (
                (obs, old, nouv) -> {rectangle.setWidth(Math.floor(nouv.floatValue()*12));
                    System.out.println(rectangle.getWidth());}
        );
        e.getPosXP().addListener(listenerX);
        e.getPosYP().addListener(listenerY);
        e.pourcentPVProperty().addListener(listenPVActeur);
        rectangle.setId(e.getId()+"R");
        rectangle.setWidth(12);
        rectangle.setHeight(1);
        rectangle.setFill(Color.GREEN);
        panneauJeu.getChildren().add(rectangle);
    }
    public void creerSprite(Entite e){
        URL urlIm;
        if(e instanceof Ennemi){
            if (e instanceof SkieurBasique) {
                urlIm = Main.class.getResource("skieur1.png");
            }
            else {
                urlIm = Main.class.getResource("skieur1.png");
            }




            Image im= new Image(String.valueOf(urlIm));
            ImageView imageEn = new ImageView();
            imageEn.setImage(im);

            imageEn.translateXProperty().bind(e.getPosXP());
            imageEn.translateYProperty().bind(e.getPosYP());

            imageEn.setId(e.getId());
            panneauJeu.getChildren().add(imageEn);


        }else if (e instanceof Tour){
            urlIm = Main.class.getResource("watertower.png");
            Image im= new Image(String.valueOf(urlIm));
            ImageView imageEn = new ImageView();
            imageEn.setImage(im);
            imageEn.translateXProperty().bind(e.getPosXP());
            imageEn.translateYProperty().bind(e.getPosYP());
            imageEn.setId(e.getId());
            panneauJeu.getChildren().add(imageEn);
        }


    }
}
