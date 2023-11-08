package fr.iut.montreuil.stationski.Modele;



import fr.iut.montreuil.stationski.Modele.Ennemis.*;
import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;
import fr.iut.montreuil.stationski.Modele.Ennemis.Bobsleigh;
import fr.iut.montreuil.stationski.Modele.Ennemis.SkieurBasique;
import fr.iut.montreuil.stationski.Modele.Ennemis.Yeti;
import fr.iut.montreuil.stationski.Modele.Fabric.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class Vague {

    private IntegerProperty numeroVague;
    private int nombreEnnemisSpawn;
    private double pourcentChanceSkieurBasique;
    private double pourcentChanceSnowboardeur;
    private double pourcentChanceLuge;
    private double pourcentChanceBobsleigh;
    private double pourcentChanceYeti;
    private ObservableList<Ennemi> listEnnemis;
    private Environnement env;
    private ArrayList<Ennemi> listEnnemisEnAttente;


    private ArrayList<FabricEnnemi> listeFabric;
    public Vague ( Environnement env){
        this.numeroVague = new SimpleIntegerProperty(0);
        this.pourcentChanceSkieurBasique =0;
        this.pourcentChanceSnowboardeur=0;
        this.pourcentChanceLuge=0;
        this.pourcentChanceYeti=0;
        this.listEnnemis= FXCollections.observableArrayList();
        this.listEnnemisEnAttente=new ArrayList<>();
        this.env = env;
        this.nombreEnnemisSpawn=5;



        this.listeFabric = new ArrayList<FabricEnnemi>();
        this.listeFabric.add(new FabricSkieur());
        this.listeFabric.add(new FabricLuge());
        this.listeFabric.add(new FabricSnowboarder());
        this.listeFabric.add(new FabricYeti());
        this.listeFabric.add(new FabricBobsleigh());
        this.listeFabric.add(new FabricLuge());




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
            Ennemi newEnnemi = this.listeFabric.get(i).creerEnnemi(this.env, this);
            if(newEnnemi != null){
                this.listEnnemisEnAttente.add(newEnnemi);
            }
            i++;
            i%=this.listeFabric.size();
        }
        this.numeroVague.setValue(this.numeroVague.getValue()+1);

    }
/**

    public void prochaineVague(){

        if(this.numeroVague.getValue()==3){
            this.nombreEnnemisSpawn=10;
        }

        else if(this.numeroVague.getValue()==6){
            this.nombreEnnemisSpawn=20;
        }


        // Initialisation de tous les paramètres de génération des Ennemis :
        if (numeroVague.getValue()<=3)
            this.pourcentChanceSkieurBasique = -(double)(0.6/3)*numeroVague.getValue() + 0.8;
        else
            this.pourcentChanceSkieurBasique = 0.75 * this.pourcentChanceSkieurBasique;

        if (numeroVague.getValue()<=3)
            this.pourcentChanceSnowboardeur = -(double)(1/6)*numeroVague.getValue() + 0.25 ;
        else
            this.pourcentChanceSnowboardeur = 0.8*this.pourcentChanceSnowboardeur;

        if (numeroVague.getValue()<=2)
            this.pourcentChanceLuge = 0;
        else if (numeroVague.getValue()<5)
            this.pourcentChanceLuge =  (double)(0.65/3)*(numeroVague.getValue()-2);
        else
            this.pourcentChanceLuge = 0.8* this.pourcentChanceLuge;

        if (numeroVague.getValue()<9)
            this.pourcentChanceYeti = 0;
        else
            this.pourcentChanceYeti = 0.5+(this.pourcentChanceYeti*(double)(1/this.numeroVague.getValue()));

        if (numeroVague.getValue()<4)
            this.pourcentChanceBobsleigh = 0;
        else if(this.numeroVague.getValue()<8)
            this.pourcentChanceBobsleigh = 0.3 + this.pourcentChanceBobsleigh*1.2;
        else
            this.pourcentChanceBobsleigh = 0.6;


        // Génération des ennemis :
        while (this.listEnnemisEnAttente.size()<this.nombreEnnemisSpawn){
            if (10-this.listEnnemis.size()>0 && (Math.random() * 1)<this.pourcentChanceSkieurBasique) this.listEnnemisEnAttente.add(new SkieurBasique( this.env.getTerrain().getSource().getX() * 16, this.env.getTerrain().getSource().getY() * 16, env,  new Dijkstra(this.env.getTerrain()), this)); // new Skieur
            if (10-this.listEnnemis.size()>0 && (Math.random() * 1)<this.pourcentChanceSnowboardeur) this.listEnnemisEnAttente.add(new Snowboarder( this.env.getTerrain().getSource().getX()*16, this.env.getTerrain().getSource().getY()*16, env,  new Dijkstra(this.env.getTerrain()),this));
            if (10-this.listEnnemis.size()>0 && (Math.random() * 1)<this.pourcentChanceLuge) this.listEnnemisEnAttente.add(new Luge( this.env.getTerrain().getSource().getX() * 16, this.env.getTerrain().getSource().getY() * 16,  env,  new Dijkstra(this.env.getTerrain()), this)); // new Luge
            if (10-this.listEnnemis.size()>0 && (Math.random() * 1)<this.pourcentChanceBobsleigh) this.listEnnemisEnAttente.add(new Bobsleigh( this.env.getTerrain().getSource().getX() * 16, this.env.getTerrain().getSource().getY() * 16,  env,  new Dijkstra(this.env.getTerrain()),this.env.getVague())); // new Luge
            if (10-this.listEnnemis.size()>0 && (Math.random() * 1)<this.pourcentChanceYeti) this.listEnnemisEnAttente.add(new Yeti( this.env.getTerrain().getSource().getX() * 16, this.env.getTerrain().getSource().getY() * 16,  env,  this));

        }
        this.numeroVague.setValue(this.numeroVague.getValue()+1);
    }

**/
    public Sommet getCible(){
        return this.env.getTerrain().getCible();
    }

    public ArrayList<Ennemi> getListEnnemisEnAttente(){return this.listEnnemisEnAttente;}
    public IntegerProperty numeroVagueProperty () {return this.numeroVague;}
}