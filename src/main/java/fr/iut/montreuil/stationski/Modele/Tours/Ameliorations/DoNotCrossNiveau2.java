package fr.iut.montreuil.stationski.Modele.Tours.Ameliorations;

import fr.iut.montreuil.stationski.Modele.Tour;
import fr.iut.montreuil.stationski.Modele.Tours.DoNotCross;

public class DoNotCrossNiveau2 extends DoNotCross {
    public DoNotCrossNiveau2(Tour doNotCross){
        super (250, doNotCross.getPosX(), doNotCross.getPosY(), 3, doNotCross.getEnv());
    }
}
