package fr.iut.montreuil.stationski.Modele;

import fr.iut.montreuil.stationski.Modele.Tours.*;
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
                            if(!this.vague.getListEnnemis().get(acteur).getRalenti()){
                                this.vague.getListEnnemis().get(acteur).setRalenti(true);
                                this.vague.getListEnnemis().get(acteur).prendDegats(this.vague.getListEnnemis().get(acteur).getTaille());
                            }
                            this.listeTours.get(defense).prendDegats(1);
//                            this.vague.getListEnnemis().get(acteur).augmVitesseDeN(5);
                        }
                    }
                }
            }
            // fin DoNotCross
            // en lien avec capacité dopage
            if (this.dopage>=1){
                dopage++;
                System.out.println("dop : "+this.listeTours.get(defense).getCadence());
            }
            if (this.dopage>=1000){
                for (int ref = this.listeToursRef.size()-1; ref>=0; ref--){
                    if (listeToursRef.get(ref).getClass() == listeTours.get(defense).getClass()) {
                        this.listeTours.get(defense).setCadence(listeToursRef.get(ref).getCadence());
                        this.listeTours.get(defense).setPtsAttaque(listeToursRef.get(ref).getPtsAttaque());
                    }
                }
                dopage=0;
                System.out.println("dop terminé");
                System.out.println(this.listeTours.get(defense).getCadence());
            }
            //fin capa dopage

            this.listeTours.get(defense).agit();

            //non testé : fonctionnement théroque de la suppression d'une tour ET de la case en dessous (qui est de 5)
            if (!this.listeTours.get(defense).estVivant()){
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

    public void majAllier(){
//        if (listeAllier.size()!= 0) {
//            for (int a = listeAllier.size()-1; a >= 0; a--) {
//                listeAllier.get(a).prendDegats(1);
//                System.out.println("d");
//            }
//        }
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

    /*  public int getnbTour(){
        return tour;
    }
*/
}
