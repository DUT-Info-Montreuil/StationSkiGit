package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

import java.util.ArrayList;

public class GestionnaireEffet {
    // Cette classe controle la durée des capacites qui ont un effet dans le temps
    private ArrayList<Capacite> CapacitesAVerif;
    //private Environnement env;

    public GestionnaireEffet (Environnement env){
        this.CapacitesAVerif = new ArrayList<Capacite>();
        //this.env = env;
    }

    public void addCapacite(Capacite c){
        this.CapacitesAVerif.add(c);
    }

    public void surveillanceEffets(){
        for (int i =CapacitesAVerif.size()-1; i>=0 ;i--) {
            if(CapacitesAVerif.get(i).getToursEffet() < CapacitesAVerif.get(i).getTourLimite()) {
                CapacitesAVerif.get(i).addToursEffet(1);
            }
            else{
                //il n'existe qu'un objet capacité de chaque type, donc mise a 0 des tours d'effet si réutilisation
                System.out.println("arret effet capa");
                CapacitesAVerif.get(i).arreterEffet();
                CapacitesAVerif.get(i).setToursEffet(0);
                CapacitesAVerif.remove(i);
            }
        }
    }





}
