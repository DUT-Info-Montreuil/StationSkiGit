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
    private ArrayList<Integer> listeMap;
    private static int[] terrain = new int[45*45];

    public VueTerrainAleatoire(Environnement env, TilePane root){
        this.root = root;
        this.env = env;

    }

    public void afficheMap(){

        //root.setStyle("-fx-background-color:blue");
        URL urlIm = Main.class.getResource("TileSet_Final.png");
        Image im = new Image(String.valueOf(urlIm));
        // 1 neige, 0 chemin ,  3 spawn , 4 objectif, 5 tour
        int[] listeMap = this.getTableauTerrain();
        for(int i=0; i<listeMap.length; i++) {

            ImageView imageTile = new ImageView();
            imageTile.setImage(im);

            Rectangle2D rect = new Rectangle2D(((listeMap[i]-1)%45)*16, ((listeMap[i]-1)/45)*16, 16, 16);
            imageTile.setViewport(rect);

            root.getChildren().add(imageTile);
        }

    }



    public int[] getTableauTerrain(){
        int[][] terrain2d = this.create2Dterrain();
        int[] terrain1D = new int[terrain2d.length* terrain2d.length];
        for(int i = 0; i<terrain2d.length; i++){

            for(int j =0; j<terrain2d[i].length; j++){

                terrain1D[i* terrain2d.length + j] = terrain2d[i ][j];

            }
        }


       return terrain1D;
    }

    public static ArrayList<Integer> créerListeTerrain(){
        int[] terrain1d = new int[terrain.length];
        for(int i =0; i< terrain.length; i++){
            terrain1d[i] = terrain[i];
            //System.out.println(terrain1d[i]);
        }
        return (ArrayList<Integer>) Arrays.stream(terrain1d).boxed().collect(Collectors.toList());
    }


    public int[][] create2Dterrain(){
        int[][] path = new int[45][45]; // Tableau 2D représentant la piste de ski

        // Tirer un point aléatoire sur la première ligne
        Random random = new Random();
        int startX = random.nextInt(45);
        path[0][startX] = 1;

        int prevX = startX;
        int expand = (int) (Math.random()*4 + 2); // Nombre de cases à élargir
        for (int i = 1; i <= expand; i++) {
            if (prevX - i >= 0) {
                path[0][prevX - i] = 1; // Élargir vers la gauche
            }
            if (prevX + i < 45) {
                path[0][prevX + i] = 1; // Élargir vers la droite
            }
        }

        int direction = random.nextInt(3);
        if (direction==2) direction = 1;
        else direction= -1;


        // Générer les points de contrôle et relier les segments
        for (int y = 1; y < 45; y++) {
            int tirage = random.nextInt(6);
            if(tirage==1)direction = -direction; //1/5eme de chance de changer de direction a chaque point de controle

            if(prevX <5){
                direction = 1;
            }
            if(prevX > 39){
                direction = -1;
            }//Permet de changer de direction si on se rapproche trop du bord


            int controlX = prevX + random.nextInt(10)*direction ; // Tirer un point de contrôle
            controlX = Math.max(0, Math.min(44, controlX)); // Limiter le point de contrôle aux limites du tableau
            path[y][controlX] = 1;

            // Relier les segments
            int minX = Math.min(prevX, controlX);
            int maxX = Math.max(prevX, controlX);
            for (int x = minX; x <= maxX; x++) {
                path[y][x] = 1;
            }

            // Élargir la route
            expand = (int) (Math.random()*4 + 2); // Nombre de cases à élargir
            for (int i = 1; i <= expand; i++) {
                if (controlX - i >= 0) {
                    path[y][controlX - i] = 1; // Élargir vers la gauche
                }
                if (controlX + i < 45) {
                    path[y][controlX + i] = 1; // Élargir vers la droite
                }
            }

            prevX = controlX;

        }

        return path;
    }



}
