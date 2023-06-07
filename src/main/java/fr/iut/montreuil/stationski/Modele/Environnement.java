package fr.iut.montreuil.stationski.Modele;

import fr.iut.montreuil.stationski.Modele.Tours.Allier;
import fr.iut.montreuil.stationski.Modele.Tours.Cahute;
import fr.iut.montreuil.stationski.Modele.Tours.DoNotCross;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


import java.util.ArrayList;

public class Environnement {
    private Terrain terrain;
    // private int tour;
    private IntegerProperty argent;
    private ArrayList<Integer> listeEnv;
    private Capacite capa;
    private ObservableList<Tour> listeTours;
    private ObservableList<Allier> listeAllier;
    private Vague vague;
    private IntegerProperty PV;
    private IntegerProperty nbEnnemis;
    private ObservableList<Projectile>listeProj;

    public Environnement(Terrain terrain){
        this.terrain = terrain;
        this.vague = new Vague(1, 100,6,9,0,this);
        this.listeTours = FXCollections.observableArrayList();
        this.argent = new SimpleIntegerProperty(1500);
        this.PV = new SimpleIntegerProperty(20);
        //this.tour = 0;
        this.nbEnnemis = new SimpleIntegerProperty(this.vague.getListEnnemis().size());
        this.listeAllier = FXCollections.observableArrayList();
        this.listeProj = FXCollections.observableArrayList();
    }

    public void resetEnv(){
        System.out.println("Reset env");
    }


    public Terrain getTerrain(){return this.terrain;}

    public Vague getVague(){
        return this.vague;
    }


    public void unTour(){

        majEnnemi();
        majTour();
        majVague();
        majProjectile();
        //tour++;

    }
    public void majTour(){
        int xTour;
        int yTour;
        int xTourD;
        int yTourD;
        for (int defense = this.listeTours.size()-1; defense>=0; defense--){
            // pour le DoNotCross
            if (listeTours.get(defense) instanceof DoNotCross){
                xTourD = listeTours.get(defense).getPosX();
                yTourD = listeTours.get(defense).getPosY();
                for (int acteur = this.vague.getListEnnemis().size()-1; acteur>=0; acteur--){
                    if ((obtenirEnvironInf(this.vague.getListEnnemis().get(acteur).getPosX()) == obtenirEnvironInf(xTourD)) || (obtenirEnvironSup(obtenirEnvironInf(this.vague.getListEnnemis().get(acteur).getPosX())) == obtenirEnvironSup(obtenirEnvironInf(xTourD))) ){
                        if ((obtenirEnvironInf(this.vague.getListEnnemis().get(acteur).getPosY()) == obtenirEnvironInf(yTourD)) || (obtenirEnvironSup(obtenirEnvironInf(this.vague.getListEnnemis().get(acteur).getPosY())) == obtenirEnvironSup(obtenirEnvironInf(yTourD))) ){
                            this.vague.getListEnnemis().get(acteur).dimVitesseDeN(5);
//                            listeTours.get(defense).prendDegats(this.vague.getListEnnemis().get(acteur).getTaille());
//                            System.out.println("degats de "+this.vague.getListEnnemis().get(acteur).getTaille());
//                            this.vague.getListEnnemis().get(acteur).augmVitesseDeN(5);
                        }
                    }
                }
            }
            // fin DoNotCross
            this.listeTours.get(defense).agit();

            //non testé : fonctionnement théroque de la suppression d'une tour ET de la case en dessous (qui est de 5)
            if (!this.listeTours.get(defense).estVivant()){
                xTour = this.listeTours.get(defense).getPosX();
                yTour = this.listeTours.get(defense).getPosY();

                this.terrain.getList().set(((yTour/16)*32+(xTour/16)),1);
                this.listeTours.remove(defense);

            }
        }
    }

    public void majEnnemi(){
        for (int acteur = this.vague.getListEnnemis().size()-1; acteur>=0; acteur--){
            this.vague.getListEnnemis().get(acteur).agit();
            if (!this.vague.getListEnnemis().get(acteur).estVivant()){
                this.ajoutArgent(this.vague.getListEnnemis().get(acteur).getButin());
                this.vague.getListEnnemis().remove(acteur);
            }
        }
        this.nbEnnemis.setValue(this.vague.getListEnnemis().size());
    }

    public void majVague(){
        if (this.vague.getListEnnemis().isEmpty())
            this.vague.prochaineVague();
    }

    public void majProjectile(){

        for(int i = listeProj.size()-1; i>=0; i--){

            boolean touche = listeProj.get(i).attaque();


            if(touche)this.listeProj.remove(i);

        }
    }

    public IntegerProperty getPVP() {
        return PV;
    }

    public ObservableList<Projectile> getListeProj(){return this.listeProj;}

    public int obtenirEnvironInf(int x) {
        int intervalle = 15; // Largeur de l'intervalle

        int borneInf = (x / intervalle) * intervalle;
        int borneSup = borneInf + intervalle;

        return borneInf;
    }

    // necessite méthode préscédente
    public int obtenirEnvironSup(int borneInf) {
        int intervalle = 15; // Largeur de l'intervalle

        int borneSup = borneInf + intervalle;

        return borneSup;
    }

    public void ajouterAllier (Allier a){
        listeAllier.add(a);
    }

    public ObservableList<Allier> getListeAllier() {
        return listeAllier;
    }

    public IntegerProperty nbEnnemisProperty (){
        return this.nbEnnemis;
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


  /*  public int getnbTour(){
        return tour;
    }
*/
}
