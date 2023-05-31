package fr.iut.montreuil.stationski.Modele;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vague {

    private int numeroVague;
    private double pourcentChanceSkieurBasique;
    private double pourcentChanceSnowboardeur;
    private double pourcentChanceLuge;
    private double pourcentChanceBobsleigh;
    private double pourcentChanceYeti;
    private ObservableList<Ennemi> listEnnemis;
    private Environnement env;

    public Vague (int numeroVague, double pourcentChanceSkieur, double pourcentChanceSnowboardeur, double pourcentChangeLuge, double pourcentChanceYeti, Environnement env){
        this.numeroVague=numeroVague; // this.numeroVague = 0;
        this.pourcentChanceSkieurBasique =pourcentChanceSkieur;
        this.pourcentChanceSnowboardeur=pourcentChanceSnowboardeur;
        this.pourcentChanceLuge=pourcentChangeLuge;
        this.pourcentChanceYeti=pourcentChanceYeti;
        this.listEnnemis= FXCollections.observableArrayList();
        this.env = env;

    }

    public ObservableList<Ennemi> getListEnnemis(){
        return this.listEnnemis;
    }



    public void prochaineVague(){
        // Initialisation de tous les paramètres de génération des Ennemis :
        if (numeroVague<=3)
            this.pourcentChanceSkieurBasique = -(double)(0.6/3)*numeroVague + 0.8;
        else
            this.pourcentChanceSkieurBasique = -0.25*numeroVague + 0.2;

        if (numeroVague<=3)
            this.pourcentChanceSnowboardeur = -(double)(1/6)*numeroVague + 0.25 ;
        else
            this.pourcentChanceSnowboardeur = -0.2*numeroVague + 0.75;

        if (numeroVague<=2)
            this.pourcentChanceLuge = 0;
        else if (numeroVague<5)
            this.pourcentChanceLuge = -(double)(0.65/3)*numeroVague;
        else
            this.pourcentChanceLuge = -0.1*numeroVague + 0.65;

        // Génération des ennemis jusqu'à avoir 10 ennemis :
        while (this.listEnnemis.size()<10){

            if ((Math.random() * 1)<this.pourcentChanceSkieurBasique) {
                this.listEnnemis.add(new SkieurBasique(100, this.env.getTerrain().getSource().getX() * 16, this.env.getTerrain().getSource().getY() * 16, 1, env, 5, new Dijkstra(this.env.getTerrain()))); // new Skieur
            }
            if ((Math.random() * 1)<this.pourcentChanceSnowboardeur){
                this.listEnnemis.add(new Ennemi(100, this.env.getTerrain().getSource().getX()*16, this.env.getTerrain().getSource().getY()*16, 2, env, 10, new Dijkstra(this.env.getTerrain()))); // new Snowboardeur

            }
            if ((Math.random() * 1)<this.pourcentChanceLuge) {
                this.listEnnemis.add(new Ennemi(100, this.env.getTerrain().getSource().getX() * 16, this.env.getTerrain().getSource().getY() * 16, 2, env, 15, new Dijkstra(this.env.getTerrain()))); // new Luge

            }

        }
    }

}