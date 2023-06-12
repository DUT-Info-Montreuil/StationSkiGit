package fr.iut.montreuil.stationski.Modele.Tours;

import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Projectiles.ProjectileTeleski;

import fr.iut.montreuil.stationski.Modele.Tour;
import javafx.collections.ObservableList;

public class Teleski extends Tour {
    public static int nombreTeleski=0;
    private int numeroTeleski;
    private double coefDirecteur;
    private double ordonneeOrigine;
    private int nbToursDeBoucle;
    private Tour tourCible;
    public Teleski(int posX, int posY, Environnement env) {
        super(10, posX, posY, 40,20,1, env);
        System.out.println("NOUVEAU TELESKI");

        this.coefDirecteur=0;
        this.ordonneeOrigine=0;
        this.numeroTeleski=this.nombreTeleski;
        this.nombreTeleski++;
        System.out.println("nombreTeleski : "+ this.nombreTeleski);
        System.out.println("numeroTeleski : " + this.numeroTeleski);
        System.out.println("posX : " + this.getPosX());
        System.out.println("posY : " + this.getPosY());
        this.nbToursDeBoucle = 0;
        créerLigneTeleski();
    }

    public void créerLigneTeleski(){
        if(this.numeroTeleski >0){
            int teleskiPrecedentX ;
            int teleskiPrecedentY ;
            ObservableList<Tour> listeTours =  this.env.getListeTours();
            int i=0;
            while (i<listeTours.size() && (!(listeTours.get(i) instanceof Teleski) || listeTours.get(i).getNumeroTeleski()!=this.numeroTeleski-1)){
                i++;
            }

            if (i<listeTours.size()){
                this.tourCible = listeTours.get(i);
                teleskiPrecedentX= listeTours.get(i).getPosX();
                teleskiPrecedentY= listeTours.get(i).getPosY();
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
            this.env.getListeProj().add(new ProjectileTeleski(this.tourCible, this.getPosX(), this.getPosY(), 400, coefDirecteur, ordonneeOrigine));
        }
    }
    @Override
    public int getNumeroTeleski(){return this.numeroTeleski;}
    public void verifierSiTourTjrsExistante(){
        int i=0;
        while(i<this.env.getListeTours().size() && this.tourCible!=this.env.getListeTours().get(i)){
            i++;
        }
        if(!(i<this.env.getListeTours().size())) this.tourCible=null;
    }
}


