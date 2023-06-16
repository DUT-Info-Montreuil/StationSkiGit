package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.Dijkstra;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public class Snowboarder extends Ennemi {
    public Snowboarder( int posX, int posY,  Environnement env, Dijkstra dijkstra, Vague vague) {
        super(30, posX, posY, 1, env, 30, dijkstra, vague, 2);
    }
}
