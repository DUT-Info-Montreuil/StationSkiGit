package fr.iut.montreuil.stationski.Modele.Ennemis;


import fr.iut.montreuil.stationski.Modele.Dijkstra;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public abstract class EnnemiPiste extends Ennemi {
    public EnnemiPiste(int pv, int posX, int posY, int vitesse, Environnement env, int butin, Dijkstra dijkstra, Vague vague, int importance){
        super(pv, posX, posY, vitesse, env, butin,dijkstra, vague, importance);
    }
}