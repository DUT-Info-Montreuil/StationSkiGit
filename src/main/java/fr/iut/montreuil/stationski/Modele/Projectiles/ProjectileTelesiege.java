package fr.iut.montreuil.stationski.Modele.Projectiles;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Tours.Telesiege;

public class ProjectileTelesiege  extends Projectile {

    private int cibleX;
    private int cibleY;

    private double coefA;
    private double coefC;
    private double constanteN;
    private Telesiege t;
    private double x;
    private int nbToursDeBoucle;

    public ProjectileTelesiege(Ennemi cible, int ptsAttaque, Telesiege t, double coefA, double coefC, double constanteN) {
        super(cible, t.getPosX(), t.getPosY(), ptsAttaque);

        this.t=t;
        if(cible.getDijkstra().getParcours().size()>2){
            this.cibleX=cible.getDijkstra().getParcours().get(cible.getDijkstra().getParcours().size()-1).getX()*16;
            this.cibleY=cible.getDijkstra().getParcours().get(cible.getDijkstra().getParcours().size()-1).getY()*16;
        }
        else{
            this.cibleX=cible.getPosX();
            this.cibleY=cible.getPosY();
        }
        this.nbToursDeBoucle=0;
        this.x=0;
        this.coefA=coefA;
        this.coefC=coefC;
        this.constanteN=constanteN;


    }


    @Override
    public boolean attaque(){
        this.nbToursDeBoucle++;

        if(this.nbToursDeBoucle%2==0 ){

            if (Math.sqrt(Math.abs(this.getPosX()-cibleX)*Math.abs(this.getPosX()-cibleX)+Math.abs(this.getPosY()-cibleY)*Math.abs(this.getPosY()-cibleY))<this.t.getRange()+16 && this.nbToursDeBoucle<100)      {
                if(this.getPosX()<cibleX) {
                    this.x+=0.05;
                    this.setPosX(this.getPosX() + 1);
                }
                else {
                    this.x-=0.05;
                    this.setPosX(this.getPosX() - 1);
                }
                this.setPosY(((int)(this.coefA*((this.x+this.constanteN)*(this.x+this.constanteN))+this.coefC))+this.getPosY());

            }
            else {
                this.getCible().prendDegats(this.getPtsAttaque());
                return true;
            }
        }
        return false;

    }
}