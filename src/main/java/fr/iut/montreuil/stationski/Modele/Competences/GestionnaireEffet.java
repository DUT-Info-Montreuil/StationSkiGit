package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

import java.util.ArrayList;

public class GestionnaireEffet {
    // Cette classe controle la durée des capacites qui ont un effet dans le temps
    // actuellement il n'existe que le capaciteBoost qui correspond à cette condition

    private int compteur;

    private ArrayList<Capacite> CapacitesAVerif;
    private Environnement env;
/*
    private int dopage;
    private int tourR;
    private int finTourR;*/

    public GestionnaireEffet (Environnement env){
        this.compteur = 0;
        this.CapacitesAVerif = new ArrayList<Capacite>();
        this.env = env;
        /*this.dopage = 0;
        this.tourR=0;
        this.finTourR=1000;*/
    }

   /* public void effetDopage(int defense){
        if (this.dopage>=1){
            dopage++;
        }
        if (this.dopage>=700){
            env.getListeTours().get(defense).setCadence(env.getListeTours().get(defense).getCadenceInit());
            env.getListeTours().get(defense).setPtsAttaque(env.getListeTours().get(defense).getPtsAttaqueInit());
            dopage=0;
        }
    }*/
    public void addCapacite(Capacite c){
        this.CapacitesAVerif.add(c);
    }
   /* public void estRalenti(int ennemi){
        if (env.getVague().getListEnnemis().get(ennemi).getRalenti()){
            tourR++;
        }
        if (tourR >= finTourR){
            tourR = 0;
            finTourR = 250;
            env.getVague().getListEnnemis().get(ennemi).setRalenti(false);
            env.getVague().getListEnnemis().get(ennemi).setVitesse(env.getVague().getListEnnemis().get(ennemi).getVitesseI());
        }
    }*/
    public void surveillanceEffets(){
        for (int i =0; i<CapacitesAVerif.size() ;i++) {
            if(CapacitesAVerif.get(i).getToursEffet() < CapacitesAVerif.get(i).getTourLimite()) {
                CapacitesAVerif.get(i).addToursEffet(1);
            }
            else{
                CapacitesAVerif.get(i).arreterEffet();
            }
        }
    }

    /*public void surveillanceEffetsDefense(int defense){

        effetDopage(defense);
    }
    public void surveillanceEffetsEnnemis(int ennemis){
        estRalenti(ennemis);
    }

    public void setDopage(int dopage) {
        this.dopage = dopage;
    }*/





}
