package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Ennemi;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectiles.ProjectileCanonEau;
import fr.iut.montreuil.stationski.Modele.Tour;

public class CanonEau extends Tour {
    private int salve;
    public CanonEau(int posX, int posY, Environnement env) {
        super(250, posX, posY, 3, 80,2, env);
        super.setIdTour(2);
        this.salve=0;
    }
    public CanonEau( int pv, int posX, int posY, int ptsAttaque, Environnement env) {
        super(pv, posX, posY, ptsAttaque, 80,2, env);
        this.salve=0;
    }
    @Override
    public void attaquer() {
        Ennemi ennemiCible = searchEnnemi();
        if (ennemiCible != null) {
            if (isInRange(ennemiCible)) {
                this.salve++;
                if (ennemiCible.estVivant() && this.salve%50<8) {
                    double coefA=0;
                    double coefC=0;
                    if(ennemiCible.getPosX()==this.getPosX() || ennemiCible.getPosY()==this.getPosY())
                        coefA=0;
                    else
                        coefA= ((double)(this.getPosY()-ennemiCible.getPosY())/(double)(this.getPosX()-ennemiCible.getPosX()));
                    coefC= this.getPosY() - coefA * this.getPosX();

                    this.getEnv().getListeProj().add(new ProjectileCanonEau(ennemiCible, this.getPtsAttaque(),this,coefA, coefC));

                } else ennemiCible = null;
            } else {
                ennemiCible = searchEnnemi();
                attaquer();

            }
        } else {
            ennemiCible = searchEnnemi();
        }
    }

}
