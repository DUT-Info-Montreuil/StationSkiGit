package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectile;

import fr.iut.montreuil.stationski.Modele.Projectiles.ProjectileTelesiege;
import fr.iut.montreuil.stationski.Modele.Tour;

public class Telesiege extends Tour {
    private Projectile projectile;
    private int nbToursdeBoucle;

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
            if(ennemiCible!=null && isInRange(ennemiCible) && ennemiCible.estVivant()){
                double n;
                double coefA;
                double coefC;
                int cibleX = ennemiCible.getPosX();
                int cibleY = ennemiCible.getPosY();

                cibleX = cibleX - this.getPosX();
                cibleY = this.getPosY() - cibleY;

                coefC = cibleY + 2;

                if (cibleX < this.getPosX()) {
                    n = (-(-4 * cibleX) + Math.sqrt((-4 * cibleX) * (-4 * cibleX) - 4 * (-cibleY) * (-coefC * (cibleX * cibleX)))) / (2 * (-cibleY));
                } else {
                    n = (-(-4 * cibleX) - Math.sqrt((-4 * cibleX) * (-4 * cibleX) - 4 * (-cibleY) * (-coefC * (cibleX * cibleX)))) / (2 * (-cibleY));
                }
                coefA = (-coefC) / (n * n);
                this.projectile = new ProjectileTelesiege(this.getCible(), 50, this, coefA, coefC);
                this.env.getListeProj().add(this.projectile);
            }
            else{
                super.setCible(searchEnnemi());
            }
        }
    }

    public void setProjectileNull(){
        this.projectile = null;
    }
}

