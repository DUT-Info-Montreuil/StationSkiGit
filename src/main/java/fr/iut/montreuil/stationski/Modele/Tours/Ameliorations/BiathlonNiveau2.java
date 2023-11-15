package fr.iut.montreuil.stationski.Modele.Tours.Ameliorations;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;
import fr.iut.montreuil.stationski.Modele.Tours.Biathlon;

public class BiathlonNiveau2 extends Biathlon {

    public BiathlonNiveau2(Tour biathlon){
        //super (canonEau.pv, canonEau.posX, canonEau.posY, int ptsAttaque, canonEau.env)
        super (400, biathlon.getPosX(), biathlon.getPosY(), 45, biathlon.getEnv());

    }
}
