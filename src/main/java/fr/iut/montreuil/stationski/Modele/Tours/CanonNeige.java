package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;

public class CanonNeige extends Tour {

    public CanonNeige(int posX, int posY, Environnement env) {
        super(200, posX, posY, 10, 60,40, env);
    }

}
