package fr.iut.montreuil.stationski.Modele.Tours.Ameliorations;

import fr.iut.montreuil.stationski.Modele.Tour;
import fr.iut.montreuil.stationski.Modele.Tours.CanonEau;

public class CanonEauNiveau2 extends CanonEau {

    public CanonEauNiveau2(Tour canonEau){
        //super (canonEau.pv, canonEau.posX, canonEau.posY, int ptsAttaque, canonEau.env)
        super (400, canonEau.getPosX(), canonEau.getPosY(), 5, canonEau.getEnv());

    }


}
