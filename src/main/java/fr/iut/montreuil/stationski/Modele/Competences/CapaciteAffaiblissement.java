package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteAffaiblissement extends CapaciteSurEnnemis {
    public CapaciteAffaiblissement(Environnement env) {
        super("Tempete", 300, env);
    }

    @Override
    public void effet(int acteur) {
        dimVitesseDeN(5, acteur);
        this.env.getVague().getListEnnemis().get(acteur).setRalenti(true);
    }
}
