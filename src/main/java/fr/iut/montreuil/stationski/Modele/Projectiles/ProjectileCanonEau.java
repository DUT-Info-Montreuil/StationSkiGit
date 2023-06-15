package fr.iut.montreuil.stationski.Modele.Projectiles;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Tours.CanonEau;
import fr.iut.montreuil.stationski.Modele.Tours.Telesiege;

public class ProjectileCanonEau extends Projectile {
    private double coefA;
    private double coefB;
    private int cibleX;
    private int cibleY;
    private int xOriginel;
    private int yOriginel;
    private int vitesse;

    public ProjectileCanonEau(Ennemi cible, int ptsAttaque, CanonEau canonEau, double coefA, double coefB) {
        super(cible, canonEau.getPosX(), canonEau.getPosY(), ptsAttaque);
        this.coefA = coefA;
        this.coefB = coefB;
        this.cibleX = cible.getPosX();
        this.cibleY = cible.getPosY();
        this.xOriginel = canonEau.getPosX();
        this.yOriginel = canonEau.getPosY();
        this.vitesse=1;

    }


    @Override
    public boolean attaque(){
        if(Math.abs(this.getPosX()-this.cibleX)>4 || Math.abs(this.getPosY()-this.cibleY)>4){

            if(Math.abs(this.xOriginel-this.cibleX)>64){
                if (this.cibleX<this.getPosX())
                    this.setPosX(this.getPosX()-vitesse);
                else
                    this.setPosX(this.getPosX()+vitesse);
                if(this.coefA!=0) this.setPosY((int)(this.coefA*this.getPosX() + this.coefB));
            }
            else if (Math.abs(this.yOriginel-this.cibleY)>64){
                if (this.yOriginel<this.getPosY()){
                    this.setPosY(this.getPosY()-vitesse);
                }
                else
                    this.setPosY(this.getPosY()+vitesse);
                if(this.coefA!=0) this.setPosX((int)((double)((this.getPosY() - this.coefB)/this.coefA)));

            }
            else{
                if (this.yOriginel<this.cibleY){
                    this.setPosY(this.getPosY()+vitesse);
                }
                else
                    this.setPosY(this.getPosY()-vitesse);
                if(this.coefA!=0) this.setPosX((int)((double)((this.getPosY() - this.coefB)/this.coefA)));
            }
            return false;
        }
        else{
            return true;
        }
    }
}
