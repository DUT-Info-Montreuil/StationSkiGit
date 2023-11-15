package fr.iut.montreuil.stationski.Modele.Tours.Ameliorations;

import fr.iut.montreuil.stationski.Modele.Tour;
import fr.iut.montreuil.stationski.Modele.Tours.CanonNeige;

public class CanonNeigeNiveau2 extends CanonNeige {
    public CanonNeigeNiveau2(Tour canonNeige){
        super (250, canonNeige.getPosX(), canonNeige.getPosY(), 5, canonNeige.getEnv());

    }
}
