package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.*;
import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;
import java.util.ArrayList;

public class Bobsleigh extends EnnemiPiste {
    public Bobsleigh( int posX, int posY, Environnement env, Dijkstra dijkstra, Vague vague) {
        super(30, posX, posY, 2, env, 40, dijkstra, vague, 3);

    }

    public void meurt(){
        this.getEnv().ajoutArgent(getButin());
        Terrain nouveauTerrain = créerTerrainPourSkieursDuBobsleigh(this.getEnv().getTerrain(),this.getPosX(), this.getPosY());
        this.getEnv().getVague().getListEnnemis().add(new SkieurBasique( getPosX(), getPosY(),  this.getEnv(),  new Dijkstra(nouveauTerrain), this.getEnv().getVague()));
        this.getEnv().getVague().getListEnnemis().add(new SkieurBasique( getPosX(), getPosY(),  this.getEnv(),  new Dijkstra(nouveauTerrain), this.getEnv().getVague()));
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
