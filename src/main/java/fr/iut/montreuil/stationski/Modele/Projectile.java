package fr.iut.montreuil.stationski.Modele;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Projectile {
    private SimpleIntegerProperty posX;
    private SimpleIntegerProperty posY;
    private int ptsAttaque;
    private static int vitesse = 3;
    private String id;
    private static int compteur = 0;

    public Projectile(int posX, int posY, int ptsAttaque){
        this.posX = new SimpleIntegerProperty(posX+10);
        this.posY = new SimpleIntegerProperty(posY);
        this.ptsAttaque = ptsAttaque;
        this.id = "P"+ compteur;
        compteur++;
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


    public abstract boolean attaque();
    public int getPtsAttaque(){return this.ptsAttaque;}

}