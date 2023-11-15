package fr.iut.montreuil.stationski.Modele.Tours.Ameliorations;

import fr.iut.montreuil.stationski.Modele.Tour;
import fr.iut.montreuil.stationski.Modele.Tours.Teleski;

public class TeleskiNiveau2 extends Teleski {
    public TeleskiNiveau2(Tour teleski){
        super (400, teleski.getPosX(), teleski.getPosY(), 40, teleski.getEnv());
        int i=0;
        while(i<Teleski.listeTeleskis.size() && teleski.getId() != Teleski.listeTeleskis.get(i).getId()){
            i++;
        }
        if (i<Teleski.listeTeleskis.size()){
            this.setTourCible(Teleski.listeTeleskis.get(i).getTourCible());
            Teleski.listeTeleskis.remove(i);
        }
        Teleski.listeTeleskis.add(i, this);
    }
}
