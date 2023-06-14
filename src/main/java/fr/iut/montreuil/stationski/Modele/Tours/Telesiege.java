package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectile;

import fr.iut.montreuil.stationski.Modele.Projectiles.ProjectileTelesiege;
import fr.iut.montreuil.stationski.Modele.Tour;

public class Telesiege extends Tour {

    private int nbToursdeBoucle;

    public Telesiege(int posX, int posY, Environnement env) {
        super(2000, posX, posY, 50, 90,3, env);
        this.nbToursdeBoucle = 0;
    }


    @Override
    public void attaquer(){
        this.nbToursdeBoucle++;
        if(this.nbToursdeBoucle%30==0){
            Ennemi ennemiCible = super.getCible();
            if(ennemiCible!=null && isInRange(ennemiCible) && ennemiCible.estVivant() && ennemiCible.getPosX()!=this.getPosX()){
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
                coefC2 = cibleY+25;

                if (cibleX < this.getPosX()) {
                    constanteN = (-(-4 * cibleX) + Math.sqrt(Math.abs((-4 * cibleX) * (-4 * cibleX) - 4 * (-cibleY) * (-coefC * (cibleX * cibleX)))) / (2 * (-cibleY)));
                    constanteN2 = (-(-4 * cibleX) + Math.sqrt(Math.abs((-4 * cibleX) * (-4 * cibleX) - 4 * (-cibleY) * (-coefC2 * (cibleX * cibleX)))) / (2 * (-cibleY)));
                }
                else {
                    constanteN = (-(-4 * cibleX) - Math.sqrt(Math.abs((-4 * cibleX) * (-4 * cibleX) - 4 * (-cibleY) * (-coefC * (cibleX * cibleX)))) / (2 * (-cibleY)));
                    constanteN2 = (-(-4 * cibleX) - Math.sqrt(Math.abs((-4 * cibleX) * (-4 * cibleX) - 4 * (-cibleY) * (-coefC2 * (cibleX * cibleX)))) / (2 * (-cibleY)));
                }
                coefA = (-coefC) / (constanteN * constanteN);
                coefA2 = (-coefC2) / (constanteN2 * constanteN2);
                this.env.getListeProj().add(new ProjectileTelesiege(this.getCible(), 50, this, coefA, coefC, constanteN));
                this.env.getListeProj().add(new ProjectileTelesiege(this.getCible(), 50, this, coefA2, coefC2, constanteN2));
            }
            else{
                super.setCible(searchEnnemi());
            }
        }
    }


}

