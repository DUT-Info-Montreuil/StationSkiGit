package fr.iut.montreuil.stationski.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Grille {
    private int largeur;
    private int hauteur;
    private Sommet source;
    private Sommet cible;
    private Terrain terrain;
    private HashMap<Sommet, Set<Sommet>> listeAdj;


    public Grille(Terrain terrain, Sommet source, Sommet cible) {
        this.terrain=terrain;
        this.largeur = (terrain.getLargeurCase());
        this.hauteur = (terrain.getHauteurCase());
        this.source=source;
        this.cible=cible;
        this.listeAdj = new HashMap<>();
        this.build();
    }

    // Cette méthode crée tous les Set Adj et initialise tous les sommets
    public void build() {

        for (int i = 0; i < this.largeur; i++) {

            for (int j = 0; j < this.hauteur; j++) {
                if (this.terrain.getList().get((j*45)+i)==0 || this.terrain.getList().get((j*45)+i)==5) {
                    if (i == this.source.getX() && j == this.source.getY()) {
                        this.listeAdj.put(this.source, new HashSet<Sommet>());
                    }else if (i == this.cible.getX() && j == this.cible.getY()) {
                        this.listeAdj.put(this.cible, new HashSet<Sommet>());
                    }else
                        this.listeAdj.put(new Sommet(i, j, false), new HashSet<Sommet>());
                }

            }

        }

        for(Sommet s : this.listeAdj.keySet()){

            if (this.dansGrille(s.getX() - 1, s.getY())) {
                ( this.listeAdj.get(s)).add(this.getSommet(s.getX() - 1, s.getY()));
            }

            if (this.dansGrille(s.getX() + 1, s.getY()) ) {
                ( this.listeAdj.get(s)).add(this.getSommet(s.getX() + 1, s.getY()));
            }

            if (this.dansGrille(s.getX(), s.getY() + 1) ) {
                ( this.listeAdj.get(s)).add(this.getSommet(s.getX(), s.getY() + 1));
            }

            if (this.dansGrille(s.getX(), s.getY() - 1) ) {
                (this.listeAdj.get(s)).add(this.getSommet(s.getX(), s.getY() - 1));
            }

            //System.out.println("sommet : " + s+ ", adj" + this.listeAdj.get(s));

        }
    }

    // Cette méthode recherche un sommet par ses coordonnées parmi la listeAdj
    public Sommet getSommet (int x, int y){
        Iterator<Sommet> ListAdj = this.listeAdj.keySet().iterator();
        Sommet sommet;

        do{
            if (!ListAdj.hasNext()) {
                System.out.println("x "+ x+ " y "+ y);
                return null;
            }
            sommet = (Sommet)ListAdj.next();
        }while(sommet.getX() != x || sommet.getY() != y);
        //System.out.println("Methode getSommet :  x : " + x + "; y : "+ y + "sommet "+ sommet);
        return sommet;
    }

    public boolean dansGrille (int x, int y){return x >= 0 && x < this.largeur && y >= 0 && y < this.hauteur && this.terrain.getList().get(x+(y*45))==0;}



    public Set<Sommet> getVoisins(Sommet s) {

        return this.listeAdj.get(s);
    }
}
