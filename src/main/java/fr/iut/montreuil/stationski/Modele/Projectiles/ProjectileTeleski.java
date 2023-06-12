package fr.iut.montreuil.stationski.Modele.Projectiles;

import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Tour;
import javafx.beans.property.SimpleIntegerProperty;

public class ProjectileTeleski extends Projectile {

    private int vitesse=1;
    private double coefDirecteur;
    private double ordonneeOrigine;
    private Tour tourCible;
    public ProjectileTeleski(Tour tourCible, int posX, int posY, int ptsAttaque, double coefDirecteur, double ordonneeOrigine){
        super(posX, posY, ptsAttaque);
        this.tourCible = tourCible;
        this.coefDirecteur = coefDirecteur;
        this.ordonneeOrigine = ordonneeOrigine;
    }



    public boolean attaque(){
        if (Math.abs(this.getPosX()-this.tourCible.getPosX())>5 && Math.abs(this.getPosX()-this.tourCible.getPosX())>5){
            if (this.tourCible.getPosX()<this.getPosX()){
                this.setPosX(this.getPosX()-vitesse);
                this.setPosY((int)(this.coefDirecteur*this.getPosX() + ordonneeOrigine));
            }
            else{
                this.setPosX(this.getPosX()+vitesse);
                this.setPosY((int)(this.coefDirecteur*this.getPosX() + ordonneeOrigine));
            }
            return false;
        }
        else{
            return true;
        }

    }

}
