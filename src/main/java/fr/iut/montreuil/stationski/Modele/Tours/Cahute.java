package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;

import java.util.ArrayList;

public class Cahute extends Tour {

    public Cahute(int posX, int posY, Environnement env) {
        super(200, posX, posY, 15, 60,50, env);
        super.setIdTour(1);
        creerAllier();

    }


    public void creerAllier (){
        // ATTENTION : les alliers ne spawn que sur la ligne de cahute pas les col actuellement

        int xCahute = getPosX();
        int yCahute = getPosY();
        int numcase0 = 0;
        int xProche = 0;
        int yProche = 0;
        int x;
        int y;
        ArrayList<Integer> List0 = new ArrayList<Integer>();

        for (int i = 0; i < this.getEnv().getTerrain().getList().size(); i++) {
            if (this.getEnv().getTerrain().getList().get(i) == 0) {
                List0.add(i);
            }
        }
        double distance = Math.sqrt((((List0.get(0) % 45) * 16) - xCahute) * (((List0.get(0) % 45) * 16) - xCahute) + (((List0.get(0) / 45) * 16) - yCahute) * (((List0.get(0) / 45) * 16) - yCahute));
        double distancePlusProche = Math.sqrt((((List0.get(0) % 45) * 16) - xCahute) * (((List0.get(0) % 45) * 16) - xCahute) + (((List0.get(0) / 45) * 16) - yCahute) * (((List0.get(0) / 45) * 16) - yCahute));

        for (int z = 0; z < List0.size(); z++) {
            xProche = (List0.get(z) % 45) * 16;
            yProche = (List0.get(z) / 45) * 16;
            distance = Math.sqrt((xProche - xCahute) * (xProche - xCahute) + (yProche - yCahute) * (yProche - yCahute));
            if (distance < distancePlusProche) {
                numcase0 = List0.get(z);
                distancePlusProche = distance;
            }
        }
        x = (numcase0 % 45) * 16;
        y = (numcase0 / 45) * 16;
        Allier a1 = new Allier(x, y, this.getEnv(), this);
        Allier a2 = new Allier(x, y, this.getEnv(), this);
        this.getEnv().ajouterAllier(a1);
        this.getEnv().ajouterAllier(a2);
    }

    @Override
    public void attaquer(){

    }
}
