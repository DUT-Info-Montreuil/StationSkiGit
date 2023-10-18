package fr.iut.montreuil.stationski.Modele.Fabric;


import fr.iut.montreuil.stationski.Modele.Dijkstra;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Ennemis.SkieurBasique;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public class FabricSkieur  extends FabricEnnemi {


    public FabricSkieur(int numeroVague) {
        super(numeroVague);

    }

    @Override
    public Ennemi creerEnnemi(Environnement env, Vague vague) {
        if (creationPossible())
            return new SkieurBasique(env.getTerrain().getSource().getX() * 16, env.getTerrain().getSource().getY() * 16, env, new Dijkstra(env.getTerrain()), vague);
        return null;
    }

    @Override
    public boolean creationPossible() {


        if (this.getNumeroVague() > 3)
            this.setPourcentChanceEnnemi(-(double) (0.6 / 3) * this.getNumeroVague() + 0.8);
        else
            this.setPourcentChanceEnnemi(0.75 * this.getPourcentChanceEnnemi());


        return Math.random() < this.getPourcentChanceEnnemi();


    }

}