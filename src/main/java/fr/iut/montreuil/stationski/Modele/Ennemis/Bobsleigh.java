package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.*;

public class Bobsleigh extends Ennemi {
    public Bobsleigh(int pv, int posX, int posY, int vitesse, Environnement env, int butin, Dijkstra dijkstra, Vague vague) {
        super(pv, posX, posY, vitesse, env, butin, dijkstra, vague, 3);
    }

    public void meurt(){
        this.env.ajoutArgent(getButin());
        Ennemi s1 = new SkieurBasique(400, getPosX(), getPosY(), 1, this.env, 5, new Dijkstra(this.env.getTerrain(),new Sommet(getPosX(),getPosY(),false)), this.vague);
        Ennemi s2 = new SkieurBasique(400, getPosX(), getPosY(), 1, this.env, 5, new Dijkstra(this.env.getTerrain(),new Sommet(getPosX(),getPosY(),false)), this.vague);
        this.vague.getListEnnemis().add(s1);
        this.vague.getListEnnemis().add(s1);
    }
}
