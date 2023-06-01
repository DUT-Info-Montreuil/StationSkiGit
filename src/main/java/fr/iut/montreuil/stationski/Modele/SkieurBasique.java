package fr.iut.montreuil.stationski.Modele;


import java.util.ArrayList;

public class SkieurBasique extends Ennemi{


    public SkieurBasique(int pv, int posX, int posY, int vitesse, Environnement env, int butin, Dijkstra dijkstra, Vague vague) {
        super(pv, posX, posY, vitesse, env, butin, dijkstra,  vague, 1);
    }




}
