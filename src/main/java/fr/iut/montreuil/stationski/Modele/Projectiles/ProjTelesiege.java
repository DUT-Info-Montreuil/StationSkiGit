package fr.iut.montreuil.stationski.Modele.Projectiles;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Tours.Telesiege;

public class ProjTelesiege  extends Projectile {

    private int cibleX;
    private int cibleY;

    private int currentX;
    private int currentY;
    private int a;
    private int c;
    private int b;
    private int direction;
    private int origineX;
    private int origineY;
    private Telesiege t;
    public ProjTelesiege(Ennemi cible, int cibleX, int cibleY, int posX, int posY, int ptsAttaque, Telesiege t) {
        super(cible, posX, posY, ptsAttaque);
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

        System.out.println("cibleX : " + cibleX);
        System.out.println("cibleY : " + cibleY);


    }


    @Override
    public boolean attaque(){
        this.nbToursDeBoucle++;
        if(this.nbToursDeBoucle%100==0){
            double n;
            cibleX=cibleX-this.getPosX();
            cibleY=this.getPosY()-cibleY;
            if(cibleX<this.getPosX()){
                n = (-(-4*cibleX)+Math.sqrt((-4*cibleX)*(-4*cibleX)-4*(-cibleY)*(-2*(cibleX*cibleX))))/(2*(-cibleY));
            }
            else{
                n = (-(-4*cibleX)-Math.sqrt((-4*cibleX)*(-4*cibleX)-4*(-cibleY)*(-2*(cibleX*cibleX))))/(2*(-cibleY));
            }
            this.a = (-2)/(n*n);


        }









        this.currentY = ( a * currentX * currentX + b * currentX + c);
        System.out.println(currentY);
        System.out.println(currentY);
        this.currentX +=this.getVitesse();

        this.setPosX( this.getPosX()+ this.getVitesse()*direction);
        this.setPosY(  this.getPosY() +  this.currentY);


        System.out.println("X : " + this.getPosX());
        System.out.println("Y : " + this.getPosY());

        if (Math.abs(this.getPosX() - this.cibleX)<25 && (Math.abs(this.getPosY() - this.cibleY))<25){
            this.t.setProjectileNull();
            return true;
        }



        return false;




    }
}
