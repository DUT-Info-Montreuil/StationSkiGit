package fr.iut.montreuil.stationski.Modele.Ennemis;


import fr.iut.montreuil.stationski.Modele.Dijkstra;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public class SkieurBasique extends Ennemi {

    public SkieurBasique( int posX, int posY, Environnement env, Dijkstra dijkstra, Vague vague) {
        super(20, posX, posY, 1, env, 20, dijkstra,  vague, 1);
    }

}
