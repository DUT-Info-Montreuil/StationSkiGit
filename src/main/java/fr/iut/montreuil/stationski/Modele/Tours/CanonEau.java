package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;

public class CanonEau extends Tour {

    public static int prix = 500;

    public CanonEau(int posX, int posY, Environnement env) {
        super(20, posX, posY, 40, 50, env);
    }



}
