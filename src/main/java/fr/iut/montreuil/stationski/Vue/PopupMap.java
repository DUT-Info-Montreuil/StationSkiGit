package fr.iut.montreuil.stationski.Vue;

import fr.iut.montreuil.stationski.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.Popup;

import java.net.URL;

public class PopupMap{
    private Popup popup;
    private Stage stage;

    public PopupMap(Stage stage){
       popup = new Popup();
       this.stage = stage;
        TilePane tp = new TilePane();


        Button map1 = new Button();
        map1.setGraphic(new ImageView(new Image(String.valueOf( Main.class.getResource("cahute.png")))));
        Button map2 = new Button("button");
        Button map3 = new Button("button");

        tp.getChildren().add(map1);
        tp.getChildren().add(map2);
        tp.getChildren().add(map3);
        this.popup.show(stage);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent e)
                    {
                        if (!popup.isShowing())
                            popup.show(stage);
                        else
                            popup.hide();
                    }
        };

        stage.show();


    }
}
