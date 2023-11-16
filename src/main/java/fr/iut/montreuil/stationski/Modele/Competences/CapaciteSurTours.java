package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public abstract class CapaciteSurTours extends Capacite{
    public CapaciteSurTours(String nom, int cout, Environnement env, int TourLimite) {
        super(nom, cout, env, TourLimite);
    }

    @Override
    public abstract void effet(int acteur);
    public abstract void stopEffet(int acteur);

    @Override
    public void parcours() {
        for(int acteur = this.env.getListeTours().size()-1; acteur>=0; acteur--){
            effet(acteur);
            super.addActeur(this.env.getVague().getListEnnemis().get(acteur));
        }
        this.env.retraitArgent(this.cout);
    }

}
