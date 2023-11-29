package fr.iut.montreuil.stationski.Modele;


import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;
import fr.iut.montreuil.stationski.Modele.Fabric.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class Vague {

    private IntegerProperty numeroVague;
    private int nombreEnnemisSpawn;
    private ObservableList<Ennemi> listEnnemis;
    private Environnement env;
    private ArrayList<Ennemi> listEnnemisEnAttente;


    private ArrayList<FabricEnnemi> listeFabricEnnemis;
    public Vague ( Environnement env){
        this.numeroVague = new SimpleIntegerProperty(0);

        this.listEnnemis= FXCollections.observableArrayList();
        this.listEnnemisEnAttente=new ArrayList<>();
        this.env = env;
        this.nombreEnnemisSpawn=5;

        this.listeFabricEnnemis = new ArrayList<FabricEnnemi>();
        this.listeFabricEnnemis.add(new FabricSkieur());
        this.listeFabricEnnemis.add(new FabricLuge());
        this.listeFabricEnnemis.add(new FabricSnowboarder());
        this.listeFabricEnnemis.add(new FabricYeti());
        this.listeFabricEnnemis.add(new FabricBobsleigh());
        this.listeFabricEnnemis.add(new FabricLuge());


        prochaineVague();
    }
    public void faireAgirVague(int nbTour){
        //Faire apparaitre des ennemis
        if(nbTour%50==0 && this.listEnnemisEnAttente.size()>0){
            this.listEnnemis.add(this.listEnnemisEnAttente.get(this.listEnnemisEnAttente.size()-1));
            this.listEnnemisEnAttente.remove(this.listEnnemisEnAttente.size()-1);
        }
        //Génère la prochaine vague
        if (this.listEnnemis.isEmpty() && this.listEnnemisEnAttente.isEmpty() && nbTour%350==0){
            prochaineVague();
        }
    }
    public void faireAgirEnnemis(){
        for (int acteur = this.getListEnnemis().size()-1; acteur>=0; acteur--){
            this.getListEnnemis().get(acteur).agit();


            if (!this.getListEnnemis().get(acteur).estVivant()){
                mortEnnemi(acteur);

            }
        }
        this.env.setNbEnnemis(this.listEnnemis.size());
    }
    public void mortEnnemi(int acteur){
        this.listEnnemis.get(acteur).meurt();
        this.listEnnemis.remove(acteur);
    }
    public ObservableList<Ennemi> getListEnnemis(){
        return this.listEnnemis;
    }



    public void prochaineVague(){
        if(this.numeroVague.getValue()==3){
            this.nombreEnnemisSpawn=10;
        }

        else if(this.numeroVague.getValue()==6 ){
            this.nombreEnnemisSpawn=20;
        }


        int i = 0;
        while (this.listEnnemisEnAttente.size()<this.nombreEnnemisSpawn) {
            Ennemi newEnnemi = this.listeFabricEnnemis.get(i).creerEnnemi(this.env, this);
            if(newEnnemi != null){
                this.listEnnemisEnAttente.add(newEnnemi);
            }
            i++;
            i%=this.listeFabricEnnemis.size();
        }
        this.numeroVague.setValue(this.numeroVague.getValue()+1);

    }
    public Sommet getCible(){
        return this.env.getTerrain().getCible();
    }
    public IntegerProperty numeroVagueProperty () {return this.numeroVague;}
}