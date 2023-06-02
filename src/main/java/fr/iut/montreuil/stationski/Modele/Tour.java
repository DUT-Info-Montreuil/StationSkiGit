package fr.iut.montreuil.stationski.Modele;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Tour extends Entite {

    private int ptsAttaque;
    private int range;
    private Ennemi ennemiCible;
    private static int prix = 500;

    public Tour (int pv, int posX, int posY, int ptsAttaque, int range, Environnement env){
        super(pv, posX, posY, env);
        this.ptsAttaque=ptsAttaque;
        this.range=range;

    }




    public void attaquer(){

        if(ennemiCible != null  ){

            if(isInRange(ennemiCible)) {
                if(ennemiCible.estVivant()) ennemiCible.prendDegats(ptsAttaque);
                else ennemiCible = null;
            }
            else{
                ennemiCible= searchEnnemi();
                attaquer();

            }

        }else{

            ennemiCible = searchEnnemi();
        }
/*
        ObservableList<Ennemi> listeEn = this.env.getVague().getListEnnemis();
        for(int i =0; i<listeEn.size(); i++){
            if(isInRange(listeEn.get(i))){
                this.ennemiCible = listeEn.get(i);
                this.ennemiCible.prendDegats(ptsAttaque);
            }
        }
       */
    }

    public Ennemi searchEnnemi(){
        for(Ennemi e : this.env.getVague().getListEnnemis()){
            if(isInRange(e)){

                return e;

            }
        }
        return null;
    }

    public int getPrix(){
        return prix;
    }


    public boolean isInRange(Ennemi ennemi){
        return (Math.abs(this.getPosY()-ennemi.getPosY())<this.range && Math.abs(this.getPosX()-ennemi.getPosX())<this.range);

    }


    @Override
    public void agit() {

        attaquer();
    }
}