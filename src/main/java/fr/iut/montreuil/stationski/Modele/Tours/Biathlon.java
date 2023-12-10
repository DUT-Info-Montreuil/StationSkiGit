package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;

public class Biathlon extends Tour {

    public Biathlon(int posX, int posY, Environnement env) {
        super(300, posX, posY, 40, 120,70, env);
    }
    public Biathlon( int pv, int posX, int posY, int ptsAttaque, Environnement env) {
        super(pv, posX, posY, ptsAttaque, 120,70, env);
        super.setIdTour(0);


    }
}
