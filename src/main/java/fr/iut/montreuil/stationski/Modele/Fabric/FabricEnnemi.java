package fr.iut.montreuil.stationski.Modele.Fabric;


import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public abstract  class FabricEnnemi {


    private double pourcentChanceEnnemi;

    public FabricEnnemi(){
    }

    public abstract Ennemi creerEnnemi(Environnement env, Vague vague);
    public abstract boolean creationPossible(int numeroVague);

    public double getPourcentChanceEnnemi() {
        return this.pourcentChanceEnnemi;
    }

    public void setPourcentChanceEnnemi(double pourcentChanceEnnemi) {
        this.pourcentChanceEnnemi = pourcentChanceEnnemi;
    }
}