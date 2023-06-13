package fr.iut.montreuil.stationski.Modele;


import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;

import java.util.ArrayList;
import java.util.Arrays;

public class Terrain {
    private int largeurCase, hauteurCase;

    private ArrayList<int[]> listeSpawn;

    private ArrayList<Integer>listeTerrain;

    private int nbSpawn;
    private Sommet source;

    private Sommet cible;


    public Terrain(int largeur, int hauteur, int nbSpawn, Sommet source, Sommet cible, ArrayList<Integer> listeTerrain){

        this.largeurCase = largeur;
        this.hauteurCase = hauteur;
        //this.objectif = createObj();
        this.listeTerrain = listeTerrain;
        this.nbSpawn = nbSpawn;
        this.source = source;
        this.cible= cible;
        //this.listeSpawn = createSpawn(nbSpawn);

        /**
        this.listeTerrain = new ArrayList<Integer>(Arrays.asList(
                1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0, 0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0, 0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                0,0,0,1,1,1,1,1,1,1,1,0,0,0,1,0, 0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,
                0,0,0,1,1,1,1,1,1,1,1,0,0,0,1,1, 1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,
                0,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1, 1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,
                1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1, 1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,
                1,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1, 1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,
                1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1, 1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1, 1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1, 1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1, 1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,

                1,1,1,1,1,1,0,0,0,0,0,1,1,0,0,1, 1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,0,0,0,0,0,0,1,1,0,0,0, 0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,0,0,0,1,1,1,1,1,0,0,0, 0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,0, 0,0,0,0,1,1,0,1,1,1,1,1,1,1,1,1,
                1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1, 1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,
                1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
        ));
        */
        //this.listeTerrain = new ArrayList<Integer>(Arrays.asList(createTableauTerrain()));
    }


    public ArrayList<Integer> getList(){
        return this.listeTerrain;
    }

    public int[] createObj(){

        int posY, posX;
        int cote = (int)(Math.random()*4)+1;
        switch(cote){
            case 1 :
                posY = 0;
                posX = (int)(Math.random()*largeurCase*16);
                break;
            case 2 :
                posY = (int)(Math.random()* hauteurCase*16);
                posX = hauteurCase*16;
                break;

            case 3:
                posY = hauteurCase*16;
                posX = (int)(Math.random()*largeurCase*16);
                break;
            case 4 :

                posY = (int)(Math.random()* hauteurCase*16);
                posX = 0;
                break;

            default :
                posY = 0;
                posX = 0;
                break;
        }

        int[]obj = {posX, posY};
        return obj;
    }


    public ArrayList<int[]> createSpawn(int nbSpawn){
        ArrayList<int[]> spawns = new ArrayList<int[]>();
        for(int i =0; i<nbSpawn; i++){
            int posY, posX;
            int cote = (int)(Math.random()*4)+1;
            switch(cote){
                case 1 :
                    posY = 0;
                    posX = (int)(Math.random()*largeurCase);
                    break;
                case 2 :
                    posY = (int)(Math.random()* hauteurCase);
                    posX = hauteurCase;
                    break;

                case 3:
                    posY = largeurCase;
                    posX = (int)(Math.random()*largeurCase);
                    break;
                case 4 :

                    posY = (int)(Math.random()* hauteurCase);
                    posX = 0;
                    break;

                default :
                    posY = 0;
                    posX = 0;
                    break;
            }
            int[] spawn = new int[] { posX, posY};
            spawns.add(spawn);
        }

        return spawns;
    }


    public void genererEmplacementTour(){

    }


    public Integer[] createTableauTerrain(){
        Integer[] tableTerrain = new Integer[largeurCase* hauteurCase];
        for(int indice = 0; indice<this.hauteurCase*this.largeurCase; indice++){

            tableTerrain[indice] = ((int)(Math.random()*4)/3);

        }

/*
        for(int i=0;i<this.listeSpawn.size(); i++){
            tableTerrain[listeSpawn.get(i)[1]*16 + listeSpawn.get(i)[0] ] = 3;
        }
*/
        tableTerrain[this.cible.getY() + this.cible.getX()] = 4;

        return tableTerrain;
    }

    public int getLargeurCase () { return this.largeurCase;}
    public int getHauteurCase() {return this.hauteurCase;}

    public ArrayList<Integer> getTerrain() { return this.listeTerrain;}
    public void setTerrain(ArrayList<Integer> listeTerrain){this.listeTerrain=listeTerrain;}
    public Sommet getCible (){return this.cible;}
    public Sommet getSource(){return this.source;}
    public void setSource (Sommet sommet) {this.source = sommet;}
    public void setCible (Sommet sommet) {this.cible = sommet;}
}
