package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

import java.util.ArrayList;

public class GestionnaireEffet {
    // Cette classe controle la durée des capacites qui ont un effet dans le temps
    // actuellement il n'existe que le capaciteBoost qui correspond à cette condition

    private int compteur;

    //Pour l'instant pas d'utilites
    private ArrayList<Capacite> CapacitesAVerif;
    private Environnement env;

    private int dopage;

    public GestionnaireEffet (Environnement env){
        this.compteur = 0;
        this.CapacitesAVerif = new ArrayList<Capacite>();
        this.env = env;
        this.dopage = 0;
    }

    public void effetDopage(int defense){
        if (this.dopage>=1){
            dopage++;
        }
        if (this.dopage>=700){
            env.getListeTours().get(defense).setCadence(env.getListeTours().get(defense).getCadenceInit());
            env.getListeTours().get(defense).setPtsAttaque(env.getListeTours().get(defense).getPtsAttaqueInit());
            dopage=0;
        }
    }

    public void surveillanceEffets(int defense){

        effetDopage(defense);
    }

    public void setDopage(int dopage) {
        this.dopage = dopage;
    }





}
