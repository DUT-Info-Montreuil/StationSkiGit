package fr.iut.montreuil.stationski.Modele.Tours.Ameliorations;

import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Tour;

public class AmeliorationCadence extends Tour {
    private Tour tour;
    public AmeliorationCadence (Tour tour) {
        super(tour.getPV(), tour.getPosX(), tour.getPosY(), tour.getPtsAttaque(), tour.getRange(), tour.getCadence(), tour.getEnv());
        this.tour = tour;
        System.out.println("AmeliorationCadence");

    }

    @Override
    public void attaquer(){tour.attaquer();}
    @Override
    public double getCadence() {
        return Math.round(this.tour.getCadence()*0.7);

    }

    @Override
    public Acteur getType() {return this.tour.getType();}
}
