package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteBoost extends CapaciteSurTours {
    public CapaciteBoost(Environnement env) {
        super("Dopage", 500, env, 700);
    }

    @Override
    public void effet(int acteur) {
        MultiplicationAttaque(2, acteur);
        MultiplicationCadence(2, acteur);
    }
    public void MultiplicationAttaque(int nb, int acteur){
        this.env.getListeTours().get(acteur).setPtsAttaque(this.env.getListeTours().get(acteur).getPtsAttaque()*nb);
    }
    public void MultiplicationCadence(int nb, int acteur){
        this.env.getListeTours().get(acteur).setCadence(this.env.getListeTours().get(acteur).getCadence()*nb);
    }
    @Override
    public void stopEffet() {
        for (int acteur = this.env.getListeTours().size()-1; acteur>=0; acteur--) {
            this.env.getListeTours().get(acteur).setCadence(env.getListeTours().get(acteur).getCadenceInit());
            this.env.getListeTours().get(acteur).setPtsAttaque(env.getListeTours().get(acteur).getPtsAttaqueInit());
        }
    }


}
