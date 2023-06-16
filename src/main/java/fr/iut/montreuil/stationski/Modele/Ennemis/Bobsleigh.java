package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.*;
import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;

import java.util.ArrayList;

public class Bobsleigh extends Ennemi {
    public Bobsleigh(int pv, int posX, int posY, int vitesse, Environnement env, int butin, Dijkstra dijkstra, Vague vague) {
        super(pv, posX, posY, vitesse, env, butin, dijkstra, vague, 3);
    }

    public void meurt(){
        this.env.ajoutArgent(getButin());
        Terrain nouveauTerrain = créerTerrainPourSkieursDuBobsleigh(this.env.getTerrain(),this.getPosX(), this.getPosY());
        this.env.getVague().getListEnnemis().add(new SkieurBasique(400, getPosX(), getPosY(), 1, this.env, 5, new Dijkstra(nouveauTerrain), this.env.getVague()));
        this.env.getVague().getListEnnemis().add(new SkieurBasique(400, getPosX(), getPosY(), 1, this.env, 5, new Dijkstra(nouveauTerrain), this.env.getVague()));
    }
    public Terrain créerTerrainPourSkieursDuBobsleigh(Terrain terrain, int posX, int posY){
        ArrayList<Integer> listeTerrain = new ArrayList<Integer>();
        for (int i=0; i<terrain.getTerrain().size(); i++){
            listeTerrain.add(terrain.getTerrain().get(i));
        }
        Terrain nouveauTerrain;
        nouveauTerrain = new Terrain(45, 45,  new Sommet(posX/16,posY/16,false), terrain.getCible(), listeTerrain);
        return nouveauTerrain;
    }
}
