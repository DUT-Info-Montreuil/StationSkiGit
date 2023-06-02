package fr.iut.montreuil.stationski.Modele.Ennemis;


import fr.iut.montreuil.stationski.Modele.Dijkstra;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

import java.util.ArrayList;

public class SkieurBasique extends Ennemi {


    public SkieurBasique(int pv, int posX, int posY, int vitesse, Environnement env, int butin, Dijkstra dijkstra, Vague vague) {
        super(pv, posX, posY, vitesse, env, butin, dijkstra,  vague, 1);
    }




}
