package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Capacite;
import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteBoost extends Capacite {
    public CapaciteBoost(Environnement env) {
        super("Dopage", 500, env);
    }

    @Override
    public void activation() {
        for (int acteur = this.env.getListeTours().size()-1; acteur>=0; acteur--){

            this.env.getListeTours().get(acteur).MultiplicationAttaque(2);
            this.env.setDopage(1);
        }
        this.env.retraitArgent(this.cout);
    }
}
