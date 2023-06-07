package fr.iut.montreuil.stationski.Modele;

import fr.iut.montreuil.stationski.Modele.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Ennemi extends Entite {
    private int vitesse;
    private int butin;
    private Dijkstra dijkstra;
    private int taille;
    protected Vague vague;

    public Ennemi (int pv, int posX, int posY, int vitesse, Environnement env, int butin, Dijkstra dijkstra, Vague vague, int taille){
        super(pv, posX, posY, env);
        this.vague = vague;
        this.taille = taille;
        this.vitesse=vitesse;
        this.butin = butin;
        this.dijkstra = dijkstra;
    }


    public void agit(){
       // if(env.getnbTour()%vitesse == 0) {
        for (int v = 0; v <vitesse; v++) {
            deplacement();
        }
       // }
    }

    public void deplacement (){
        //deplacement tres simple
        if (this.dijkstra.getParcours().size() > 0) {
            //this.posX.setValue(((Sommet)this.dijkstra.getParcours().get(this.dijkstra.getParcours().size()-1)).getX()*16);
            //this.posY.setValue(((Sommet)this.dijkstra.getParcours().get(this.dijkstra.getParcours().size()-1)).getY()*16);

            Sommet sommetCible = this.dijkstra.getParcours().get(this.dijkstra.getParcours().size()-1);

            int sommetX = sommetCible.getX()*16;
            int sommetY = sommetCible.getY()*16;

            // Parcours pixel par pixel aléatoire du terrain
            if(Math.random()*1>0.5 && this.posX.getValue()!=sommetX){
                if (sommetX>this.posX.getValue()){
                    this.posX.setValue(this.posX.getValue()+1);
                }
                else{
                    this.posX.setValue(this.posX.getValue()-1);
                }
            }
            else if (Math.random()*1>0.8 && this.posX.getValue()!=sommetX && this.posY.getValue()!=sommetY){ // Aller en diagonale
                if (sommetX>this.posX.getValue() && sommetY>this.posY.getValue()){
                    this.posX.setValue(this.posX.getValue()+1);
                    this.posY.setValue(this.posY.getValue()+1);
                }
                else if (sommetX<this.posX.getValue() && sommetY>this.posY.getValue()){
                    this.posX.setValue(this.posX.getValue()-1);
                    this.posY.setValue(this.posY.getValue()+1);
                }
                else if (sommetX<this.posX.getValue() && sommetY<this.posY.getValue()){
                    this.posX.setValue(this.posX.getValue()-1);
                    this.posY.setValue(this.posY.getValue()-1);
                }
                else{
                    this.posX.setValue(this.posX.getValue()+1);
                    this.posY.setValue(this.posY.getValue()-1);
                }
            }
            else if (this.posY.getValue()!=sommetY){
                if (sommetY>this.posY.getValue()){
                    this.posY.setValue(this.posY.getValue()+1);
                }
                else{
                    this.posY.setValue(this.posY.getValue()-1);
                }
            }


            // Si on arrive au point du sommet cible, on passe au prochain sommet cible
            if (sommetX==this.posX.getValue() && sommetY==this.posY.getValue())
                this.dijkstra.getParcours().remove(this.dijkstra.getParcours().size()-1);
        }
        else{
            this.env.objAttaque(this.taille);
            super.setPV(0);
        }
    }



    public int getButin() {
        return butin;
    }

    public int getVitesse(){
        return vitesse;
    }

    public void dimVitesseDeN(int n){
        vitesse -= n;
        if (vitesse <=0){
            vitesse =1;
        }
    }

    public  void augmVitesseDeN(int n){
        vitesse += n;
    }


    public boolean checkCible(){
        return (this.posX.getValue()== this.vague.getCible().getX() && this.posY.getValue() == this.vague.getCible().getY());
    }

    public int getTaille() {
        return taille;
    }
}