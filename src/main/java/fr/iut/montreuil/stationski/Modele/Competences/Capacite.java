package fr.iut.montreuil.stationski.Modele.Competences;


import fr.iut.montreuil.stationski.Modele.Environnement;

public abstract class Capacite {
    protected String nom;
    protected int cout;
    protected Environnement env;

    public Capacite (String nom, int cout, Environnement env){
        this.nom =nom;
        this.cout = cout;
        this.env=env;
    }

    public abstract void effet(int acteur);
    public abstract void parcours();
    public void activation (){
        /*for (int acteur = this.env.getVague().getListEnnemis().size()-1; acteur>=0; acteur--){
            effet(acteur);
        }
        this.env.retraitArgent(this.cout);
         */

        parcours();
    }
    public String getNom() {
        return nom;
    }

    public int getCout() {
        return cout;
    }


}
