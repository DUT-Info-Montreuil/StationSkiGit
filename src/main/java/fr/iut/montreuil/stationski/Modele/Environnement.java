package fr.iut.montreuil.stationski.Modele;

import fr.iut.montreuil.stationski.Modele.Ennemis.Bobsleigh;
import fr.iut.montreuil.stationski.Modele.Ennemis.SkieurBasique;
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
    // private int tour;
    private IntegerProperty argent;
    private ArrayList<Integer> listeEnv;
    private ArrayList<Capacite> capacites;
    private ObservableList<Tour> listeTours;
    private ArrayList<Tour> listeToursRef;
    private ObservableList<Allier> listeAllier;
    private Vague vague;
    private IntegerProperty PV;
    private int nbTour;
    private IntegerProperty nbEnnemis;
    private ObservableList<Projectile>listeProj;
    private int dopage;
    private Map<String, Integer> prixDesTours;
    private Sound sound = new Sound();


    public Environnement(Terrain terrain){
        this.terrain = terrain;
        this.vague = new Vague(1, 100,6,9,0,this);
        this.listeTours = FXCollections.observableArrayList();
        this.listeToursRef= new ArrayList<Tour>();
        this.addTourRef(new CanonEau(0,0,this));
        this.addTourRef(new CanonNeige(0,0,this));
        this.addTourRef(new Biathlon(0,0,this));
        this.addTourRef(new Cahute(0,0,this,false));
        this.addTourRef(new DoNotCross(0,0,this));
        this.addTourRef(new Telesiege(0,0,this));
        this.addTourRef(new Teleski(0,0,this));
        this.argent = new SimpleIntegerProperty(1500);
        this.PV = new SimpleIntegerProperty(20);
        this.capacites = new ArrayList<Capacite>();
        this.nbTour=0;
        this.nbEnnemis = new SimpleIntegerProperty(this.vague.getListEnnemis().size());
        this.listeAllier = FXCollections.observableArrayList();
        this.listeProj = FXCollections.observableArrayList();
        this.prixDesTours = new HashMap<>();
        initialiserPrixTours();
        this.dopage = 0;

    }

    public void resetEnv(){
        System.out.println("Reset env");
    }


    public Terrain getTerrain(){return this.terrain;}

    public Vague getVague(){
        return this.vague;
    }

    public void addCapacite (Capacite c){
        capacites.add(c);
    }
    public void addTourRef(Tour tr){
        listeToursRef.add(tr);
    }
    public ArrayList<Tour> getListeToursRef(){
        return listeToursRef;
    }


    public void unTour(){

        majEnnemi();
        majTour(nbTour);
        majAllier();
        majVague();
        majProjectile();
        nbTour++;

    }
    public void majTour(int nbTour){
        for (int defense = this.listeTours.size()-1; defense>=0; defense--){
            effetDopage(defense);

            this.listeTours.get(defense).agit();

            if (!this.listeTours.get(defense).estVivant()){
                mortTour(defense);
            }
        }
    }

    public void effetDopage(int defense){
        if (this.dopage>=1){
            dopage++;
        }
        if (this.dopage>=1000){
            for (int ref = this.listeToursRef.size()-1; ref>=0; ref--){
                if (listeToursRef.get(ref).getClass() == listeTours.get(defense).getClass()) {
                    this.listeTours.get(defense).setCadence(listeToursRef.get(ref).getCadence());
                    this.listeTours.get(defense).setPtsAttaque(listeToursRef.get(ref).getPtsAttaque());
                }
            }
            dopage=0;
            System.out.println(this.listeTours.get(defense).getCadence());
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

    public void majEnnemi(){
        for (int acteur = this.vague.getListEnnemis().size()-1; acteur>=0; acteur--){
            this.vague.getListEnnemis().get(acteur).agit();
            if (!this.vague.getListEnnemis().get(acteur).estVivant()){
                mortEnnemi(acteur);
            }
        }
        this.nbEnnemis.setValue(this.vague.getListEnnemis().size());
    }

    public void mortEnnemi(int acteur){
        this.vague.getListEnnemis().get(acteur).meurt();
        this.vague.getListEnnemis().remove(acteur);
    }

    public void majVague(){
        if (this.vague.getListEnnemis().isEmpty())
            this.vague.prochaineVague();
    }

    public void majAllier(){
        for (int acteur = this.listeAllier.size()-1; acteur>=0; acteur--){
//            this.listeAllier.get(acteur).agit();
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

    public void setDopage(int dopage) {
        this.dopage = dopage;
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

    public int getNbTour() {
        return nbTour;
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

    public ArrayList<Capacite> getCapacites() {
        return capacites;
    }

    public void initialiserPrixTours(){
        this.prixDesTours.put("canonEau", 500);
        this.prixDesTours.put("canonNeige", 500);
        this.prixDesTours.put("biathlon", 500);
        this.prixDesTours.put("cahute", 500);
        this.prixDesTours.put("donotcross", 500);
        this.prixDesTours.put("telesiege", 500);
        this.prixDesTours.put("teleski", 500);
        this.prixDesTours.put("allier", 500);
    }
    public Map<String, Integer> getPrixTours(){return this.prixDesTours;}
    public int getnbTour(){
        return this.nbTour;
    }

    public void playMusic(int i){
        this.sound.setFile(i);
        this.sound.play();
        this.sound.loop();
    }


    public void stopMusic(){
        this.sound.stop();
    }

    public void playSoundEffect(int i){
        this.sound.setFile(i);
        this.sound.play();
    }

}
