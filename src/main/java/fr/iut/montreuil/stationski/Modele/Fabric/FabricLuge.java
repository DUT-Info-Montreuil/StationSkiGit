package fr.iut.montreuil.stationski.Modele.Fabric;


import fr.iut.montreuil.stationski.Modele.Dijkstra;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Ennemis.Luge;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public class FabricLuge extends FabricEnnemi {

    public FabricLuge() {
        super();
    }

    @Override
    public Ennemi creerEnnemi(Environnement env, Vague vague) {
        if (creationPossible(vague.numeroVagueProperty().getValue()))
            return new Luge(env.getTerrain().getSource().getX() * 16, env.getTerrain().getSource().getY() * 16, env, new Dijkstra(env.getTerrain()), vague);
        return null;
    }

    @Override
    public boolean creationPossible(int numeroVague) {
        if(numeroVague <=2)
            this.setPourcentChanceEnnemi(0);
        else if (numeroVague < 5 )
            this.setPourcentChanceEnnemi(-(double) (0.65 / 3) * numeroVague -2);
        else
            this.setPourcentChanceEnnemi(0.8 * this.getPourcentChanceEnnemi());

        return Math.random() < this.getPourcentChanceEnnemi();
    }

}