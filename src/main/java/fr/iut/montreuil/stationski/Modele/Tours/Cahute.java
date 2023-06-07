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
        if (b) {
            Allier a1 = new Allier(10, 0, 0, env);
            Allier a2 = new Allier(10, 1, 1, env);
            Allier a3 = new Allier(10, 2, 2, env);
            env.ajouterAllier(a1);
            env.ajouterAllier(a2);
            env.ajouterAllier(a3);
            System.out.println("alliés creer");
        }
    }
}
