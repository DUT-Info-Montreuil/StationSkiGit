package fr.iut.montreuil.stationski.CreationMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CreationMap {
    private int w;
    private int h;
    private  ObservableList<Integer> tuile;

    public CreationMap(int w, int h){
            this.w = w;
            this.h = h;

            this.tuile = FXCollections.observableArrayList();;
    }

    public void construit() {
        int i;
        for (i = 0; i < this.w; i++) {
            for (int j = 0; j < this.h; j++)
                this.tuile.add(45);
        }

    }


    private boolean dansGrille(int x, int y) {
        return (x >= 0 && x < this.w && y >= 0 && y < this.h);
    }

    public void poseTuile(int pourcent) {
        for (int i = 0; i < this.w * this.h * pourcent / 100; i++) {
            int aleaX = (int) (Math.random() * this.w);
            int aleaY = (int) (Math.random() * this.h);
            //this.obstacles.add(getSommet(aleaX, aleaY));
        }
    }
    public ObservableList<Integer> getTuile(){return this.tuile;}

    public void setTuiles(int index, int value){
        tuile.set(index, value);
    }

    public String toString() {
        return "Grille [w=" + this.w + ", h=" + this.h + ", listeAdj=" + "]";
    }
}
