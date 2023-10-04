package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteAffaiblissement extends Capacite {
    public CapaciteAffaiblissement(Environnement env) {
        super("Tempete", 300, env);
    }

    @Override
    public void effet(int acteur) {
        this.env.getVague().getListEnnemis().get(acteur).setFinTourR(1000);
        this.env.getVague().getListEnnemis().get(acteur).setRalenti(true);
    }
}
