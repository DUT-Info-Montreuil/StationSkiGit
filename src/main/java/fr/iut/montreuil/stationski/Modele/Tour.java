package fr.iut.montreuil.stationski.Modele;

import fr.iut.montreuil.stationski.Modele.Projectiles.ProjectileCanon;

public abstract class Tour extends Entite {

    private int ptsAttaque;
    private int range;
    private Ennemi ennemiCible;
    private static int prix = 500;
    private int cadence;

    public Tour (int pv, int posX, int posY, int ptsAttaque, int range,int cadence, Environnement env){
        super(pv, posX, posY, env);
        this.ptsAttaque=ptsAttaque;
        this.range=range;
        this.cadence = cadence;
        this.ennemiCible=null;
    }




    public void attaquer(){

        if(ennemiCible != null  ){

            if(isInRange(ennemiCible)) {
                if(ennemiCible.estVivant()) {
                    this.env.getListeProj().add(new ProjectileCanon(this.ennemiCible, this.getPosX(), this.getPosY(), this.ptsAttaque));
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
        for(Ennemi e : this.env.getVague().getListEnnemis()){
            if(isInRange(e)){
                return e;

            }
        }
        return null;
    }

    public int getPrix(){
        return prix;
    }


    public boolean isInRange(Ennemi ennemi){
        return (Math.abs(this.getPosY()-ennemi.getPosY())<this.range && Math.abs(this.getPosX()-ennemi.getPosX())<this.range);

    }
    public int getRange(){return this.range;}
    public int getCadence() {
        return cadence;
    }

    public int getPtsAttaque() {
        return ptsAttaque;
    }

    public void augAttaque(int a){
        this.ptsAttaque+=a;
    }
    public void dimAttaque(int d){
        this.ptsAttaque-=d;
    }
    public void setPtsAttaque(int ptsAttaque) {
        this.ptsAttaque = ptsAttaque;
    }

    public void setCadence(int cadence) {
        this.cadence = cadence;
    }
    public void dimCadence(int d){
        this.cadence-=d;
    }
    public void augCadence(int a){
        this.cadence+=a;
    }
    public int getNumeroTeleski(){return -1;};
    public Ennemi getCible(){return this.ennemiCible;}
    public void setCible(Ennemi ennemiCible){this.ennemiCible=ennemiCible;}
    @Override
    public void agit() {
        if(env.getNbTour() % cadence ==0) {
            attaquer();
        }
    }
}