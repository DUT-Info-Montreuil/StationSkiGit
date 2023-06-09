package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Yeti extends Ennemi {
    private ObservableList<Tour> listeTours;
    private Dijkstra dijkstra;
    private Terrain terrain;
    public Yeti(int pv, int posX, int posY, int vitesse, Environnement env, int butin, Vague vague, ObservableList<Tour> listeTours) {
        super(pv, posX, posY, vitesse, env, butin,  vague, 1);
        this.listeTours=listeTours;
        this.terrain = créerTerrainPourYeti(env.getTerrain());
        this.dijkstra = new Dijkstra(this.terrain);
        renouvelerTerrain();
    }

    public Terrain créerTerrainPourYeti(Terrain terrain){
        //Créer un terrain 0 0 0 0 0  pour le snow et 2 pour le décor.

        ArrayList<Integer> listeTerrain = new ArrayList<Integer>();
        for (int i=0; i<terrain.getTerrain().size(); i++){
            if(terrain.getTerrain().get(i)==1) listeTerrain.add(0);
            else listeTerrain.add(terrain.getTerrain().get(i));
        }
        Terrain nouveauTerrain;

        if(this.listeTours.size()>0) {
            nouveauTerrain = new Terrain(45, 45, 1, terrain.getSource(), new Sommet(this.listeTours.get(this.listeTours.size()-1).getPosX()/16, this.listeTours.get(this.listeTours.size()-1).getPosY()/16, false), listeTerrain);
        }
        else {
            nouveauTerrain = new Terrain(45, 45, 1, new Sommet(0,0,false), terrain.getCible(), listeTerrain);
        }
        return nouveauTerrain;
    }
    @Override
    public void deplacement(){
        if (this.dijkstra.getParcours().size()>0){
            Sommet sommetCible = this.dijkstra.getParcours().get(this.dijkstra.getParcours().size()-1);

            int sommetX = sommetCible.getX()*16;
            int sommetY = sommetCible.getY()*16;

            // Parcours pixel par pixel aléatoire du terrain
            if(Math.random()*1>0.5 && this.posX.getValue()!=sommetX){
                if (sommetX>this.posX.getValue()){
                    this.posX.setValue(this.posX.getValue()+1);
                }
                else{
                    this.posX.setValue(this.posX.getValue()-1);
                }
            }
            else if (Math.random()*1>0.8 && this.posX.getValue()!=sommetX && this.posY.getValue()!=sommetY){ // Aller en diagonale
                if (sommetX>this.posX.getValue() && sommetY>this.posY.getValue()){
                    this.posX.setValue(this.posX.getValue()+1);
                    this.posY.setValue(this.posY.getValue()+1);
                }
                else if (sommetX<this.posX.getValue() && sommetY>this.posY.getValue()){
                    this.posX.setValue(this.posX.getValue()-1);
                    this.posY.setValue(this.posY.getValue()+1);
                }
                else if (sommetX<this.posX.getValue() && sommetY<this.posY.getValue()){
                    this.posX.setValue(this.posX.getValue()-1);
                    this.posY.setValue(this.posY.getValue()-1);
                }
                else{
                    this.posX.setValue(this.posX.getValue()+1);
                    this.posY.setValue(this.posY.getValue()-1);
                }
            }
            else if (this.posY.getValue()!=sommetY){
                if (sommetY>this.posY.getValue()){
                    this.posY.setValue(this.posY.getValue()+1);
                }
                else{
                    this.posY.setValue(this.posY.getValue()-1);
                }
            }


            // Si on arrive au point du sommet cible, on passe au prochain sommet cible
            //if (sommetX==this.posX.getValue() && sommetY==this.posY.getValue())
            //    this.dijkstra.getParcours().remove(this.dijkstra.getParcours().size()-1);
        }
        else if (this.listeTours.size()>0){
            if(this.listeTours.get(this.listeTours.size()-1).getPV()>0){
                this.attaque(this.listeTours.get(this.listeTours.size()-1));
            }
            else{
                this.listeTours.remove(this.listeTours.size()-1);
                renouvelerTerrain();
            }
        }
        else{
            this.setPV(0);
        }
    }
    public void renouvelerTerrain(){
        this.dijkstra.setSource(this.dijkstra.getGrille().getSommet(this.posX.getValue()/16, this.posY.getValue()/16));
        if(this.listeTours.size()>0){
            this.terrain.setCible(this.dijkstra.getGrille().getSommet(this.listeTours.get(this.listeTours.size()-1).getPosX()/16, this.listeTours.get(this.listeTours.size()-1).getPosY()/16));
        }
        else this.setPV(0);
        this.dijkstra = new Dijkstra(this.terrain);

    }
    public void attaque(Tour tour){
        tour.prendDegats(1);
    }

}
