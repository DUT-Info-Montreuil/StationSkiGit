package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Ennemis.SkieurBasique;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectile;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;


public class ListObsProj implements ListChangeListener<Projectile> {


    private Environnement env;
    private Pane pane;

    public ListObsProj(Pane pane, Environnement env){
        this.env = env;
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends Projectile> c) {
        while(c.next()){

            for(Projectile p : c.getAddedSubList()){
                creerSprite(p);
            }

            for(Projectile p : c.getRemoved()) {
                Node sprite = pane.lookup("#" + p.getIdProj());
                pane.getChildren().remove(sprite);

            }
        }
    }

    public void creerSprite(Projectile p){
        URL urlIm;


        urlIm = Main.class.getResource("skieur1.png");


        Image im= new Image(String.valueOf(urlIm));
        ImageView imageEn = new ImageView();
        imageEn.setImage(im);

        imageEn.translateXProperty().bind(p.posXP());
        imageEn.translateYProperty().bind(p.posYP());

        imageEn.setId(p.getIdProj());
        pane.getChildren().add(imageEn);
    }
}