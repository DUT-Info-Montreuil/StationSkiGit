package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

import java.util.ArrayList;

public class GestionnaireEffet {
    // Cette classe controle la durée des capacites qui ont un effet dans le temps
    private ArrayList<Capacite> CapacitesAVerif;

    public GestionnaireEffet (){
        this.CapacitesAVerif = new ArrayList<Capacite>();
    }

    public void addCapacite(Capacite c){
        this.CapacitesAVerif.add(c);
    }

    public ArrayList<Capacite> getCapacitesAVerif() {
        return CapacitesAVerif;
    }

    public void surveillanceEffets(){
        for (int i =CapacitesAVerif.size()-1; i>=0 ;i--) {
            if (CapacitesAVerif.get(i).getActive()) {
                if (CapacitesAVerif.get(i).getNbToursSousEffet() < CapacitesAVerif.get(i).getNbToursAnePasDepasser()) {
                    CapacitesAVerif.get(i).addToursEffet(1);
                } else {
                    //il n'existe qu'un objet capacité de chaque type, donc mise a 0 des tours d'effet si réutilisation
                    System.out.println("arret effet capa");
                    CapacitesAVerif.get(i).stopEffet();
                    CapacitesAVerif.get(i).setNbToursSousEffet(0);
                    CapacitesAVerif.get(i).setActive(false);
                    //CapacitesAVerif.remove(i);
                }
            }
        }
    }





}
