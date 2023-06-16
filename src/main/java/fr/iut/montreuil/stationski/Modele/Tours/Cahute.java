package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Cahute extends Tour {

    public Cahute(int posX, int posY, Environnement env, boolean b) {
        super(10, posX, posY, 50, 50,50, env);
        creerAllier(b);
    }

    public int MiseCarree (int nombre){
        return nombre*nombre;
    }

    public void creerAllier (boolean b){
        // ATTENTION : les alliers ne spawn que sur la ligne de cahute pas les col actuellement
        if (b) {
            int numcaseori = (getPosY()/16)*45+(getPosX()/16);
            int xCahute = getPosX();
            int yCahute = getPosY();
            int numcase0 = 0;
            int xProche =0;
            int yProche =0;
            int x;
            int y;
            ArrayList<Integer> List0 = new ArrayList<Integer>();

            for (int i =0; i<this.env.getTerrain().getList().size(); i++){
                if (this.env.getTerrain().getList().get(i) == 0){
                    List0.add(i);
                }
            }
            double distance = Math.sqrt(MiseCarree(((List0.get(0)%45)*16)-xCahute) + MiseCarree(((List0.get(0)/45)*16)-yCahute));
            double distancePlusProche =Math.sqrt(MiseCarree(((List0.get(0)%45)*16)-xCahute) + MiseCarree(((List0.get(0)/45)*16)-yCahute));

            for (int z =0; z<List0.size(); z++){
                    xProche = (List0.get(z)%45)*16;
                    yProche = (List0.get(z)/45)*16;
                    distance = Math.sqrt(MiseCarree(xProche-xCahute) + MiseCarree(yProche-yCahute));
                    if (distance < distancePlusProche){
                        numcase0 = List0.get(z);
                        distancePlusProche = distance;
                    }
            }
            // attention, ici 45 car la map et en 45*45
            x = (numcase0%45)*16;
            y= (numcase0/45)*16;
            Allier a1 = new Allier(10, x, y, env, this);
            Allier a2 = new Allier(50, x, y, env, this);
            env.ajouterAllier(a1);
            env.ajouterAllier(a2);
        }
    }

    public void agit(){
        if (this.env.getListeAllier().size()%2 != 0){
            Allier am = new Allier(50,50,10,env, this);
            env.ajouterAllier(am);
        }
    }
}
