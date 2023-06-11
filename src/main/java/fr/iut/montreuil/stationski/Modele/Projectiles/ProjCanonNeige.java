package fr.iut.montreuil.stationski.Modele.Projectiles;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectile;

public class ProjCanonNeige extends Projectile {

    private int portee;
    private int decalageY;
    private int decalageX;

    private int directionY;
    private int directionX;
    private Environnement env;
    private int X;

    private int cibleX;
    private int cibleY;
    private int a;

    private int b;

    private int distanceEffectuee;

    public ProjCanonNeige(Environnement env, Ennemi cible, int cibleX, int cibleY, int posX, int posY, int ptsAttaque, int decalageX, int decalageY, int portee) {
        super(cible, posX, posY, ptsAttaque);
        this.decalageX = decalageX;
        this.decalageY = decalageY;
        this.portee = portee;
        this.distanceEffectuee = 0;
        this.env = env;
        this.cibleX =  cibleX;
        this.cibleY = cibleY;
        X = 0;

        if(super.getPosX() - cibleX <0)directionX  = 1;
        else directionX = -1;
        if(super.getPosY() - cibleY <0)directionY  = 1;
        else directionY = -1;

        a =  (super.getPosY()- cibleY + decalageY) / (super.getPosX() - cibleX + decalageX  );

        b = -a * this.getPosX() + super.getPosY();

    }


    @Override
    public boolean attaque(){


        X+=this.getVitesse();

        int currentY = a*X ;


        super.setPosX(this.getPosX() + (this.getVitesse()+ decalageX)*directionX);
        super.setPosY(this.getPosY() + (currentY+ decalageY)*directionY);


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
