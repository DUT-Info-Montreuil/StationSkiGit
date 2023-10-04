package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Capacite;
import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteBoost extends Capacite {
    public CapaciteBoost(Environnement env) {
        super("Dopage", 500, env);
    }

    @Override
    public void effet(int acteur) {
        this.env.getListeTours().get(acteur).MultiplicationAttaque(2);
        this.env.setDopage(1);
    }
}
