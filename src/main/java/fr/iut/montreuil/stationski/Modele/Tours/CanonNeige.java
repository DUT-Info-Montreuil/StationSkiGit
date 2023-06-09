package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Projectiles.ProjCanonNeige;
import fr.iut.montreuil.stationski.Modele.Tour;

public class CanonNeige extends Tour {
    private int portee;

    public CanonNeige(int posX, int posY, Environnement env, int portee, int range) {
        super(20, posX, posY, 20, 50, env);
        this.portee = portee;
        super.setRange(range);
    }


    @Override
    public void attaquer(){
           int  tir = (int)( Math.random()*20)+1;
            if((tir==1)) {
                if (super.getCible() != null) {

                    if (isInRange(super.getCible())) {
                        if (super.getCible().estVivant()) {
                            int directionX, directionY;
                            for (int i = 0; i < 5; i++) {

                                if ((int) (Math.random() * 2) +1 == 1) {
                                    directionX = (int) (super.getCible().getPosX()/16 + (Math.random() * 10));
                                } else directionX =  (int) (super.getCible().getPosX()/16 -(Math.random() * 10));

                                if ((int) (Math.random() * 2) + 1 == 1) {
                                    directionY = (int) (super.getCible().getPosY()/16 + Math.random() * 10);
                                } else directionY =  (int) (super.getCible().getPosY()/16 - Math.random() * 10);



                                this.env.getListeProj().add(new ProjCanonNeige(env, super.getCible(), this.getPosX(), this.getPosY(), super.getPtsAttaque(), directionX, directionY, portee));
                            }


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
