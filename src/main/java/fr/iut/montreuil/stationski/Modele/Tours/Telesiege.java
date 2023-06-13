package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Projectiles.ProjCanonNeige;
import fr.iut.montreuil.stationski.Modele.Projectiles.ProjTelesiege;
import fr.iut.montreuil.stationski.Modele.Tour;

public class Telesiege extends Tour {
    private Projectile projectile;
    private int nbToursdeBoucle;
    private double coefA;
    private double coefC;
    public Telesiege(int posX, int posY, Environnement env) {
        super(20, posX, posY, 50, 50, env);
        this.nbToursdeBoucle = 0;
        this.projectile = null;
    }


    @Override
    public void attaquer(){
        this.nbToursdeBoucle++;
        if(this.nbToursdeBoucle%50==0){
            Ennemi ennemiCible = super.getCible();
            if(ennemiCible!=null){
                if(isInRange(ennemiCible)){
                    if(ennemiCible.estVivant()){
                        double n;
                        int cibleX = ennemiCible.getPosX();
                        int cibleY = ennemiCible.getPosY();
                        cibleX=cibleX-this.getPosX();
                        cibleY=this.getPosY()-cibleY;
                        if(cibleX<this.getPosX()){
                            n = (-(-4*cibleX)+Math.sqrt((-4*cibleX)*(-4*cibleX)-4*(-cibleY)*(-2*(cibleX*cibleX))))/(2*(-cibleY));
                        }
                        else{
                            n = (-(-4*cibleX)-Math.sqrt((-4*cibleX)*(-4*cibleX)-4*(-cibleY)*(-2*(cibleX*cibleX))))/(2*(-cibleY));
                        }
                        this.coefA = (-2)/(n*n);
                        this.projectile = new ProjTelesiege(this.getCible(), this.getCible().getPosX(), this.getCible().getPosY(), this.getPosX(), this.getPosY(), 50, this);
                        this.env.getListeProj().add(this.projectile);
                    }
                }
            }
        }
    }
    @Override
    public void attaquer() {
        this.nbToursdeBoucle++;
        if (this.nbToursdeBoucle % 50 == 0) {

            if (projectile == null) {
                if (super.getCible() != null) {

                    if (isInRange(super.getCible())) {
                        if (super.getCible().estVivant()) {


                            this.projectile = new ProjTelesiege(this.getCible(), this.getCible().getPosX(), this.getCible().getPosY(), this.getPosX(), this.getPosY(), 50, this);
                            this.env.getListeProj().add(this.projectile);

                        } else super.setCible(null);
                    } else {
                        super.setCible(searchEnnemi());
                        attaquer();

                    }

                } else {

                    super.setCible(searchEnnemi());
                }
            }
        }
    }
    public void setProjectileNull(){
        this.projectile = null;
    }
}
