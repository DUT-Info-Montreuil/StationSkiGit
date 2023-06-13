package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;

public class DoNotCross extends Tour {

    public DoNotCross(int posX, int posY, Environnement env) {
        super(100, posX, posY, 2, 1,1, env);
    }

    @Override
    public void agit() {
        for (int acteur = this.env.getVague().getListEnnemis().size()-1; acteur>=0; acteur--){
            if ((obtenirEnvironInf(this.env.getVague().getListEnnemis().get(acteur).getPosX()) == obtenirEnvironInf(getPosX())) || (obtenirEnvironSup(obtenirEnvironInf(this.env.getVague().getListEnnemis().get(acteur).getPosX())) == obtenirEnvironSup(obtenirEnvironInf(getPosX()))) ){
                if ((obtenirEnvironInf(this.env.getVague().getListEnnemis().get(acteur).getPosY()) == obtenirEnvironInf(getPosY())) || (obtenirEnvironSup(obtenirEnvironInf(this.env.getVague().getListEnnemis().get(acteur).getPosY())) == obtenirEnvironSup(obtenirEnvironInf(getPosY()))) ){
                    if(!this.env.getVague().getListEnnemis().get(acteur).getRalenti()){
                        this.env.getVague().getListEnnemis().get(acteur).setRalenti(true);
                        this.env.getVague().getListEnnemis().get(acteur).prendDegats(this.env.getVague().getListEnnemis().get(acteur).getTaille());
                    }
                    this.prendDegats(1);
                }
            }
        }
        if(env.getNbTour() % this.getCadence() ==0) {
            attaquer();
        }
    }

    public int obtenirEnvironInf(int x) {
        int intervalle = 15; // Largeur de l'intervalle

        int borneInf = (x / intervalle) * intervalle;
        int borneSup = borneInf + intervalle;

        return borneInf;
    }

    // necessite méthode préscédente
    public int obtenirEnvironSup(int borneInf) {
        int intervalle = 15; // Largeur de l'intervalle

        int borneSup = borneInf + intervalle;

        return borneSup;
    }
}
