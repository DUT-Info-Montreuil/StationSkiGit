package fr.iut.montreuil.stationski.Modele.Competences;


import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Environnement;

import java.util.ArrayList;

public abstract class Capacite {
    protected String nom;
    protected int cout;
    protected Environnement env;
    protected int nbToursSousEffet;
    //nb de tour ou la capacité a été activée (=nb de tours où l'effet est en cours)
    protected int nbToursAnePasDepasser;
    //le nb de tours à ne pas dépasser
    // ATTENTION : si capa a effet immédiat (= pas dans le temps), mettre cette valeur à 1
    protected boolean active;

    public Capacite (String nom, int cout, Environnement env, int nbToursAnePasDepasser){
        this.nom =nom;
        this.cout = cout;
        this.env=env;
        this.nbToursSousEffet = 0;
        this.nbToursAnePasDepasser = nbToursAnePasDepasser;
        active=false;
    }
    public int getNbToursAnePasDepasser() {
        return nbToursAnePasDepasser;
    }

    public int getNbToursSousEffet() {
        return nbToursSousEffet;
    }

    public void addToursEffet(int toursEffetEnPlus) {
        nbToursSousEffet += toursEffetEnPlus;
    }

    public void setNbToursSousEffet(int nbToursSousEffet) {
        this.nbToursSousEffet = nbToursSousEffet;
    }

    public abstract void effet(int acteur); // codé par la capacité la plus basse
    public abstract void stopEffet(); // codé par la capacité la plus basse
    public abstract void parcours(); // codé par la capaSurTour ou capaSurEnnemi
    public void activation (){
        //quand activation, parcours des acteurs, activation effet et gestion du temps d'effet par gestioEffets
        parcours();
        active = true;
    }
    public String getNom() {
        return nom;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean getActive(){
        return active;
    }

    public int getCout() {
        return cout;
    }


}
