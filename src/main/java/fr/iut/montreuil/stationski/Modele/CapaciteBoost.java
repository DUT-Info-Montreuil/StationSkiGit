package fr.iut.montreuil.stationski.Modele;

public class CapaciteBoost extends Capacite{
    // apparement non fonctionnel
    public CapaciteBoost(Environnement env) {
        super("Dopage", 500, env);
    }

    @Override
    public void activation() {
        for (int acteur = this.env.getListeTours().size()-1; acteur>=0; acteur--){

            this.env.getListeTours().get(acteur).dimCadence(100);
            this.env.getListeTours().get(acteur).augAttaque(50);
            this.env.setDopage(1);
        }
        this.env.retraitArgent(this.cout);
    }
}
