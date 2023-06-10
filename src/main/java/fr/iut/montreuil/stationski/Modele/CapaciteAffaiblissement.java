package fr.iut.montreuil.stationski.Modele;

public class CapaciteAffaiblissement extends Capacite{
    public CapaciteAffaiblissement(Environnement env) {
        super("Tempete", 500, env);
    }

    @Override
    public void activation() {
        for (int acteur = this.env.getVague().getListEnnemis().size()-1; acteur>=0; acteur--){
            this.env.getVague().getListEnnemis().get(acteur).setRalenti(true);
            // coder dim des dégats qu'ils font quand notion ajoutée
        }
        this.env.retraitArgent(this.cout);
    }
}
