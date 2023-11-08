package fr.iut.montreuil.stationski.Modele.Fabric;


import fr.iut.montreuil.stationski.Modele.Dijkstra;
import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Ennemis.SkieurBasique;
import fr.iut.montreuil.stationski.Modele.Ennemis.Snowboarder;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public class FabricSnowboarder extends FabricEnnemi {

    public FabricSnowboarder() {
        super();
    }

    @Override
    public Ennemi creerEnnemi(Environnement env, Vague vague) {
        if (creationPossible(vague.numeroVagueProperty().getValue())) {
            return new Snowboarder(env.getTerrain().getSource().getX() * 16, env.getTerrain().getSource().getY() * 16, env, new Dijkstra(env.getTerrain()), vague);
        }    return null;
    }

    @Override
    public boolean creationPossible(int numeroVague) {
        if (numeroVague<=3)

            this.setPourcentChanceEnnemi(-(double) (1 / 6) * numeroVague + 0.25);

        else
            this.setPourcentChanceEnnemi(0.8*this.getPourcentChanceEnnemi());

        return Math.random() < this.getPourcentChanceEnnemi();
    }

}