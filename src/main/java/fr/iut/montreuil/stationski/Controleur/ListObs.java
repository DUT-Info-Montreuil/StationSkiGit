package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
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