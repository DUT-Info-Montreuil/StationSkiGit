package fr.iut.montreuil.stationski.Modele;

import fr.iut.montreuil.stationski.Modele.Competences.Capacite;
import fr.iut.montreuil.stationski.Modele.Competences.GestionnaireEffet;
import fr.iut.montreuil.stationski.Modele.Tours.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Environnement {
    private Terrain terrain;
    private IntegerProperty argent;
    private ObservableList<Tour> listeTours;
    private ObservableList<Allier> listeAllier;
    private Vague vague;
    private IntegerProperty PV;
    private int nbTour;
    private IntegerProperty nbEnnemis;
    private ObservableList<Projectile>listeProj;
    private GestionnaireEffet gestioEffets;
    private Map<String, Integer> prixDesTours;

    public Environnement(Terrain terrain){
        this.terrain = terrain;
        this.vague = new Vague(this);
        this.listeTours = FXCollections.observableArrayList();
        this.argent = new SimpleIntegerProperty(1600);
        this.PV = new SimpleIntegerProperty(20);
        this.nbTour=1;
        this.nbEnnemis = new SimpleIntegerProperty(this.vague.getListEnnemis().size());
        this.listeAllier = FXCollections.observableArrayList();
        this.listeProj = FXCollections.observableArrayList();
        this.prixDesTours = new HashMap<>();
        initialiserPrixTours();
        this.gestioEffets= new GestionnaireEffet();
    }

    public void setNbEnnemis(int nbEnnemis){this.nbEnnemis.setValue(nbEnnemis);}
    public Terrain getTerrain(){return this.terrain;}

    public Vague getVague(){
        return this.vague;
    }


    public void unTour(){

        majEnnemi();
        majTour(nbTour);
        majAllier();
        majVague();
        majProjectile();
        gestioEffets.surveillanceEffets();
        nbTour++;


    }
    public void majTour(int nbTour){
        for (int defense = this.listeTours.size()-1; defense>=0; defense--){
            //gestioEffets.surveillanceEffetsDefense(defense);

            this.listeTours.get(defense).agit();

            if (!this.listeTours.get(defense).estVivant()){
                mortTour(defense);
            }
        }
    }

    public void mortTour(int defense){
        int xTour;
        int yTour;
        xTour = this.listeTours.get(defense).getPosX();
        yTour = this.listeTours.get(defense).getPosY();

        this.terrain.getList().set(((yTour/16)*45+(xTour/16)),1);
        if (this.listeTours.get(defense) instanceof DoNotCross){
            this.terrain.getList().set(((yTour/16)*32+(xTour/16)),0);
        }
        else {
            this.terrain.getList().set(((yTour / 16) * 32 + (xTour / 16)), 1);
        }
        this.listeTours.remove(defense);
    }
    public void majEnnemi() {
        for (int ennemi = this.vague.getListEnnemis().size()-1; ennemi>=0; ennemi--){
            //gestioEffets.surveillanceEffetsEnnemis(ennemi);
        }
        this.vague.faireAgirEnnemis();
    }
    public void majVague(){
        this.vague.faireAgirVague(this.nbTour);
    }

    public void majAllier(){
        for (int acteur = this.listeAllier.size()-1; acteur>=0; acteur--){
            this.listeAllier.get(acteur).agit();
            if (!this.listeAllier.get(acteur).estVivant()){
                this.listeAllier.remove(acteur);
            }
        }
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

    public GestionnaireEffet getGestioEffets() {
        return gestioEffets;
    }

    public ObservableList<Projectile> getListeProj(){return this.listeProj;}

    public void ajouterAllier (Allier a){
        listeAllier.add(a);
    }

    public int getNbTour() {
        return nbTour;
    }

    public ObservableList<Allier> getListeAllier() {
        return listeAllier;
    }

    public IntegerProperty nbEnnemisProperty (){
        return this.nbEnnemis;
    }

    public void setPV(int pv){
        this.PV.setValue(pv);
    }
    public void addTour(Tour t){
        this.listeTours.add(t);

    }
    public ObservableList<Tour> getListeTours(){
        return this.listeTours;
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

    public ArrayList<Capacite> getCapacites() {
        return gestioEffets.getCapacitesAVerif();
    }

    public void initialiserPrixTours(){
        this.prixDesTours.put("canonEau", 300);
        this.prixDesTours.put("canonNeige", 200);
        this.prixDesTours.put("biathlon", 300);
        this.prixDesTours.put("cahute", 500);
        this.prixDesTours.put("donotcross", 100);
        this.prixDesTours.put("telesiege", 300);
        this.prixDesTours.put("teleski", 300);
    }
    public Map<String, Integer> getPrixTours(){return this.prixDesTours;}

}
