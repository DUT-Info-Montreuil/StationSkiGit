package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.Dijkstra;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public class Snowborder extends Ennemi {
    public Snowborder(int pv, int posX, int posY, int vitesse, Environnement env, int butin, Dijkstra dijkstra, Vague vague, int taille) {
        super(pv, posX, posY, vitesse, env, butin, dijkstra, vague, taille);
    }
}
