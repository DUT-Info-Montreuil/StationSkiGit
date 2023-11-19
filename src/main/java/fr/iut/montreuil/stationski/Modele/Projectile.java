package fr.iut.montreuil.stationski.Modele;


public abstract class Projectile extends Entite{
    private Acteur cible;
    private double ptsAttaque;
    public Projectile(Acteur cible,  int posX, int posY, double ptsAttaque){
        super(posX, posY);
        this.cible = cible;
        this.ptsAttaque = ptsAttaque;
    }
    public double getPtsAttaque(){return this.ptsAttaque;}
    public Acteur getCible() {
        return this.cible;
    }

    public String getIdProj(){return this.getId();}

    public abstract boolean attaque();

    public void agit(){
        attaque();
    }

}
