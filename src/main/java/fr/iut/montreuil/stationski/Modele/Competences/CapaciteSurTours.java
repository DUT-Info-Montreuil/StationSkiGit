package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public abstract class CapaciteSurTours extends Capacite{
    public CapaciteSurTours(String nom, int cout, Environnement env, int TourLimite) {
        super(nom, cout, env, TourLimite);
    }

    @Override
    public abstract void effet(int acteur); // template
    public abstract void stopEffet();

    @Override
    public void parcours() {
        // parcours des batiments présent et déclenchement de l'effet
        for(int acteur = this.env.getListeTours().size()-1; acteur>=0; acteur--){
            effet(acteur);
        }
        this.env.retraitArgent(this.cout);
    }

}
