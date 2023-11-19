package fr.iut.montreuil.stationski.Modele;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Projectile extends Entite{
    private Acteur cible;
    private int ptsAttaque;
    public Projectile(Acteur cible,  int posX, int posY, int ptsAttaque){
        super(posX, posY);
        this.cible = cible;
        this.ptsAttaque = ptsAttaque;
    }
    public int getPtsAttaque(){return this.ptsAttaque;}
    public Acteur getCible() {
        return this.cible;
    }

    public String getIdProj(){return this.getId();}

    public abstract boolean attaque();

    public void agit(){
        attaque();
    }

}
