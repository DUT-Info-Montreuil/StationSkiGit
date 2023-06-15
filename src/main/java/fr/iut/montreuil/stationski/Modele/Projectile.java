package fr.iut.montreuil.stationski.Modele;

import javafx.beans.property.SimpleIntegerProperty;

public class Projectile {
    private Entite cible;
    private SimpleIntegerProperty posX;
    private SimpleIntegerProperty posY;
    private int ptsAttaque;
    private static int vitesse = 1;
    private String id;
    private static int compteur = 0;

    public Projectile(Entite cible,  int posX, int posY, int ptsAttaque){
        this.cible = cible;
        this.posX = new SimpleIntegerProperty(posX);
        this.posY = new SimpleIntegerProperty(posY);
        this.ptsAttaque = ptsAttaque;
        this.id = "P"+ compteur;
        compteur++;
    }
    public int getPtsAttaque(){return this.ptsAttaque;}

    public int getVitesse(){return vitesse;}

    public Entite getCible() {
        return cible;
    }
    public int getPosX() {
        return posX.getValue();
    }
    public void setPosX(int posX) {
        this.posX.set(posX);
    }
    public SimpleIntegerProperty posXP() {
        return posX;
    }
    public void setPosY(int posY) {
        this.posY.set(posY);
    }
    public int getPosY() {
        return posY.getValue();
    }
    public SimpleIntegerProperty posYP() {
        return posY;
    }

    public String getIdProj(){return this.id;}


    public boolean attaque(){
        if(!this.cible.estVivant()) {
            return true;
        }

        int dx, dy;
        if(this.cible.getPosX()-this.getPosX() >0)dx = 1;
        else dx = -1;
        if(this.cible.getPosY()-this.getPosY() >0)dy = 1;
        else dy = -1;

        this.posX.setValue(this.getPosX() + (dx * vitesse));
        this.posY.setValue(this.getPosY() + (dy * vitesse));



        //
        if( (this.getPosX() <= this.cible.getPosX() && this.getPosX() >= (this.cible.getPosX()-8)) && (this.getPosY() <= this.cible.getPosY() && this.getPosY() >= (this.cible.getPosY()-8)) ){
            this.cible.prendDegats(this.ptsAttaque);
            return true;
        }
        return false;

    }


}
