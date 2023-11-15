package fr.iut.montreuil.stationski.Modele.Tours.Ameliorations;

import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Tour;

public class AmeliorationCadence extends Tour {
    private Tour tour;
    public AmeliorationCadence (Tour tour) {
        super(tour.getPV(), tour.getPosX(), tour.getPosY(), tour.getPtsAttaque(), tour.getRange(), tour.getCadenceInit(), tour.getEnv());
        this.tour = tour;
        System.out.println("AmeliorationCadence");
    }

    @Override
    public void attaquer(){tour.attaquer();}
    @Override
    public int getCadence(){return this.tour.getCadence()-(int)0.3*this.tour.getCadence();}

    @Override
    public Acteur getType() {return this.tour.getType();}
}
