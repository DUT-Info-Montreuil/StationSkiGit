package fr.iut.montreuil.stationski.Modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Acteur extends Entite{
    private Environnement env;
    private IntegerProperty pv;
    private int pvMax;
    private DoubleProperty pourcentPV;
    public Acteur(int pv, int posX, int posY, Environnement env){
        super(posX, posY);
        this.env = env;
        this.pv= new SimpleIntegerProperty(pv);
        this.pvMax= pv;
        this.pourcentPV = new SimpleDoubleProperty(1);
    }


    public int getPV(){return this.pv.getValue();}
    public void setPV(int pv) {
        this.pv.setValue(pv);
    }
    public IntegerProperty pvProperty (){return this.pv;}
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
    public Environnement getEnv(){return this.env;}
    public Acteur getType(){return this;}
}
