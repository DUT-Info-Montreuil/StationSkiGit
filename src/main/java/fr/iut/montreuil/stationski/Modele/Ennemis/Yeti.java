package fr.iut.montreuil.stationski.Modele.Ennemis;

import fr.iut.montreuil.stationski.Modele.*;
import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;
import javafx.collections.ObservableList;

public class Yeti extends EnnemiHorsPiste {
    private ObservableList<Tour> listeTours;
    private int nbTours;
    private boolean cibleUneTour;
    public Yeti( int posX, int posY,  Environnement env, Vague vague) {
        super(200, posX, posY, 1, env, 200,  vague, 3);
        this.listeTours = this.getEnv().getListeTours();
        this.nbTours=this.listeTours.size();
        renouvelerTerrain();
    }
    @Override
    public void seDeplace(){
        if(this.cibleUneTour){
            if (this.getDijkstra().getParcours().size()>0){
                avancer();
            }
            else if (this.listeTours.size()==this.nbTours && this.listeTours.get(this.listeTours.size()-1).getPV()>0){
                this.attaque(this.listeTours.get(this.listeTours.size()-1));
                if(this.listeTours.get(this.listeTours.size()-1).getPV()<=0){
                    this.getEnv().getTerrain().getList().set(this.listeTours.get(this.listeTours.size()-1).getPosX()/16+(this.listeTours.get(this.listeTours.size()-1).getPosX()/16)*45, 1);
                    this.listeTours.remove(this.listeTours.size()-1);
                    this.nbTours--;
                    renouvelerTerrain();
                }
            }
            else{
                renouvelerTerrain();
            }
        }
        else if(this.listeTours.size()>0){
            this.nbTours=this.listeTours.size();
            renouvelerTerrain();
        }
        else{
            if (this.getDijkstra().getParcours().size()>0){
                avancer();
            }
            else{
                this.setPV(0);
            }
        }
    }

    public void renouvelerTerrain(){

        if(this.listeTours.size()>0){
            this.nbTours=this.listeTours.size();
            this.cibleUneTour=true;
            this.getTerrain().setSource(this.getDijkstra().getGrille().getSommet(this.posX.getValue()/16, this.posY.getValue()/16));
            this.getTerrain().setCible(this.getDijkstra().getGrille().getSommet(this.listeTours.get(this.listeTours.size()-1).getPosX()/16, this.listeTours.get(this.listeTours.size()-1).getPosY()/16));
        }
        else {
            this.cibleUneTour=false;
            this.getTerrain().setSource(this.getDijkstra().getGrille().getSommet(this.posX.getValue() / 16, this.posY.getValue() / 16));
            this.getTerrain().setCible(this.getEnv().getTerrain().getCible());

        }
        this.setDijkstra(new Dijkstra(this.getTerrain()));

    }

    public void attaque(Tour tour){
        tour.prendDegats(1);
    }

    public void avancer(){

        Sommet sommetCible = this.getDijkstra().getParcours().get(this.getDijkstra().getParcours().size()-1);

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
                this.getDirectionP().setValue("b");
            }
            else{
                this.posY.setValue(this.posY.getValue()-1);
                this.getDirectionP().setValue("h");
            }
        }

        // Si on arrive au point du sommet cible, on passe au prochain sommet cible
        if (sommetX==this.posX.getValue() && sommetY==this.posY.getValue())
            this.getDijkstra().getParcours().remove(this.getDijkstra().getParcours().size()-1);
    }


}
