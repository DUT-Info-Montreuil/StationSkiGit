package fr.iut.montreuil.stationski.Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entite {

    private static int compteur = 0;
    private String id;
    private int pv;
    protected IntegerProperty posX;
    protected IntegerProperty posY;
    protected Environnement env;

    public Entite(int pv, int posX, int posY, Environnement env){
        this.posX = new SimpleIntegerProperty(posX);
        this.posY = new SimpleIntegerProperty(posY);
        this.pv=pv;
        this.env = env;
        this.id = "E"+compteur;
        compteur++;

    }


    public IntegerProperty getPosXP(){
        return posX;
    }
    public IntegerProperty getPosYP(){
        return posY;
    }

    public int getPosX() {
        return posX.getValue();
    }
    public int getPosY() {
        return posY.getValue();
    }
    public int getPV(){return this.pv;}
    public void setPV(int pv) {this.pv=pv;}
    public String getId(){
        return this.id;
    }




    public void prendDegats(int degat){
        this.pv-= degat;
    }

    public boolean estVivant (){
        return pv>0;
    }

    public abstract void agit();


}