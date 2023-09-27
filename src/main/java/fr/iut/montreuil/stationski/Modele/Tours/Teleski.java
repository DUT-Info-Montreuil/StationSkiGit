package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectiles.ProjectileTeleski;
import fr.iut.montreuil.stationski.Modele.Tour;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Teleski extends Tour {
    public static ArrayList<Teleski> listeTeleskis = new ArrayList<>();
    private double coefDirecteur;
    private double ordonneeOrigine;
    private int nbToursDeBoucle;
    private Tour tourCible;
    public Teleski(int posX, int posY, Environnement env) {
        super(350, posX, posY, 30,20, 1, env);

        this.coefDirecteur=0;
        this.ordonneeOrigine=0;
        this.nbToursDeBoucle = 0;
        créerLigneTeleski();
        Teleski.listeTeleskis.add(this);
    }

    public void créerLigneTeleski(){
        if(Teleski.listeTeleskis.size()>0){
            int teleskiPrecedentX ;
            int teleskiPrecedentY ;
            ObservableList<Tour> listeTours =  this.env.getListeTours();
            int i=0;
            //while (i<listeTours.size() && (!(listeTours.get(i) instanceof Teleski) || listeTours.get(i).getNumeroTeleski()!=this.numeroTeleski-1)){
            //    i++;
            //}

            if (i<listeTours.size()){
                this.tourCible = Teleski.listeTeleskis.get(Teleski.listeTeleskis.size()-1);
                teleskiPrecedentX= this.tourCible.getPosX();
                teleskiPrecedentY= this.tourCible.getPosY();
                if(teleskiPrecedentX==this.getPosX() || teleskiPrecedentY==this.getPosY()){
                    this.coefDirecteur=0;
                }
                else
                    this.coefDirecteur= ((double)(this.getPosY()-teleskiPrecedentY)/(double)(this.getPosX()-teleskiPrecedentX));
                this.ordonneeOrigine = this.getPosY() - this.coefDirecteur * this.getPosX();
            }
        }
    }

    @Override
    public void attaquer(){
        this.nbToursDeBoucle++;
        verifierSiTourTjrsExistante();
        if (this.nbToursDeBoucle%90==0 && this.tourCible!=null){
            this.env.getListeProj().add(new ProjectileTeleski(this.tourCible, this.getPosX(), this.getPosY(), this.getPtsAttaque(), coefDirecteur, ordonneeOrigine));
        }
    }

    public void verifierSiTourTjrsExistante(){
        int i=0;
        while(i<this.env.getListeTours().size() && this.tourCible!=this.env.getListeTours().get(i)){
            i++;
        }
        if(!(i<this.env.getListeTours().size())) this.tourCible=null;
    }
}


