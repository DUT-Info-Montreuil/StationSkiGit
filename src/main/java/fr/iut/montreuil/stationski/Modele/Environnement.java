package fr.iut.montreuil.stationski.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


import java.util.ArrayList;

public class Environnement {
    private Terrain terrain;

    private IntegerProperty argent;
    private ArrayList<Integer> listeEnv;
    private Capacite capa;
    private ObservableList<Tour> listeTours;
    private Vague vague;
    private IntegerProperty PV;

    public Environnement(Terrain terrain){
        this.terrain = terrain;
        this.vague = new Vague(1, 100,6,9,0,this);
        this.listeTours = FXCollections.observableArrayList();
        this.argent = new SimpleIntegerProperty(500);
        this.PV = new SimpleIntegerProperty(20);


    }

    public void resetEnv(){
        System.out.println("Reset env");
    }


    public Terrain getTerrain(){return this.terrain;}

    public Vague getVague(){
        return this.vague;
    }


    public void unTour(){
        int xTour;
        int yTour;

        for (int acteur = this.vague.getListEnnemis().size()-1; acteur>=0; acteur--){
            this.vague.getListEnnemis().get(acteur).agit();

            if (!this.vague.getListEnnemis().get(acteur).estVivant()){
                this.ajoutArgent(this.vague.getListEnnemis().get(acteur).getButin());
                this.vague.getListEnnemis().remove(acteur);
            }
        }
        for (int defense = this.listeTours.size()-1; defense>=0; defense--){
            this.listeTours.get(defense).agit();


            //non testé : fonctionnement théroque de la suppression d'une tour ET de la case en dessous (qui est de 5)
            if (!this.listeTours.get(defense).estVivant()){
                xTour = this.listeTours.get(defense).getPosX();
                yTour = this.listeTours.get(defense).getPosY();

                //y*32+x
                this.terrain.getList().set((yTour*32+xTour),1);

                this.listeTours.remove(defense);

            }
        }
        if (this.vague.getListEnnemis().isEmpty())
            this.vague.prochaineVague();




    }

    public IntegerProperty getPVP() {
        return PV;
    }


    public int getPV() {
        return PV.getValue();
    }

    public void setPV(int pv){
        this.PV.setValue(pv);
    }

    public void perdreVie (int degat){
        this.PV.setValue(this.PV.getValue()- degat);
    }


    public void addTour(Tour t){
        this.listeTours.add(t);

    }

    public void removeTour(Tour t){
        listeTours.remove(t);
    }

    public ObservableList<Tour> getListeTours(){
        return this.listeTours;
    }

    public Tour getTour(String id){
        for(Tour t : this.listeTours){
            if(t.getId().equals(id)){
                return t;
            }
        }
        return null;
    }



    public int getArgent() {
        return argent.getValue();
    }

    public IntegerProperty getArgentP () {
        return argent;
    }

    public void ajoutArgent(int somme) {
        this.argent.setValue(this.argent.getValue()+ somme);
    }

    public void retraitArgent (int somme){
        this.argent.setValue(this.argent.getValue()- somme);
    }

    public void objAttaque(int taille){
        this.setPV(this.PV.getValue()-taille);

    }


}