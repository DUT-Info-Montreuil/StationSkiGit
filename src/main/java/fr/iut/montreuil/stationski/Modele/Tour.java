package fr.iut.montreuil.stationski.Modele;

import fr.iut.montreuil.stationski.Modele.Projectiles.ProjectileCanon;

public abstract class Tour extends Acteur {

    private double ptsAttaque;
    private double ptsAttaqueInit;
    private int range;
    private Ennemi ennemiCible;
    private double cadence;
    private double cadenceInit;

    public Tour (int pv, int posX, int posY, double ptsAttaque, int range,double cadence, Environnement env){
        super(pv, posX, posY, env);
        this.ptsAttaque=ptsAttaque;
        this.ptsAttaqueInit = ptsAttaque;
        this.range=range;
        this.cadence = cadence;
        this.cadenceInit=cadence;
        this.ennemiCible=null;
    }


    public void attaquer(){
        if(ennemiCible != null  ){
            if(isInRange(ennemiCible)) {
                if(ennemiCible.estVivant()) {
                    this.getEnv().getListeProj().add(new ProjectileCanon(this.ennemiCible, this.getPosX(), this.getPosY(), this.ptsAttaque));
                    //super.env.playSoundEffect(1);
                } else ennemiCible = null;
            }
            else{
                ennemiCible= searchEnnemi();
                attaquer();
            }
        }else{
            ennemiCible = searchEnnemi();
        }
    }

    public Ennemi searchEnnemi(){
        for(Ennemi e : this.getEnv().getVague().getListEnnemis()){
            if(isInRange(e)){
                return e;

            }
        }
        return null;
    }

    public double getCadenceInit() {
        return cadenceInit;
    }

    public double getPtsAttaqueInit() {
        return ptsAttaqueInit;
    }

    public boolean isInRange(Ennemi ennemi){
        return (Math.abs(this.getPosY()-ennemi.getPosY())<this.range && Math.abs(this.getPosX()-ennemi.getPosX())<this.range);

    }
    public int getRange(){return this.range;}
    public double getPtsAttaque() {
        return ptsAttaque;
    }

    public void setPtsAttaque(double ptsAttaque) {
        this.ptsAttaque = ptsAttaque;
    }

    public void setCadence(double cadence) {
        this.cadence = cadence;
    }
    public Ennemi getCible(){return this.ennemiCible;}
    public void setCible(Ennemi ennemiCible){this.ennemiCible=ennemiCible;}
    public double getCadence(){return this.cadence;}
    @Override
    public void agit() {
        if(this.getEnv().getNbTour() % this.getCadence() ==0) {
            attaquer();
        }
    }
}