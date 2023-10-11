package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.*;

public class Luge extends EnnemiPiste {
    public Luge( int posX, int posY,  Environnement env, Dijkstra dijkstra, Vague vague) {
        super(50, posX, posY, 2, env, 50, dijkstra, vague, 3);
    }

}
