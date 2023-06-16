package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.Dijkstra;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public class Luge extends Ennemi {
    public Luge( int posX, int posY,  Environnement env, Dijkstra dijkstra, Vague vague) {
        super(50, posX, posY, 2, env, 50, dijkstra, vague, 3);
    }

}
