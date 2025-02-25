package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.*;
import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;
import fr.iut.montreuil.stationski.Modele.Projectiles.ProjectileAllier;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Allier extends Acteur {
    private ObservableList<Ennemi> listeEnnemis;
    private Dijkstra dijkstra;
    private Terrain terrain;

    private int ptsAttaque;
    private Ennemi ennemiCible;
    private int range;
    private int cadence;
    private Cahute cahute;
    private StringProperty direction;
    public Allier(int posX, int posY, Environnement env, Cahute cahute) {
        super(20, posX, posY, env);
        this.listeEnnemis=this.getEnv().getVague().getListEnnemis();
        this.ptsAttaque=15;
        this.range=30;
        this.cahute=cahute;
        this.cadence = 50;
        this.direction = new SimpleStringProperty("b");
        this.terrain=créerTerrainPourAllier(this.getEnv().getTerrain());
        renouvelerTerrain();
    }
    public Terrain créerTerrainPourAllier(Terrain terrain){
        ArrayList<Integer> listeTerrain = new ArrayList<Integer>();
        for (int i=0; i<terrain.getTerrain().size(); i++)
            listeTerrain.add(terrain.getTerrain().get(i));

        Terrain nouveauTerrain;
        nouveauTerrain = new Terrain(45, 45,  new Sommet(this.getPosX(),this.getPosY(),false), new Sommet(this.getPosX(),this.getPosY(), false), listeTerrain);
        this.dijkstra= new Dijkstra(nouveauTerrain);
        return nouveauTerrain;
    }
    @Override
    public void agit() {
        if(!this.cahute.estVivant()){
            this.setPV(0);
        }
        if(ennemiCible != null  ){
            if(isInRange(ennemiCible.getPosX(), ennemiCible.getPosY(), this.range)) {
                if(ennemiCible.estVivant()) {
                    if(this.getEnv().getNbTour() % cadence ==0) {
                        tirer();
                    }
                    avancer();
                } else ennemiCible = null;
            }
            else{
                ennemiCible= searchEnnemi();
                avancer();
            }

        }else{
            ennemiCible = searchEnnemi();
            avancer();
        }

    }

    public void tirer(){
        this.getEnv().getListeProj().add(new ProjectileAllier(this.ennemiCible, this.getPosX(), this.getPosY(), this.ptsAttaque));

    }
    public Ennemi searchEnnemi(){
        for(Ennemi e : this.getEnv().getVague().getListEnnemis()){
            if(isInRange(e.getPosX(), e.getPosY(), this.range)){
                return e;

            }
        }
        return null;
    }
    public boolean isInRangeCahute(int posX, int posY, int range){
        return (Math.abs(this.cahute.getPosX()-posX)<range && Math.abs(this.cahute.getPosY()-posY)<range && posX>=0 && posX<720 && posY>=0 && posY<720 && this.getEnv().getTerrain().getTerrain().get((posX/16)+(posY/16)*45)==0);

    }
    public boolean isInRange(int posX, int posY, int range){
        return (Math.abs(this.getPosX()-posX)<range && Math.abs(this.getPosY()-posY)<range);

    }
    public void avancer(){
        if (this.dijkstra.getParcours().size()>0) {
            Sommet sommetCible = this.dijkstra.getParcours().get(this.dijkstra.getParcours().size() - 1);

            int sommetX = sommetCible.getX() * 16;
            int sommetY = sommetCible.getY() * 16;

            // Parcours pixel par pixel aléatoire du terrain
            if (Math.random() * 1 > 0.5 && this.posX.getValue() != sommetX) {
                if (sommetX > this.posX.getValue()) {
                    this.posX.setValue(this.posX.getValue() + 1);
                    direction.setValue("d");
                } else {
                    this.posX.setValue(this.posX.getValue() - 1);
                    direction.setValue("g");
                }
            } else if (Math.random() * 1 > 0.8 && this.posX.getValue() != sommetX && this.posY.getValue() != sommetY) { // Aller en diagonale
                if (sommetX > this.posX.getValue() && sommetY > this.posY.getValue()) {
                    this.posX.setValue(this.posX.getValue() + 1);
                    this.posY.setValue(this.posY.getValue() + 1);
                } else if (sommetX < this.posX.getValue() && sommetY > this.posY.getValue()) {
                    this.posX.setValue(this.posX.getValue() - 1);
                    this.posY.setValue(this.posY.getValue() + 1);
                } else if (sommetX < this.posX.getValue() && sommetY < this.posY.getValue()) {
                    this.posX.setValue(this.posX.getValue() - 1);
                    this.posY.setValue(this.posY.getValue() - 1);
                } else {
                    this.posX.setValue(this.posX.getValue() + 1);
                    this.posY.setValue(this.posY.getValue() - 1);
                }
            } else if (this.posY.getValue() != sommetY) {
                if (sommetY > this.posY.getValue()) {
                    this.posY.setValue(this.posY.getValue() + 1);
                } else {
                    this.posY.setValue(this.posY.getValue() - 1);
                }
            }

            // Si on arrive au point du sommet cible, on passe au prochain sommet cible
            if (sommetX == this.posX.getValue() && sommetY == this.posY.getValue())
                this.dijkstra.getParcours().remove(this.dijkstra.getParcours().size() - 1);
        }
        else{
            renouvelerTerrain();
        }
    }

    public void renouvelerTerrain(){

        //this.dijkstra.setSource(this.dijkstra.getGrille().getSommet(this.posX.getValue()/16, this.posY.getValue()/16));
        boolean positionRecherchee=false;
        while(!positionRecherchee){

            int posX = (int)(Math.random()*(2*this.cahute.getRange()))-this.cahute.getRange() +this.cahute.getPosX();
            int posY=  (int)(Math.random()*(2*this.cahute.getRange()))-this.cahute.getRange() +this.cahute.getPosY();

            if (isInRangeCahute(posX, posY,this.cahute.getRange())){
                positionRecherchee=true;

                this.terrain.setSource(this.dijkstra.getGrille().getSommet(this.posX.getValue()/16, this.posY.getValue()/16));
                this.terrain.setCible(this.dijkstra.getGrille().getSommet(posX/16, posY/16));
            }
        }
        this.dijkstra = new Dijkstra(this.terrain);
    }

    public StringProperty getDirectionP() {
        return direction;
    }

    public Terrain getTerrain(){return this.terrain;}
}
