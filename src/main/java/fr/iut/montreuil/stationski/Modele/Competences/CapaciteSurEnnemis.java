package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public abstract class CapaciteSurEnnemis extends Capacite{


    public CapaciteSurEnnemis(String nom, int cout, Environnement env, int TourLimite) {
        super(nom, cout, env, TourLimite);
    }

    public abstract void effet(int acteur); //template
    public abstract void stopEffet();
    @Override
    public void parcours() {
        // parcours des ennemis présent et déclenchement de l'effet
        for (int acteur = this.env.getVague().getListEnnemis().size()-1; acteur>=0; acteur--){
            effet(acteur);
            super.addActeur(this.env.getVague().getListEnnemis().get(acteur));
        }
        this.env.retraitArgent(this.cout);
    }


}
