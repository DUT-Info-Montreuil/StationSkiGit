package fr.iut.montreuil.stationski.Modele.Projectiles;

import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Tour;

public class ProjectileTeleski extends Projectile {

    private int vitesse;
    private double coefDirecteur;
    private double ordonneeOrigine;
    private Tour tourCible;
    private int xOriginel;
    private int yOriginel;
    public ProjectileTeleski(Tour tourCible, int posX, int posY, double ptsAttaque, double coefDirecteur, double ordonneeOrigine){
        super(tourCible, posX, posY, ptsAttaque);
        this.xOriginel = this.getPosX();
        this.yOriginel = this.getPosY();
        this.tourCible = tourCible;
        this.coefDirecteur = coefDirecteur;
        this.ordonneeOrigine = ordonneeOrigine;
        this.vitesse=1;
    }

    public boolean attaque(){
        if (Math.abs(this.getPosX()-this.tourCible.getPosX())>10 || Math.abs(this.getPosY()-this.tourCible.getPosY())>10){
            if(Math.abs(this.xOriginel-this.tourCible.getPosX())>64){
                if (this.tourCible.getPosX()<this.getPosX())
                    this.setPosX(this.getPosX()-vitesse);
                else
                    this.setPosX(this.getPosX()+vitesse);
                if(this.coefDirecteur!=0) this.setPosY((int)(this.coefDirecteur*this.getPosX() + ordonneeOrigine));
            }
            else if (Math.abs(this.yOriginel-this.tourCible.getPosY())>64){
                if (this.tourCible.getPosY()<this.getPosY()){
                    this.setPosY(this.getPosY()-vitesse);
                }
                else
                    this.setPosY(this.getPosY()+vitesse);
                if(this.coefDirecteur!=0) this.setPosX((int)((double)((this.getPosY() - ordonneeOrigine)/this.coefDirecteur)));

            }
            else{
                if (this.tourCible.getPosY()<this.getPosY()){
                    this.setPosY(this.getPosY()-(vitesse/2));
                }
                else
                    this.setPosY(this.getPosY()+(vitesse/2));
                if(this.coefDirecteur!=0) this.setPosX((int)((double)((this.getPosY() - ordonneeOrigine)/this.coefDirecteur)));
            }
            return false;
        }
        else{
            return true;
        }

    }

}
