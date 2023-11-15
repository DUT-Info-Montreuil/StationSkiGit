package fr.iut.montreuil.stationski.Modele.Tours.Ameliorations;

import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Tour;

public class AmeliorationPointsAttaque extends Tour {
    private Tour tour;
    public AmeliorationPointsAttaque (Tour tour) {
        super(tour.getPV(), tour.getPosX(), tour.getPosY(), tour.getPtsAttaque(), tour.getRange(), tour.getCadenceInit(), tour.getEnv());
        this.tour= tour;
        System.out.println("AmeliorationPtsAttaque");
    }

    @Override
    public void attaquer(){tour.attaquer();}
    @Override
    public int getPtsAttaque(){return this.tour.getPtsAttaque()+(int)0.3*this.tour.getPtsAttaque();}
    @Override
    public Acteur getType() {return this.tour.getType();}
}

