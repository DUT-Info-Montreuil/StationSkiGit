package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectile;
import fr.iut.montreuil.stationski.Modele.Projectiles.ProjCanonNeige;
import fr.iut.montreuil.stationski.Modele.Projectiles.ProjTelesiege;
import fr.iut.montreuil.stationski.Modele.Tour;

public class Telesiege extends Tour {
    private Projectile projectile;

    public Telesiege(int posX, int posY, Environnement env) {
        super(20, posX, posY, 50, 50, env);

        projectile = null;
    }



    @Override
    public void attaquer(){

        if(projectile == null){
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

    public void setProjectileNull(){
        this.projectile = null;
    }
}
