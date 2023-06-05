package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;

public class Biathlon extends Tour {

    public Biathlon(int posX, int posY, Environnement env) {
        super(20, posX, posY, 50, 25, env);
    }
}
