package fr.iut.montreuil.stationski.Modele.Projectiles;

import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Tour;
import javafx.beans.property.SimpleIntegerProperty;

public class ProjectileTeleski extends Projectile {

    private int vitesse=1;
    private double coefDirecteur;
    private double ordonneeOrigine;
    private Tour tourCible;
    private int xOriginel;

    public ProjectileTeleski(Tour tourCible, int posX, int posY, int ptsAttaque, double coefDirecteur, double ordonneeOrigine){
        super(posX, posY, ptsAttaque);
        this.xOriginel = this.getPosX();
        this.tourCible = tourCible;
        this.coefDirecteur = coefDirecteur;
        this.ordonneeOrigine = ordonneeOrigine;
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
            else{
                if (this.tourCible.getPosY()<this.getPosY()){
                    this.setPosY(this.getPosY()-vitesse);
                }
                else
                    this.setPosY(this.getPosY()+vitesse);
                if(this.coefDirecteur!=0) this.setPosX((int)((double)((this.getPosY() - ordonneeOrigine)/this.coefDirecteur)));

            }
            return false;
        }
        else{
            return true;
        }

    }

}
