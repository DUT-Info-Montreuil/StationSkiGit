package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Ennemis.SkieurBasique;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Projectiles.ProjectileCanonEau;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
                ChangeListener<Number> listenProjectileX = ((obs, old, nouv) -> {
                    for (Ennemi e : this.env.getVague().getListEnnemis()) {
                        if (Math.abs(p.getPosX() - e.getPosX()) < 5 && Math.abs(p.getPosY() - e.getPosY()) < 5)
                            e.prendDegats(p.getPtsAttaque());
                    }
                }
                );
                p.posXP().addListener(listenProjectileX);
                creerSprite(p);
            }

            for(Projectile p : c.getRemoved()) {
                Node sprite = pane.lookup("#" + p.getIdProj());
                pane.getChildren().remove(sprite);

            }
        }
    }

    public void creerSprite(Projectile p){


        if (p instanceof ProjectileCanonEau){
            Circle cercleEau = new Circle(0,0,3, Color.BLUE);
            cercleEau.translateXProperty().bind(p.posXP());
            cercleEau.translateYProperty().bind(p.posYP());
            cercleEau.setId(p.getIdProj());
            pane.getChildren().add(cercleEau);
        }
        else{
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

}


