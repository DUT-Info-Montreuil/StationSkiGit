package fr.iut.montreuil.stationski.Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entite {

    private static int compteur = 0;
    private String id;
    protected IntegerProperty posX;
    protected IntegerProperty posY;
    protected Environnement env;

    public Entite( int posX, int posY){
        this.posX = new SimpleIntegerProperty(posX);
        this.posY = new SimpleIntegerProperty(posY);
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
    public void setPosX(int posX) {this.posX.setValue(posX);}
    public void setPosY(int posY) {this.posY.setValue(posY);}
    public String getId(){
        return this.id;
    }

    public abstract void agit();


}
