package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public abstract class CapaciteSurEnnemis extends Capacite{


    public CapaciteSurEnnemis(String nom, int cout, Environnement env, int TourLimite) {
        super(nom, cout, env, TourLimite);
    }

    public abstract void effet(int acteur);
    public abstract void stopEffet(int acteur);
    @Override
    public void parcours() {
        for (int acteur = this.env.getVague().getListEnnemis().size()-1; acteur>=0; acteur--){
            effet(acteur);
            super.addActeur(this.env.getVague().getListEnnemis().get(acteur));
        }
        this.env.retraitArgent(this.cout);
    }


}
