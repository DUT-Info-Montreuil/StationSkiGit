package fr.iut.montreuil.stationski.Modele;



import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Grille;
import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;

import java.util.*;

public class Dijkstra {


    private Sommet cible;
    private Sommet source;
    private Grille grille;
    private ArrayList <Sommet> parcours ;
    private Map<Sommet, Sommet> predecesseurs;


    // TO DO : coder classe Sommet
    //         coder classe tableau2D
    //         coder methode voisins dans tableau2D

    public Dijkstra (Terrain terrain){
        this.cible = terrain.getCible();
        this.source = terrain.getSource();
        this.grille = new Grille(terrain, this.source, this.cible);
        predecesseurs = new HashMap<>();
        parcours= new ArrayList<Sommet>();
        algo();
    }


    public void algo (){
        LinkedList<Sommet> listeaAnalyser = new LinkedList<>();
        listeaAnalyser.add(this.source);
        this.source.setReached(true);
        this.cible.setReached(false);
        Sommet courant;
        while (!listeaAnalyser.isEmpty() && !cible.isReached()) {

            courant = listeaAnalyser.poll();



            if(grille.getVoisins(courant)!=null) {

                Iterator<Sommet> ListAdj = grille.getVoisins(courant).iterator();
                Sommet sommet;


                while (ListAdj.hasNext() && !cible.isReached()) {
                    sommet = ListAdj.next();

                    if (!sommet.isReached()) {
                        listeaAnalyser.add(sommet);
                        predecesseurs.put(sommet, courant);
                        sommet.setReached(true);
                    }
                }
            }
        }
        if (cible.isReached()){
            courant = this.cible;
            while (!courant.equals(this.source)){
                parcours.add(courant);
                courant = predecesseurs.get(courant);
            }
        }



    }

    private void clear() {
        this.predecesseurs.clear();
        this.parcours.clear();
    }




    /*************************************************
     **** Pas de modifications à faire ci-dessous ****
     *************************************************/

    public ArrayList<Sommet> getParcours() {
        return parcours;
    }

    public Map<Sommet, Sommet> getPredecesseurs() {
        return predecesseurs;
    }

    public Sommet getSource() {
        return source;
    }

    public void setSource(Sommet source) {
        this.source = source;
        clear();
        algo();
    }

    public Grille getGrille(){return this.grille;}

    public Sommet getCible(){return this.cible;}
}