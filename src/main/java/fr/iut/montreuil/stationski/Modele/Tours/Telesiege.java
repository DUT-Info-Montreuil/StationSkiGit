package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;

import fr.iut.montreuil.stationski.Modele.Projectiles.ProjectileTelesiege;
import fr.iut.montreuil.stationski.Modele.Tour;

public class Telesiege extends Tour {

    public Telesiege(int posX, int posY, Environnement env) {
        super(300, posX, posY, 30, 90,50, env);
    }
    public Telesiege( int pv, int posX, int posY, double ptsAttaque, Environnement env) {
        super(pv, posX, posY, ptsAttaque, 90,50, env);
    }
    @Override
    public boolean isInRange(Ennemi ennemi){
        return (Math.abs(this.getPosX()-ennemi.getPosX())>10 && this.getPosY()-ennemi.getPosY()<-16   && Math.abs(this.getPosY()-ennemi.getPosY())<this.getRange() && Math.abs(this.getPosX()-ennemi.getPosX())<this.getRange() );
    }
    @Override
    public void attaquer(){

        Ennemi ennemiCible = super.getCible();
        if (ennemiCible != null && isInRange(ennemiCible) && ennemiCible.estVivant() && ennemiCible.getPosX() != this.getPosX()) {

                double constanteN;
                double constanteN2;
                double coefA;
                double coefA2;
                double coefC;
                double coefC2;
                int cibleX = ennemiCible.getPosX();
                int cibleY = ennemiCible.getPosY();

                cibleX = cibleX - this.getPosX();
                cibleY = this.getPosY() - cibleY;


                coefC = cibleY + 15;
                coefC2 = cibleY + 25;

                if (cibleX < this.getPosX()) {
                    constanteN = (-(-4 * cibleX) + Math.sqrt(Math.abs((-4 * cibleX) * (-4 * cibleX) - 4 * (-cibleY) * (-coefC * (cibleX * cibleX)))) / (2 * (-cibleY)));
                    constanteN2 = (-(-4 * cibleX) + Math.sqrt(Math.abs((-4 * cibleX) * (-4 * cibleX) - 4 * (-cibleY) * (-coefC2 * (cibleX * cibleX)))) / (2 * (-cibleY)));
                } else {
                    constanteN = (-(-4 * cibleX) - Math.sqrt(Math.abs((-4 * cibleX) * (-4 * cibleX) - 4 * (-cibleY) * (-coefC * (cibleX * cibleX)))) / (2 * (-cibleY)));
                    constanteN2 = (-(-4 * cibleX) - Math.sqrt(Math.abs((-4 * cibleX) * (-4 * cibleX) - 4 * (-cibleY) * (-coefC2 * (cibleX * cibleX)))) / (2 * (-cibleY)));
                }
                coefA = (-coefC) / (constanteN * constanteN);
                coefA2 = (-coefC2) / (constanteN2 * constanteN2);
                this.getEnv().getListeProj().add(new ProjectileTelesiege(this.getCible(), this.getPtsAttaque(), this, coefA, coefC, constanteN));
                this.getEnv().getListeProj().add(new ProjectileTelesiege(this.getCible(), this.getPtsAttaque(), this, coefA2, coefC2, constanteN2));
        }
        else {
            super.setCible(searchEnnemi());
        }

    }


}

