package fr.iut.montreuil.stationski.Modele.Projectiles;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Projectile;
import javafx.beans.property.SimpleIntegerProperty;

public class ProjectileCanon extends Projectile {
    private Ennemi cible;
    private SimpleIntegerProperty posX;
    private SimpleIntegerProperty posY;
    private int ptsAttaque;
    private static int vitesse = 3;
    private String id;
    private static int compteur = 0;

    public ProjectileCanon(Ennemi cible, int posX, int posY, int ptsAttaque){
        super(posX, posY, ptsAttaque);
        this.cible = cible;

    }


    public Ennemi getCible() {
        return cible;
    }

    public boolean attaque(){
        if(!this.cible.estVivant()) {
            return true;
        }

        int dx, dy;
        if(this.cible.getPosX()-this.getPosX() >0)dx = 1;
        else dx = -1;
        if(this.cible.getPosY()-this.getPosY() >0)dy = 1;
        else dy = -1;

        this.setPosX(this.getPosX() + (dx * vitesse));
        this.setPosY(this.getPosY() + (dy * vitesse));



        //
        if( (this.getPosX() <= this.cible.getPosX() && this.getPosX() >= (this.cible.getPosX()-8)) && (this.getPosY() <= this.cible.getPosY() && this.getPosY() >= (this.cible.getPosY()-8)) ){
            this.cible.prendDegats(this.getPtsAttaque());

            return true;
        }
        return false;

    }
}
