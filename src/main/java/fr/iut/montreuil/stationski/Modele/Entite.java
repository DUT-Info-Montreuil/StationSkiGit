package fr.iut.montreuil.stationski.Modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entite {

    private static int compteur = 0;
    private String id;
    private IntegerProperty pv;
    protected IntegerProperty posX;
    protected IntegerProperty posY;
    protected Environnement env;
    private int pvMax;
    private DoubleProperty pourcentPV;

    public Entite(int pv, int posX, int posY, Environnement env){
        this.posX = new SimpleIntegerProperty(posX);
        this.posY = new SimpleIntegerProperty(posY);
        this.pv= new SimpleIntegerProperty(pv);
        this.env = env;
        this.id = "E"+compteur;
        this.pvMax= pv;
        this.pourcentPV = new SimpleDoubleProperty(1);
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
    public int getPV(){return this.pv.getValue();}
    public void setPV(int pv) {
        this.pv.setValue(pv);
    }
    public IntegerProperty pvProperty (){return this.pv;}
    public String getId(){
        return this.id;
    }
    public void setPourcentPV (){
        this.pourcentPV.setValue(((double)(this.pv.getValue())/((double)this.pvMax)));
    }

    public DoubleProperty pourcentPVProperty(){
        return this.pourcentPV;
    }

    public void prendDegats(int degat){

        this.pv.setValue(this.pv.getValue()-degat);
        int pv = this.pv.getValue()-degat;
        setPourcentPV();
    }
    public int getPVMax(){return this.pvMax;}
    public boolean estVivant (){
        return this.pv.getValue()>0;
    }

    public abstract void agit();


}
