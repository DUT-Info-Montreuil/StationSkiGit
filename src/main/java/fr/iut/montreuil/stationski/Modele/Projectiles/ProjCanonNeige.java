package fr.iut.montreuil.stationski.Modele.Projectiles;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectile;

public class ProjCanonNeige extends Projectile {

    private int portee;
    private int directionY;
    private int directionX;
    private Environnement env;

    private int distanceEffectuee;
    public ProjCanonNeige(Environnement env, Ennemi cible, int posX, int posY, int ptsAttaque, int directionX, int directionY, int portee) {
        super(cible, posX, posY, ptsAttaque);
        this.directionX = directionX;
        this.directionY = directionY;
        this.portee = portee;
        this.distanceEffectuee = 0;
        this.env = env;
    }


    @Override
    public boolean attaque(){

        int deltaX, deltaY;
        deltaX = super.getCible().getPosX() - super.getPosX() ;
        deltaY = super.getCible().getPosY() - super.getPosY() ;

        int a;
        if(deltaX==0)a = deltaY;
        else a = deltaY / deltaX;

        int b = -a * super.getPosX() + super.getPosY();





        super.setPosX(this.getPosX() + this.getVitesse() );
        super.setPosY(a*this.getPosX() + b );


        this.distanceEffectuee++;
        if(this.distanceEffectuee>portee) return true;


        for(Ennemi e : env.getVague().getListEnnemis() ) {

            if ((this.getPosX() <= e.getPosX() && this.getPosX() >= (e.getPosX() - 8)) && (this.getPosY() <= e.getPosY() && this.getPosY() >= (e.getPosY() - 8))) {
               e.prendDegats(this.getPtsAttaque());
                return true;
            }
        }
        return false;
    }
}
