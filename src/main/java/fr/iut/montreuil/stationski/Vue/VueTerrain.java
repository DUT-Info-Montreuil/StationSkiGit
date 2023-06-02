package fr.iut.montreuil.stationski.Vue;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Environnement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.net.URL;

public class VueTerrain {
    private TilePane root;
    private Environnement env;

    public VueTerrain(Environnement env, TilePane root){
        this.root = root;
        this.env = env;
    }

    public void afficheMap(){
        root.setStyle("-fx-background-color:blue");

// 1 neige, 0 chemin ,  3 spawn , 4 objectif, 5 tour
        for (int row = 0; row<this.env.getTerrain().getList().size(); row++) {
            if (this.env.getTerrain().getList().get(row) == 1 || this.env.getTerrain().getList().get(row) == 3 || this.env.getTerrain().getList().get(row) == 5) {
                URL urlIm = Main.class.getResource("snow2.png");
                Image im = new Image(String.valueOf(urlIm));
                ImageView imageSnow = new ImageView();
                imageSnow.setImage(im);

                root.getChildren().add(imageSnow);

            } else if (this.env.getTerrain().getList().get(row) == 0 || this.env.getTerrain().getList().get(row) == 4) {
                URL urlIm = Main.class.getResource("snow01.png");
                Image im = new Image(String.valueOf(urlIm));
                ImageView imageSnow = new ImageView();
                imageSnow.setImage(im);

                root.getChildren().add(imageSnow);
            }

        }
    }
}
