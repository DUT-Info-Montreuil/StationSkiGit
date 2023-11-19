package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.*;

public class SkieurBasique extends EnnemiPiste {

    public SkieurBasique( int posX, int posY, Environnement env, Dijkstra dijkstra, Vague vague) {
        super(20, posX, posY, 1, env, 20, dijkstra,  vague, 1);
    }

}
