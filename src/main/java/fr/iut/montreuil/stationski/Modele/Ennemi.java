package fr.iut.montreuil.stationski.Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Ennemi extends Entite {
    private int vitesse;
    private int butin;
    private Dijkstra dijkstra;
    public Ennemi (int pv, int posX, int posY, int vitesse, Environnement env, int butin, Dijkstra dijkstra){
        super(pv, posX, posY, env);
        this.vitesse=vitesse;
        this.butin = butin;
        this.dijkstra = dijkstra;
    }


    public void agit(){
        deplacement();
        //System.out.println("se deplace");
        this.prendDegats(1);
        //System.out.println("souffre");
    }

    public void deplacement (){
        //deplacement tres simple
        if (this.dijkstra.getParcours().size() > 0) {
            this.posX.setValue(((Sommet)this.dijkstra.getParcours().get(this.dijkstra.getParcours().size()-1)).getX()*16);
            this.posY.setValue(((Sommet)this.dijkstra.getParcours().get(this.dijkstra.getParcours().size()-1)).getY()*16);
            this.dijkstra.getParcours().remove(this.dijkstra.getParcours().size()-1);
        }
        else{
            super.setPV(0);
        }
    }



    public int getButin() {
        return butin;
    }



}