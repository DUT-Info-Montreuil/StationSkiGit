package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;

public class Teleski extends Tour {
    public Teleski(int posX, int posY, Environnement env) {
        super(10, posX, posY, 40, 20,50, env);
    }
}
