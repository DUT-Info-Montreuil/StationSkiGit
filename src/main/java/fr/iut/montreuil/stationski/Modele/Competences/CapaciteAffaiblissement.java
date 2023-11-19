package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteAffaiblissement extends CapaciteSurEnnemis {
    public CapaciteAffaiblissement(Environnement env) {
        super("Tempete", 300, env, 250);
    }

    @Override
    public void effet(int acteur) {
        dimVitesseDeN(5, acteur);
        this.env.getVague().getListEnnemis().get(acteur).setRalenti(true);
    }
    @Override
    public void stopEffet() {
        for (int acteur = this.env.getVague().getListEnnemis().size()-1; acteur>=0; acteur--) {
            this.env.getVague().getListEnnemis().get(acteur).setRalenti(false);
            this.env.getVague().getListEnnemis().get(acteur).setVitesse(env.getVague().getListEnnemis().get(acteur).getVitesseI());
        }
    }
    public void dimVitesseDeN(int n, int acteur){
        this.env.getVague().getListEnnemis().get(acteur).setVitesse(this.env.getVague().getListEnnemis().get(acteur).getVitesse()-n);
        if (this.env.getVague().getListEnnemis().get(acteur).getVitesse() <=0){
            this.env.getVague().getListEnnemis().get(acteur).setVitesse(1);
        }
    }
}
