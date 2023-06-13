package fr.iut.montreuil.stationski.Modele.Projectiles;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Tours.Telesiege;

public class ProjectileTelesiege  extends Projectile {

    private int cibleX;
    private int cibleY;

    private double coefA;
    private double coefC;
    private Telesiege t;

    public ProjectileTelesiege(Ennemi cible, int ptsAttaque, Telesiege t, double coefA, double coefC) {
        super(cible, t.getPosX(), t.getPosY(), ptsAttaque);
        this.cibleX=cible.getPosX();
        this.cibleY=cible.getPosY();
        this.coefA=coefA;
        this.coefC=coefC;

        /**
         currentX = 0;
         currentY = this.getPosY();
         this.t = t;

         origineX = posX;
         origineY = posY;


         this.cibleX = cibleX;
         this.cibleY = cibleY;
         if(this.getPosX()-this.cibleX<0){direction = 1;}
         else direction = -1;

         int y1, y2;
         y1 = super.getPosY();
         y2 =Math.abs(this.cibleY - y1);



         int x1, x2;
         x1 = super.getPosX();
         x2 = Math.abs(this.cibleX - super.getPosX());



         a =  2;

         b =(y2 - y1 -  ((x2 * x2 -x1*x1) * a)) / (x2 -x1)   ;
         System.out.println(b);
         c = 0;
         */
        System.out.println("cibleX : " + cibleX);
        System.out.println("cibleY : " + cibleY);


    }


    @Override
    public boolean attaque(){
        if(Math.abs(this.getPosX()-cibleX)>5 && Math.abs(this.getPosY()-cibleY)>5){
            this.setPosX(this.getPosX()+1);
            this.setPosY((int)(this.coefA*(this.getPosX()*this.getPosX())+this.coefC));

        }
        else{
            this.getCible().prendDegats(this.getPtsAttaque());
            this.t.setProjectileNull();
            return true;
        }
        return false;

        /**
         this.currentY = ( a * currentX * currentX + b * currentX + c);
         System.out.println(currentY);
         System.out.println(currentY);
         this.currentX +=this.getVitesse();

         this.setPosX( this.getPosX()+ this.getVitesse()*direction);
         this.setPosY(  this.getPosY() +  this.currentY);


         System.out.println("X : " + this.getPosX());
         System.out.println("Y : " + this.getPosY());
         */
    }
}