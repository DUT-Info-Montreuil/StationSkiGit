package fr.iut.montreuil.stationski.Modele;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Terrain {
    private int largeurCase, hauteurCase;

    private ArrayList<Integer>listeTerrain;
    private int[] path;
    private Sommet source;
    private Sommet cible;


    public Terrain(int largeur, int hauteur){

        this.largeurCase = largeur;
        this.hauteurCase = hauteur;

        this.path = this.createTerrain();

        this.listeTerrain =    (ArrayList<Integer>) Arrays.stream(this.path).boxed().collect(Collectors.toList());


    }


    public ArrayList<Integer> getList(){
        return this.listeTerrain;
    }


    public int[] createTerrain(){
        int[] path = new int[largeurCase*hauteurCase];// Tableau 2D représentant la piste de ski
        Arrays.fill(path, 1);


        int ligne = 0;
        // Tirer un point aléatoire sur la première ligne
        Random random = new Random();
        int startX = random.nextInt(45);
        path[startX] = 0;


        int prevX = startX;
        int expand = (int) (Math.random()*4 + 2); // Nombre de cases à élargir
        for (int i = 1; i <= expand; i++) {
            if (prevX - i >= 0) {
                path[ prevX - i] = 0; // Élargir vers la gauche
            }
            if (prevX + i < 45) {
                path[ prevX + i] = 0; // Élargir vers la droite
            }
        }

        this.source = new Sommet(startX, 0, false);

        int direction = random.nextInt(3);
        if (direction==2) direction = 1;
        else direction= -1;


        // Générer les points de contrôle et relier les segments
        for (int y = 1; y <= 45; y++) {
            int tirage = random.nextInt(7);
            if(tirage==1)direction = -direction; //1/5eme de chance de changer de direction a chaque point de controle

            if(prevX <9){
                direction = 1;
            }
            if(prevX > 35){
                direction = -1;
            }//Permet de changer de direction si on se rapproche trop du bord


            int controlX = prevX + random.nextInt(6)*direction ; // Tirer un point de contrôle
            controlX = Math.max(0, Math.min(44, controlX)); // Limiter le point de contrôle aux limites du tableau
            path[ligne*45 + controlX] =0;

            // Relier les segments
            int minX = Math.min(prevX, controlX);
            int maxX = Math.max(prevX, controlX);
            for (int x = minX; x <= maxX; x++) {
                path[ligne*45 + x] = 0;
            }

            // Élargir la route
            expand = (int) (Math.random()*4 +2 ); // Nombre de cases à élargir
            for (int i = 1; i <= expand; i++) {
                if (controlX - i >= 0) {
                    path[ligne *45 + controlX - i] = 0; // Élargir vers la gauche
                }
                if (controlX + i < 45) {
                    path[ligne*45 + controlX + i] = 0; // Élargir vers la droite
                }
            }

            ligne++;
            prevX = controlX;

        }

        this.cible = new Sommet(prevX, 44, false);
        this.path = path;
        return path;
    }



    public int[] getPath(){return this.path;}
    public int getLargeurCase () { return this.largeurCase;}
    public int getHauteurCase() {return this.hauteurCase;}

    public ArrayList<Integer> getTerrain() { return this.listeTerrain;}
    public Sommet getCible (){return this.cible;}
    public Sommet getSource(){return this.source;}
}
