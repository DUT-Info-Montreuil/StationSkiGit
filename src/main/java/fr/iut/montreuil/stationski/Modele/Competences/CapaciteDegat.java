package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Capacite;
import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteDegat extends Capacite {
    public CapaciteDegat(Environnement env) {
        super("Avalanche", 500, env);
    }
    @Override
    public void activation() {
        for (int acteur = this.env.getVague().getListEnnemis().size()-1; acteur>=0; acteur--){
            this.env.getVague().getListEnnemis().get(acteur).prendDegats(500);
        }
        this.env.retraitArgent(this.cout);
    }
}
