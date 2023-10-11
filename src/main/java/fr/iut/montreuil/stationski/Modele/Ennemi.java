package fr.iut.montreuil.stationski.Modele;


import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public abstract class Ennemi extends Acteur {
    private int vitesse;
    private int vitesseI;
    private int butin;
    private Dijkstra dijkstra;
    private int importance;
    private boolean ralenti;
    private int tourR;
    private int finTourR;
    protected Vague vague;
    private StringProperty direction;

    public Ennemi (int pv, int posX, int posY, int vitesse, Environnement env, int butin, Dijkstra dijkstra, Vague vague, int importance){
        super(pv, posX, posY, env);
        this.vague = vague;
        this.importance = importance;
        this.vitesse=vitesse;
        vitesseI = vitesse;
        this.butin = butin;
        this.dijkstra = dijkstra;
        this.ralenti = false;
        tourR = 0;
        finTourR = 250;
        direction = new SimpleStringProperty("b");
    }

    public Ennemi (int pv, int posX, int posY, int vitesse, Environnement env, int butin, Vague vague, int importance){
        super(pv, posX, posY, env);
        this.vague = vague;
        this.importance = importance;
        this.vitesse=vitesse;
        this.butin = butin;
        direction = new SimpleStringProperty("b");
    }

    public void agit(){
        estRalenti();
        for (int v = 0; v <vitesse; v++) {
            seDeplace();
        }
    }
    public void estRalenti(){
        if (ralenti){
            dimVitesseDeN(5);
            tourR++;
        }
        if (tourR >= finTourR){
            tourR = 0;
            finTourR = 250;
            ralenti = false;
            setVitesse(vitesseI);
        }
    }

    public StringProperty getDirectionP() {
        return direction;
    }

    public void setFinTourR(int finTourR) {
        this.finTourR = finTourR;
    }

    public void seDeplace(){
        //deplacement tres simple
        if (this.dijkstra.getParcours().size() > 0) {
            Sommet sommetCible = this.dijkstra.getParcours().get(this.dijkstra.getParcours().size()-1);

            int sommetX = sommetCible.getX()*16;
            int sommetY = sommetCible.getY()*16;

            // Parcours pixel par pixel aléatoire du terrain
            if(Math.random()*1>0.5 && this.posX.getValue()!=sommetX){
                if (sommetX>this.posX.getValue()){
                    this.posX.setValue(this.posX.getValue()+1);
                    direction.setValue("d");
                }
                else{
                    this.posX.setValue(this.posX.getValue()-1);
                    direction.setValue("g");
                }
            }
            else if (Math.random()*1>0.8 && this.posX.getValue()!=sommetX && this.posY.getValue()!=sommetY){ // Aller en diagonale
                if (sommetX>this.posX.getValue() && sommetY>this.posY.getValue()){
                    this.posX.setValue(this.posX.getValue()+1);
                    this.posY.setValue(this.posY.getValue()+1);
                }
                else if (sommetX<this.posX.getValue() && sommetY>this.posY.getValue()){
                    this.posX.setValue(this.posX.getValue()-1);
                    this.posY.setValue(this.posY.getValue()+1);
                }
                else if (sommetX<this.posX.getValue() && sommetY<this.posY.getValue()){
                    this.posX.setValue(this.posX.getValue()-1);
                    this.posY.setValue(this.posY.getValue()-1);
                }
                else{
                    this.posX.setValue(this.posX.getValue()+1);
                    this.posY.setValue(this.posY.getValue()-1);
                }
            }
            else if (this.posY.getValue()!=sommetY){
                if (sommetY>this.posY.getValue()){
                    this.posY.setValue(this.posY.getValue()+1);
                }
                else{
                    this.posY.setValue(this.posY.getValue()-1);
                }
            }


            // Si on arrive au point du sommet cible, on passe au prochain sommet cible
            if (sommetX==this.posX.getValue() && sommetY==this.posY.getValue())
                this.dijkstra.getParcours().remove(this.dijkstra.getParcours().size()-1);
        }
        else{
            System.out.println();
            this.getEnv().objAttaque(this.importance);
            super.setPV(0);
        }
    }



    public int getButin() {
        return butin;
    }
    public void dimVitesseDeN(int n){
        vitesse -= n;
        if (vitesse <=0){
            vitesse =1;
        }
    }

    public int getImportance() {
        return importance;
    }

    public void meurt(){
        this.getEnv().ajoutArgent(butin);
    }

    public void setRalenti (boolean b){
        this.ralenti = b;
    }

    public void setVitesse(int v){
        vitesse = v;
    }

    public Dijkstra getDijkstra(){return this.dijkstra;}
    public void setDijkstra(Dijkstra dijkstra) {this.dijkstra=dijkstra;}
}