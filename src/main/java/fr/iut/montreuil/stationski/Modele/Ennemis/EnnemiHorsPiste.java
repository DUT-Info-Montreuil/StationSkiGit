package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.*;
import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;
import java.util.ArrayList;

public abstract class EnnemiHorsPiste extends Ennemi {
    private Terrain terrain;
    public EnnemiHorsPiste(int pv, int posX, int posY, int vitesse, Environnement env, int butin, Vague vague, int importance){
        super(pv, posX, posY, vitesse, env, butin, vague, importance);
        this.vague = vague;
        this.terrain=créerTerrainPourEnnemiHorsPiste(env.getTerrain());
        this.setDijkstra(new Dijkstra(this.terrain));
    }

    public Terrain créerTerrainPourEnnemiHorsPiste(Terrain terrain){
        ArrayList<Integer> listeTerrain = new ArrayList<Integer>();
        for (int i=0; i<terrain.getTerrain().size(); i++){
            if(terrain.getTerrain().get(i)==1 || terrain.getTerrain().get(i)==5 ) listeTerrain.add(0);
            else listeTerrain.add(terrain.getTerrain().get(i));
        }
        Terrain nouveauTerrain;
        nouveauTerrain = new Terrain(45, 45, new Sommet(44,0,false), terrain.getCible(), listeTerrain);
        return nouveauTerrain;
    }


    public Terrain getTerrain(){return this.terrain;}
}
