package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteDegat extends CapaciteEnnemis {
    public CapaciteDegat(Environnement env) {
        super("Avalanche", 1000, env);
    }
    @Override
    public void effet(int acteur) {
        this.env.getVague().getListEnnemis().get(acteur).setPV(0);
    }


}
