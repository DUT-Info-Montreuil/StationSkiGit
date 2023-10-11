package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.*;

public class Snowboarder extends EnnemiPiste {
    public Snowboarder( int posX, int posY,  Environnement env, Dijkstra dijkstra, Vague vague) {
        super(30, posX, posY, 1, env, 30, dijkstra, vague, 2);
    }
}
