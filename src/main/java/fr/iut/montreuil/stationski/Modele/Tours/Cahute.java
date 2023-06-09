package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Cahute extends Tour {

    public Cahute(int posX, int posY, Environnement env, boolean b) {
        super(10, posX, posY, 50, 50, env);
        creerAllier(b);
    }

    public void creerAllier (boolean b){
        // ATTENTION : les alliers ne spawn que sur la ligne de cahute pas les col actuellement
        if (b) {
            int numcaseori = (getPosY()/16)*45+(getPosX()/16);
            int numcase0 = 0;
            int differenceI =0;
            int differenceSelect=0;
            int x=0;
            int y=0;
            for (int i =0; i<this.env.getTerrain().getList().size(); i++){
                if (this.env.getTerrain().getList().get(i) == 0){
                    differenceI = Math.abs(i - numcaseori);
                    differenceSelect = Math.abs(numcase0 - numcaseori);
                    if (differenceI<differenceSelect){
                        numcase0 = i;
                    }
                }
            }
            // attention, ici 45 car la map et en 45*45
            x = (numcase0%45)*16;
            y= (numcase0/45)*16;
            Allier a1 = new Allier(10, x, y, env);
            Allier a2 = new Allier(50, x, y, env);
            Allier a3 = new Allier(90, x, y, env);
            env.ajouterAllier(a1);
            env.ajouterAllier(a2);
            env.ajouterAllier(a3);
            System.out.println("alliés creer");
        }
    }

    public void agit(){
        if (this.env.getListeAllier().size()%3 != 0){
            Allier am = new Allier(50,50,10,env);
            env.ajouterAllier(am);
            System.out.println("un allié de recreer");
        }
    }
}
