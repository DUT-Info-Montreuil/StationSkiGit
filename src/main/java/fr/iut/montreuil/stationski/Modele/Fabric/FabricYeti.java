package fr.iut.montreuil.stationski.Modele.Fabric;


import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Ennemis.Yeti;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public class FabricYeti extends FabricEnnemi {

    public FabricYeti() {
        super();
    }

    @Override
    public Ennemi creerEnnemi(Environnement env, Vague vague) {
        if (creationPossible(vague.numeroVagueProperty().getValue()))
            return new Yeti(env.getTerrain().getSource().getX() * 16, env.getTerrain().getSource().getY() * 16,  env,  vague);
        return null;
    }

    @Override
    public boolean creationPossible(int numeroVague) {
        if(numeroVague < 9)
            this.setPourcentChanceEnnemi(0);
        else
            this.setPourcentChanceEnnemi(0.5 + this.getPourcentChanceEnnemi()*((double)(1/numeroVague)));

        return Math.random() < this.getPourcentChanceEnnemi();
    }

}