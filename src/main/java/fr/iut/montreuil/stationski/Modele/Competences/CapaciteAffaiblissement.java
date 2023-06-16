package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Capacite;
import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteAffaiblissement extends Capacite {
    public CapaciteAffaiblissement(Environnement env) {
        super("Tempete", 300, env);
    }

    @Override
    public void activation() {
        for (int acteur = this.env.getVague().getListEnnemis().size()-1; acteur>=0; acteur--){
            this.env.getVague().getListEnnemis().get(acteur).setFinTourR(1000);
            this.env.getVague().getListEnnemis().get(acteur).setRalenti(true);
        }
        this.env.retraitArgent(this.cout);
    }
}
