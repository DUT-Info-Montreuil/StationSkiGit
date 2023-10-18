package fr.iut.montreuil.stationski.Modele.Fabric;


import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Vague;

public abstract  class FabricEnnemi {
    private double pourcentChanceEnnemi;

    private int numeroVague;


    public FabricEnnemi(int numeroVague){
        this.numeroVague = numeroVague;
    }

    public int getNumeroVague(){return this.numeroVague;}

    public abstract Ennemi creerEnnemi(Environnement env, Vague vague);
    public abstract boolean creationPossible();

    public void setPourcentChanceEnnemi(double pourcentChanceEnnemi){this.pourcentChanceEnnemi = pourcentChanceEnnemi;}

    public double getPourcentChanceEnnemi() {
        return pourcentChanceEnnemi;
    }


}