package fr.iut.montreuil.stationski.Modele;

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
