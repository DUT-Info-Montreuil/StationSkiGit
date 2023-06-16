package fr.iut.montreuil.stationski.Modele;


import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;

import java.util.ArrayList;
import java.util.Arrays;

public class Terrain {
    private int largeurCase, hauteurCase;
    private ArrayList<Integer>listeTerrain;
    private Sommet source;
    private Sommet cible;

    public Terrain(int largeur, int hauteur,  Sommet source, Sommet cible, ArrayList<Integer> listeTerrain){
        this(largeur, hauteur);
        this.listeTerrain = listeTerrain;
        this.source = source;
        this.cible= cible;

    }

    public Terrain (int largeur, int hauteur,int intMapSelect, ArrayList<Integer> listeTerrain){
        this(largeur, hauteur);
        this.listeTerrain = listeTerrain;
        this.source=getSommetSource(intMapSelect);
        this.cible=getSommetCible((intMapSelect));
    }

    public Terrain(int largeur, int hauteur){
        this.largeurCase = largeur;
        this.hauteurCase = hauteur;
    }

    public ArrayList<Integer> getList(){
        return this.listeTerrain;
    }

    public Sommet getSommetSource(int intMapSelect){
        if(intMapSelect==0){
            return new Sommet(13,0, false);
        }
        else if (intMapSelect==1){
            return new Sommet(33, 0, false);
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

    public int getLargeurCase () { return this.largeurCase;}
    public int getHauteurCase() {return this.hauteurCase;}

    public ArrayList<Integer> getTerrain() { return this.listeTerrain;}
    public void setTerrain(ArrayList<Integer> listeTerrain){this.listeTerrain=listeTerrain;}
    public Sommet getCible (){return this.cible;}
    public Sommet getSource(){return this.source;}
    public void setSource (Sommet sommet) {this.source = sommet;}
    public void setCible (Sommet sommet) {this.cible = sommet;}

    public int[] getPath(){return new int[0];}
}
