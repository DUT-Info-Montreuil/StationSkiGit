package fr.iut.montreuil.stationski.Controleur;

import fr.iut.montreuil.stationski.CreationMap.CreationMap;
import fr.iut.montreuil.stationski.CreationMap.Param;
import fr.iut.montreuil.stationski.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMapCreator implements Initializable {

        @FXML
        private TilePane tuiles;
        @FXML
        private TilePane grid;

        private static int type = 2;

        private CreationMap g;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            this.g = new CreationMap(Param.W, Param.H);
            initDessin();
            grid.setMouseTransparent(true);

            System.out.println("UI faite");
        }

        private void initDessin() {
            g.construit();
            URL urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/TileSet_Final.png");
            Image im = new Image(String.valueOf(urlIm));
            // 1 neige, 0 chemin ,  3 spawn , 4 objectif, 5 tour

            for(int i=0; i<g.getTuile().size(); i++) {
                ImageView imageTile = new ImageView();
                imageTile.setImage(im);
                Rectangle2D rect = new Rectangle2D(((g.getTuile().get(i))%45)*16, ((g.getTuile().get(i))/45)*16, 16, 16);

                Rectangle rectangle = new Rectangle(rect.getMinX()- Param.STROKE, rect.getMinY()- Param.STROKE, rect.getWidth()- Param.STROKE, rect.getHeight()- Param.STROKE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(0.2);
                rectangle.setFill(Color.TRANSPARENT);


                imageTile.setOnMouseClicked(e -> {
                    int x = (int) (e.getX() /16);
                    int y = (int) (e.getY() / 16);
                    this.g.getTuile().set(x + 45*y, type);
                    imageTile.setViewport(new Rectangle2D(((g.getTuile().get(x + 45*y)%45)*16), ((g.getTuile().get(x + 45*y)/45)*16), 16, 16));
                    }
                );

                imageTile.setViewport(rect);
                tuiles.getChildren().add(imageTile);
                grid.getChildren().add(rectangle);
            }
        }

        @FXML
        void actionQuitter(ActionEvent event) {
            System.out.println("Bouton Quitter");
            Platform.exit();
        }

}
