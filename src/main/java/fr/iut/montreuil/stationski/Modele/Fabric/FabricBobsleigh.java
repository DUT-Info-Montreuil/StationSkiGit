package fr.iut.montreuil.stationski.Modele.Fabric;


import fr.iut.montreuil.stationski.Modele.Dijkstra;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Ennemis.Bobsleigh;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public class FabricBobsleigh extends FabricEnnemi {

    public FabricBobsleigh() {
        super();
    }

    @Override
    public Ennemi creerEnnemi(Environnement env, Vague vague) {
        if (creationPossible(vague.numeroVagueProperty().getValue()))
            return new Bobsleigh(env.getTerrain().getSource().getX() * 16, env.getTerrain().getSource().getY() * 16, env, new Dijkstra(env.getTerrain()), vague);
        return null;
    }

    @Override
    public boolean creationPossible(int numeroVague) {
        if (numeroVague < 4)
            this.setPourcentChanceEnnemi(0);
        else if (numeroVague < 8)
            this.setPourcentChanceEnnemi(0.75 + this.getPourcentChanceEnnemi()*1.2);
        else
            this.setPourcentChanceEnnemi(0.6);
        return Math.random() < this.getPourcentChanceEnnemi();
    }

}