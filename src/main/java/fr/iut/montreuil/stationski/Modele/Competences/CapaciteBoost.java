package fr.iut.montreuil.stationski.Modele.Competences;

import fr.iut.montreuil.stationski.Modele.Environnement;

public class CapaciteBoost extends CapaciteSurTours {
    public CapaciteBoost(Environnement env) {
        super("Dopage", 500, env, 700);
    }

    @Override
    public void effet(int acteur) {
        MultiplicationAttaque(2, acteur);
        //this.env.getGestioEffets().setDopage(1);
    }
    public void MultiplicationAttaque(int nb, int acteur){
        this.env.getListeTours().get(acteur).setPtsAttaque(this.env.getListeTours().get(acteur).getPtsAttaque()*nb);
    }
    @Override
    public void stopEffet(int acteur) {
        env.getListeTours().get(acteur).setCadence(env.getListeTours().get(acteur).getCadenceInit());
        env.getListeTours().get(acteur).setPtsAttaque(env.getListeTours().get(acteur).getPtsAttaqueInit());
    }


}
