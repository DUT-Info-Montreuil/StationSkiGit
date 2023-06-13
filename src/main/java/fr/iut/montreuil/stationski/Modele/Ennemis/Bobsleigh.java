package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.Dijkstra;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public class Bobsleigh extends Ennemi {
    public Bobsleigh(int pv, int posX, int posY, int vitesse, Environnement env, int butin, Dijkstra dijkstra, Vague vague, int taille) {
        super(pv, posX, posY, vitesse, env, butin, dijkstra, vague, taille);
    }

    public void meurt(){
        this.env.ajoutArgent(getButin());
        Ennemi s1 = new SkieurBasique(400, getPosX(), getPosY(), 1, this.env, 5, new Dijkstra(this.env.getTerrain()), this.vague);
        Ennemi s2 = new SkieurBasique(400, getPosX(), getPosY(), 1, this.env, 5, new Dijkstra(this.env.getTerrain()), this.vague);
        this.vague.getListEnnemis().add(s1);
        this.vague.getListEnnemis().add(s1);
    }
}
