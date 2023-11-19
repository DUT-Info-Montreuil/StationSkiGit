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
    private Tour tourCible;
    public Teleski(int posX, int posY, Environnement env) {
        super(350, posX, posY, 30,20, 90, env);
        this.coefDirecteur=0;
        this.ordonneeOrigine=0;
        créerLigneTeleski();
        Teleski.listeTeleskis.add(this);
    }

    public Teleski(int pv, int posX, int posY, int ptsAttaque, Environnement env) {
        super(pv, posX, posY, ptsAttaque,20, 90, env);
        this.coefDirecteur=0;
        this.ordonneeOrigine=0;

    }
    public void créerLigneTeleski(){
        if(Teleski.listeTeleskis.size()>0){
            int teleskiPrecedentX ;
            int teleskiPrecedentY ;
            ObservableList<Tour> listeTours =  this.getEnv().getListeTours();
            int i=0;


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

        verifierSiTourTjrsExistante();
        if (this.tourCible!=null){
            this.getEnv().getListeProj().add(new ProjectileTeleski(this.tourCible, this.getPosX(), this.getPosY(), this.getPtsAttaque(), coefDirecteur, ordonneeOrigine));
        }
    }

    public void verifierSiTourTjrsExistante(){
        int i=0;
        while(i<this.getEnv().getListeTours().size() && this.tourCible!=this.getEnv().getListeTours().get(i)){
            i++;
        }
        if(!(i<this.getEnv().getListeTours().size())) this.tourCible=null;
    }

   
}


