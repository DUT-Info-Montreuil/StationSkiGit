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
        this.listeTerrain = listeTerrain;
        this.nbSpawn = nbSpawn;
        this.source = source;
        this.cible= cible;

    }

    public Terrain (int largeur, int hauteur, int nbSpawn, int intMapSelect, ArrayList<Integer> listeTerrain){
        this.largeurCase = largeur;
        this.hauteurCase = hauteur;
        this.listeTerrain = listeTerrain;
        this.nbSpawn = nbSpawn;
        this.source=getSommetSource(intMapSelect);
        this.cible=getSommetCible((intMapSelect));
    }

    public ArrayList<Integer> getList(){
        return this.listeTerrain;
    }

    public Sommet getSommetSource(int intMapSelect){
        if(intMapSelect==0){
            return new Sommet(13,0, false);
        }
        else if (intMapSelect==1){
            return new Sommet(37, 0, false);
        }
        else if(intMapSelect==2){
            return new Sommet(13, 0, false);
        }
        else {//if (intMapSelect==3)
            return new Sommet(40, 0, false);
        }
    }
    public Sommet getSommetCible(int intMapSelect){
        if(intMapSelect==0){
            return new Sommet(25,44, false);
        }
        else if (intMapSelect==1){
            return new Sommet(31, 44, false);
        }
        else if(intMapSelect==2){
            return new Sommet(24, 44, false);
        }
        else {//if (intMapSelect==3)
            return new Sommet(36, 44, false);
        }
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
