package fr.iut.montreuil.stationski.Vue;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Environnement;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class VueTerrainAleatoire {
    private TilePane root;
    private Environnement env;

    private  int[] terrainText;




    public VueTerrainAleatoire(Environnement env, TilePane root){
        this.root = root;
        this.env = env;
        this.terrainText = new int[this.env.getTerrain().getHauteurCase() * this.env.getTerrain().getLargeurCase()];
        this.copieTerrain();
        this.generateTree();
        this.chooseTexture();

    }

    public void afficheMap(){

        //root.setStyle("-fx-background-color:blue");
        URL urlIm = Main.class.getResource("TileSet_Final.png");
        Image im = new Image(String.valueOf(urlIm));
        // 1 neige, 0 chemin ,  3 spawn , 4 objectif, 5 tour
        //int[] listeMapTab = this.env.getTerrain().;
            int[] listeMap = terrainText;
        //for(int i=0; i<listeMapTab.length; i++) {
        for(int i=0; i< listeMap.length; i++){
            ImageView imageTile = new ImageView();
            imageTile.setImage(im);

            //Rectangle2D rect = new Rectangle2D(((listeMapTab[i]-1)%45)*16, ((listeMapTab[i]-1)/45)*16, 16, 16);
            Rectangle2D rect = new Rectangle2D(((listeMap[i]-1)%45)*16, ((listeMap[i]-1)/45)*16, 16, 16);
            imageTile.setViewport(rect);

            root.getChildren().add(imageTile);
        }

    }

    public void copieTerrain(){
        for(int i = 0; i<terrainText.length; i++){
            this.terrainText[i] = this.env.getTerrain().getPath()[i];
        }
    }

    public void generateTree(){
        Random random = new Random();
        for(int i=0; i< 45*45; i++){
            if(terrainText[i] ==1){
                if(random.nextInt(200) == 1){
                    if(i-45*5 / 5 > 0){ //verifie inbounds de if suivant
                        for(int j = 0; j<4; j++) {
                            if (terrainText[i + j] == 1 && terrainText[i - 45 * j] == 1 && terrainText[(i + 1) - 45 * j] == 1 && terrainText[i + 45 * 2] == 1 && terrainText[i - j + (int) (j / 2)] == 1) {//laisse de l'espace pour les tours
                                terrainText[i] = 95;//bas gauche
                                terrainText[i + 1] = 96;//bas droite
                                terrainText[i - 45] = 50;//milieu
                                terrainText[i - 45 + 1] = 51;
                                terrainText[i - 45 * 2] = 5;//haut gauche
                                terrainText[i - 45 * 2 + 1] = 6;
                            }
                        }
                    }
                }
            }
        }
    }


    public void chooseTexture(){
        Random random = new Random();
        for(int i=0; i< 45*45; i++) {
            if(terrainText[i] ==0) {
                int text = random.nextInt(20);
                if (text <= 3) this.terrainText[i] = 46;
                if (text <= 8) this.terrainText[i] = 4;
                if (text <= 14) this.terrainText[i] = 3;
                else this.terrainText[i] = 2;
            }
        }
    }



}
