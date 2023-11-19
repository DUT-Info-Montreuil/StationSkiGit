package fr.iut.montreuil.stationski.Modele.Tours.Ameliorations;

import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Tour;

public class AmeliorationPV extends Tour {
    private Tour tour;
    public AmeliorationPV (Tour tour) {
        super(tour.getPV(), tour.getPosX(), tour.getPosY(), tour.getPtsAttaque(), tour.getRange(), tour.getCadenceInit(), tour.getEnv());
        this.tour=tour;
        this.tour.setPVMax(this.tour.getPV()+50);
        this.tour.setPV(this.tour.getPV()+50);
        System.out.println("AmeliorationPV");
    }
    @Override
    public void attaquer(){tour.attaquer();}
    @Override
    public int getPV(){
        return this.tour.getPV();
    }
    @Override
    public Acteur getType() {return this.tour.getType();}
}
