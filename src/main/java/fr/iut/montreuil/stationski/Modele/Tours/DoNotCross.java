package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Tour;

public class DoNotCross extends Tour {

    public DoNotCross(int posX, int posY, Environnement env) {
        super(100, posX, posY, 2, 10,1, env);
    }

    @Override
    public void agit() {
        ralentissement();
    }

    public void ralentissement(){
        for (int acteur = this.env.getVague().getListEnnemis().size()-1; acteur>=0; acteur--){
            if (isInRange(this.env.getVague().getListEnnemis().get(acteur))){
                System.out.println("ralenti");
                this.env.getVague().getListEnnemis().get(acteur).setRalenti(true);
                this.env.getVague().getListEnnemis().get(acteur).prendDegats(this.env.getVague().getListEnnemis().get(acteur).getImportance());
                this.prendDegats(1);
            }
        }
    }
}
