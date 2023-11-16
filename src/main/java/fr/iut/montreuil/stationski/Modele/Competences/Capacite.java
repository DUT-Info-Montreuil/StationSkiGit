package fr.iut.montreuil.stationski.Modele.Competences;


import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Environnement;

import java.util.ArrayList;

public abstract class Capacite {
    protected String nom;
    protected int cout;
    protected Environnement env;
    protected ArrayList<Acteur> ActeursSousEffet;
    protected int ToursEffet;
    protected int TourLimite;

    public Capacite (String nom, int cout, Environnement env, int TourLimite){
        this.nom =nom;
        this.cout = cout;
        this.env=env;
        this.ToursEffet = 0;
        this.TourLimite = TourLimite;
        ActeursSousEffet = new ArrayList<Acteur>();
    }

    public ArrayList<Acteur> getActeursSousEffet() {
        return ActeursSousEffet;
    }

    public void addActeur(Acteur a){
        this.ActeursSousEffet.add(a);
    }
    public void arreterEffet(){
        for (int acteur =ActeursSousEffet.size(); acteur>=0; acteur--) {
            stopEffet(acteur);
            this.ActeursSousEffet.remove(acteur);
        }
    }

    public int getTourLimite() {
        return TourLimite;
    }

    public int getToursEffet() {
        return ToursEffet;
    }

    public void addToursEffet(int toursEffetEnPlus) {
        ToursEffet += toursEffetEnPlus;
    }

    public abstract void effet(int acteur);
    public abstract void stopEffet(int acteur);
    public abstract void parcours();
    public void activation (){
        parcours();
        this.env.getGestioEffets().addCapacite(this);
    }
    public String getNom() {
        return nom;
    }

    public int getCout() {
        return cout;
    }


}
