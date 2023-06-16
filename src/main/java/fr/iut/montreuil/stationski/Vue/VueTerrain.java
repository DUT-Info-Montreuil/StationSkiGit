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
        this(env, root);
        this.intMapSelect = intMapSelect;
    }

    public VueTerrain(Environnement env, TilePane root){
        this.root = root;
        this.env = env;
    }

    public void construitMap(){

        URL urlIm = Main.class.getResource("/fr/iut/montreuil/stationski/images/TileSet_Final.png");
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
    public int[] getTableauTerrain() {
        int[] listeMap = new int[45 * 45];

        String path;
        if (this.intMapSelect == 0) path = "src/main/resources/fr/iut/montreuil/stationski/csv/TileSet_Final_CSV.csv";
        else if (this.intMapSelect == 1) path = "src/main/resources/fr/iut/montreuil/stationski/csv/TileSet2_Final_CSV.csv";
        else if (this.intMapSelect == 2) path = "src/main/resources/fr/iut/montreuil/stationski/csv/TileSet3_Final_CSV.csv";
        else path = "src/main/resources/fr/iut/montreuil/stationski/csv/TileSet4_Final_CSV.csv";

        String line = "";
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    listeMap[i] = Integer.parseInt(value) + 1;
                    i++;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listeMap;
    }

    public Environnement getEnv(){return this.env;}
    public TilePane getRoot(){return root;}
}
