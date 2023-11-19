package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.*;
import fr.iut.montreuil.stationski.Modele.Projectiles.*;
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

    public ListObsProj(Pane pane, Environnement env) {
        this.env = env;
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends Projectile> c) {
        while (c.next()) {
            for (Projectile p : c.getAddedSubList()) {
                ChangeListener<Number> listenProjectileX = ((obs, old, nouv) -> {
                    for (Ennemi e : this.env.getVague().getListEnnemis()) {
                        if (Math.abs(p.getPosX() - e.getPosX()) < 5 && Math.abs(p.getPosY() - e.getPosY()) < 5) {
                            e.prendDegats(p.getPtsAttaque());
                        }
                    }
                }
                );
                p.getPosXP().addListener(listenProjectileX);
                creerSprite(p);
            }

            for (Projectile p : c.getRemoved()) {
                Node sprite = pane.lookup("#" + p.getIdProj());
                pane.getChildren().remove(sprite);

            }
        }
    }

    public void creerSprite(Projectile p) {
        URL urlIm;

        if (p instanceof ProjectileCanonEau) {
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/jetEau.png");
        } else if (p instanceof ProjectileTelesiege) {
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/cabineTelesiege.png");
        } else if (p instanceof ProjectileTeleski) {
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/cabineTeleski2.png");
        }
        else if (p instanceof ProjectileAllier){
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/balle.png");
        }
        else {
            urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/projneige.png");
        }

        Image im = new Image(String.valueOf(urlIm));
        ImageView imageEn = new ImageView();
        imageEn.setImage(im);
        imageEn.translateXProperty().bind(p.getPosXP());
        imageEn.translateYProperty().bind(p.getPosYP());

        imageEn.setId(p.getIdProj());
        pane.getChildren().add(imageEn);

    }
}




