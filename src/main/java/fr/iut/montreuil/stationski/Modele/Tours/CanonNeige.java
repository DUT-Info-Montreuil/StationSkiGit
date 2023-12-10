package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;

public class CanonNeige extends Tour {

    public CanonNeige(int posX, int posY, Environnement env) {
        super(200, posX, posY, 10, 60,40, env);
    }
    public CanonNeige( int pv, int posX, int posY, int ptsAttaque, Environnement env) {
        super(pv, posX, posY, ptsAttaque, 60,40, env);
        super.setIdTour(3);


    }
}
