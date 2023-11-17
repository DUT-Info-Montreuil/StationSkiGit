package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteDegat extends CapaciteSurEnnemis {
    public CapaciteDegat(Environnement env) {
        super("Avalanche", 1000, env,1);
    }
    @Override
    public void effet(int acteur) {
        this.env.getVague().getListEnnemis().get(acteur).setPV(0);
    }

    @Override
    public void stopEffet() {
        // pas d'undo pour cet effet
    }


}
