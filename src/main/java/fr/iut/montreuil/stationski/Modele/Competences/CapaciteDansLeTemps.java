/*
package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;

import java.util.ArrayList;

public abstract class CapaciteDansLeTemps extends Capacite {

    // INUTILISE ACTUELLEMENT : si capa fonctionnelles sans cette classe, la supprimer svp
    private int ToursEffet;
    private int TourLimite;
    private ArrayList<Acteur> ActeursSousEffet;
    public CapaciteDansLeTemps(String nom, int cout, Environnement env) {
        super(nom, cout, env);
        ActeursSousEffet = new ArrayList<Acteur>();

    }

    public ArrayList<Acteur> getActeursSousEffet() {
        return ActeursSousEffet;
    }

    public void addActeur(Acteur a){
        this.ActeursSousEffet.add(a);
    }
    public void removeActeur(Acteur a){
        this.ActeursSousEffet.remove(a);
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

    @Override
    public abstract void effet(int acteur);

    @Override
    public abstract void parcours();
}*/
