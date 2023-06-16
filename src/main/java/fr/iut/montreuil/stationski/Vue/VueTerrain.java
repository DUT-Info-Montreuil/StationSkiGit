package fr.iut.montreuil.stationski.Vue;

import fr.iut.montreuil.stationski.Main;
import fr.iut.montreuil.stationski.Modele.Environnement;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class VueTerrain {
    private TilePane root;
    private Environnement env;
    private int intMapSelect;
    public VueTerrain(Environnement env, TilePane root, int intMapSelect){
        this.root = root;
        this.env = env;
        this.intMapSelect=intMapSelect;
    }

    public void afficheMap(){

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
    
    public ArrayList<Integer> créerListeTerrain (){
        int[] listeMap = this.getTableauTerrain();
        ArrayList<Integer> arrayListMap = (ArrayList<Integer>) Arrays.stream(listeMap).boxed().collect(Collectors.toList());
        for(int i =0; i<arrayListMap.size(); i++){
            int valeurCase = arrayListMap.get(i);
            if(valeurCase==1) arrayListMap.set(i,1);
            else if(valeurCase==2 ||  valeurCase==3 || valeurCase==4 || valeurCase==46) arrayListMap.set(i,0);
            else arrayListMap.set(i,2);
        }
        return arrayListMap;
    }
    public int[] getTableauTerrain(){
        int[] listeMap = new int[45*45];

        URL url= Main.class.getResource("/fr/iut/montreuil/stationski/TileSet_Final_CSV.csv");
        String path;

        if(this.intMapSelect==0) path = "C:/Users/matth/Documents/GitHub/StationSkiGit/target/classes/fr/iut/montreuil/stationski/TileSet_Final_CSV.csv";
        else if (this.intMapSelect==1) path = "C:/Users/matth/Documents/GitHub/StationSkiGit/target/classes/fr/iut/montreuil/stationski/TileSet2_Final_CSV.csv";
        else if (this.intMapSelect==2) path = "C:/Users/matth/Documents/GitHub/StationSkiGit/target/classes/fr/iut/montreuil/stationski/TileSet3_Final_CSV.csv";
        else path = "C:/Users/matth/Documents/GitHub/StationSkiGit/target/classes/fr/iut/montreuil/stationski/TileSet4_Final_CSV.csv";
        String line="";
        int i=0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line=br.readLine())!= null){
                String[] values = line.split(",");
                for( String value : values){
                    listeMap[i]=Integer.parseInt(value)+1;
                    i++;
                }

            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        /**
        // fichier csv
        int[] listeMap = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 2, 3, 3, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 4, 3, 3, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 140, 141, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 2, 3, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 20, 21, 1, 1, 1, 185, 186, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 65, 66, 11, 12, 12, 12, 13, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 110, 111, 19, 57, 56, 56, 15, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 2, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 11, 12, 12, 12, 12, 12, 58, 56, 57, 56, 15, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 2, 46, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 19, 56, 57, 56, 57, 56, 57, 56, 57, 57, 15, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 2, 46, 46, 46, 1, 1, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 19, 57, 56, 56, 57, 56, 56, 56, 56, 56, 15, 1,
                5, 6, 1, 1, 1, 1, 1, 1, 1, 46, 3, 3, 4, 3, 3, 1, 46, 46, 3, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 18, 17, 17, 17, 17, 17, 17, 17, 17, 17, 16, 1,
                50, 51, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 46, 4, 4, 2, 2, 3, 3, 3, 3, 46, 46, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 22, 23, 24,
                95, 96, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 4, 46, 4, 4, 2, 3, 3, 3, 2, 3, 3, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 7, 8, 9, 10, 67, 68, 69,
                140, 141, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 1, 46, 4, 3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 52, 53, 54, 55, 112, 113, 114,
                185, 186, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 3, 3, 2, 46, 1, 1, 1, 1, 1, 1, 1, 1, 97, 98, 99, 100, 157, 158, 159,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 46, 2, 3, 46, 1, 1, 1, 1, 1, 1, 1, 1, 142, 143, 144, 145, 140, 141, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 4, 46, 3, 3, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 185, 186, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 46, 1, 1, 1, 1, 1, 46, 3, 3, 46, 3, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 1, 1, 46, 46, 46, 46, 2, 2, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 3, 2, 3, 3, 3, 3, 3, 3, 2, 46, 3, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 46, 1, 4, 4, 3, 3, 46, 46, 46, 3, 46, 1, 1, 46, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 3, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 4, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 4, 4, 46, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 4, 3, 2, 2, 46, 1, 46, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 46, 1, 46, 3, 3, 3, 3, 2, 3, 3, 46, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 46, 46, 46, 3, 46, 2, 2, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 2, 2, 2, 3, 3, 46, 46, 46, 3, 3, 3, 3, 3, 3, 3, 3, 1, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 2, 4, 4, 4, 4, 2, 4, 4, 2, 4, 3, 3, 2, 46, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 2, 4, 3, 3, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 3, 3, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 3, 2, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 1, 1, 46, 3, 3, 2, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 46, 46, 46, 46, 3, 3, 46, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 3, 3, 3, 46, 46, 46, 1, 1, 46, 46, 3, 46, 2, 2, 3, 2, 46, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 3, 2, 2, 4, 3, 3, 3, 2, 2, 46, 3, 3, 2, 46, 46, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 46, 3, 2, 3, 46, 4, 1, 1, 1, 46, 46, 1, 1, 1, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 46, 46, 3, 3, 2, 3, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 20, 21, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 46, 3, 46, 2, 3, 3, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 22, 23, 65, 66, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 46, 46, 46, 46, 3, 3, 46, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 67, 68, 110, 111, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 2, 3, 2, 2, 46, 46, 46, 3, 1, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 112, 113, 114, 20, 21, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 46, 46, 4, 46, 46, 2, 3, 46, 46, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 157, 158, 159, 65, 66, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 46, 4, 3, 4, 4, 4, 2, 3, 3, 46, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 110, 111, 1, 1,
                140, 141, 20, 21, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 3, 2, 2, 3, 3, 3, 2, 2, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                185, 186, 65, 66, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 3, 3, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                140, 141, 110, 111, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 46, 2, 3, 46, 2, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                185, 186, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 46, 4, 2, 2, 3, 4, 46, 46, 46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        */

         return listeMap;
    }
}
