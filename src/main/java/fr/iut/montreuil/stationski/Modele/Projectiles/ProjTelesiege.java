package fr.iut.montreuil.stationski.Modele.Projectiles;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Projectile;

public class ProjTelesiege  extends Projectile {
    public ProjTelesiege(Ennemi cible, int posX, int posY, int ptsAttaque) {
        super(cible, posX, posY, ptsAttaque);

    }


    @Override
    public boolean attaque(){
return true;
    }
}
