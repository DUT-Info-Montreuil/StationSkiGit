package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteBoost extends CapaciteSurTours {
    public CapaciteBoost(Environnement env) {
        super("Dopage", 500, env);
    }

    @Override
    public void effet(int acteur) {
        MultiplicationAttaque(2, acteur);
        this.env.getGestioEffets().setDopage(1);
    }


}
