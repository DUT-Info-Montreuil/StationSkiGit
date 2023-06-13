package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Projectiles.ProjCanonNeige;
import fr.iut.montreuil.stationski.Modele.Tour;

public class CanonNeige extends Tour {
    private int portee;
    private int nbToursdeBoucle;

    public CanonNeige(int posX, int posY, Environnement env, int portee, int range) {
        super(20, posX, posY, 20, 50, env);
        this.portee = portee;
        super.setRange(range);
        this.nbToursdeBoucle = 0 ;

    }


    @Override
    public void attaquer(){
        this.nbToursdeBoucle++;
           if(nbToursdeBoucle%50 ==0){
                if (super.getCible() != null) {

                    if (isInRange(super.getCible())) {
                        if (super.getCible().estVivant()) {
                            int decalageX, decalageY;
                            for (int i = 0; i < 5; i++) {

                                if ((int) (Math.random() * 2) +1 == 1) {
                                    decalageX = (int) (super.getCible().getPosX()/16 + (Math.random() * 5+1));
                                } else decalageX =  (int) (super.getCible().getPosX()/16 -(Math.random() * 5+1));

                                if ((int) (Math.random() * 2) + 1 == 1) {
                                    decalageY = (int) (super.getCible().getPosY()/16 + Math.random() * 5+1);
                                } else decalageY =  (int) (super.getCible().getPosY()/16 - Math.random() * 5+1);



                                this.env.getListeProj().add(new ProjCanonNeige(env, super.getCible(), super.getCible().getPosX(), super.getCible().getPosY(), this.getPosX(), this.getPosY(), super.getPtsAttaque(), decalageX, decalageY, portee));
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
