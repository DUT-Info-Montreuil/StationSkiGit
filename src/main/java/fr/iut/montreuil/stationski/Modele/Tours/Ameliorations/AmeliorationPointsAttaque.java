package fr.iut.montreuil.stationski.Modele.Tours.Ameliorations;

import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Tour;

public class AmeliorationPointsAttaque extends Tour {
    private Tour tour;
    public AmeliorationPointsAttaque (Tour tour) {
        super(tour.getPV(), tour.getPosX(), tour.getPosY(), tour.getPtsAttaque(), tour.getRange(), tour.getCadence(), tour.getEnv());
        this.tour= tour;
        System.out.println("AmeliorationPtsAttaque");

    }

    @Override
    public void attaquer(){tour.attaquer();}
    @Override
    public double getPtsAttaque(){
        return Math.floor(this.tour.getPtsAttaque()*1.3);

    }
    @Override
    public Acteur getType() {return this.tour.getType();}
}

