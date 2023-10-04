package fr.iut.montreuil.stationski.Modele;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Projectile extends Entite{
    private Acteur cible;
    private int ptsAttaque;
    private static int vitesse = 1;
    private String id;
    private static int compteur = 0;

    public Projectile(Acteur cible,  int posX, int posY, int ptsAttaque){
        super(posX, posY);
        this.cible = cible;
        this.ptsAttaque = ptsAttaque;
        this.id = "P"+ compteur;
        compteur++;
    }
    public int getPtsAttaque(){return this.ptsAttaque;}
    public Acteur getCible() {
        return this.cible;
    }

    public String getIdProj(){return this.id;}

    public abstract boolean attaque();

    public void agit(){
        attaque();
    }

}
