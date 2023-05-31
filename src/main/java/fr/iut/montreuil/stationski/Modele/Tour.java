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
            this.ennemiCible.prendDegats(ptsAttaque);
        }
        ObservableList<Ennemi> listeEn = this.env.getVague().getListEnnemis();
        for(int i =0; i<listeEn.size(); i++){
            if(isInRange(listeEn.get(i))){
                this.ennemiCible = listeEn.get(i);
                this.ennemiCible.prendDegats(ptsAttaque);
            }
        }

    }

    public int getPrix(){
        return prix;
    }


    public boolean isInRange(Ennemi ennemi){
        return (Math.abs(ennemi.getPosY()-this.getPosY())<range && Math.abs(ennemi.getPosX()-this.getPosX())<range);

    }


    @Override
    public void agit() {

    }
}