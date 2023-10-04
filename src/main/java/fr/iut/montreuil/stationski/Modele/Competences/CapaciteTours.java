package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public abstract class CapaciteTours extends Capacite{
    public CapaciteTours(String nom, int cout, Environnement env) {
        super(nom, cout, env);
    }

    @Override
    public abstract void effet(int acteur);

    @Override
    public void parcours() {
        for(int acteur = this.env.getListeTours().size()-1; acteur>=0; acteur--){
            effet(acteur);
        }
        this.env.retraitArgent(this.cout);
    }
}
