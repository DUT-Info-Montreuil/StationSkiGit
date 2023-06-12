package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;

public class DoNotCross extends Tour {

    public DoNotCross(int posX, int posY, Environnement env) {
        super(100, posX, posY, 2, 1,0, env);
    }
}
