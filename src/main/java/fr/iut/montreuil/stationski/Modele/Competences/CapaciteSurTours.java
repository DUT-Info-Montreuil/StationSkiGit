package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public abstract class CapaciteSurTours extends Capacite{
    public CapaciteSurTours(String nom, int cout, Environnement env) {
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
    public void MultiplicationAttaque(int nb, int acteur){
        this.env.getListeTours().get(acteur).setPtsAttaque(this.env.getListeTours().get(acteur).getPtsAttaque()*nb);
    }
}
