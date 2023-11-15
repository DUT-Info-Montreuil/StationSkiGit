package fr.iut.montreuil.stationski.Modele.Tours.Ameliorations;

import fr.iut.montreuil.stationski.Modele.Tour;
import fr.iut.montreuil.stationski.Modele.Tours.Telesiege;

public class TelesiegeNiveau2 extends Telesiege {
    public TelesiegeNiveau2(Tour telesiege){
        super (200, telesiege.getPosX(), telesiege.getPosY(), 40, telesiege.getEnv());
    }
}
