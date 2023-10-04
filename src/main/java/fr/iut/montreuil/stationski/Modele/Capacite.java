package fr.iut.montreuil.stationski.Modele;


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
    public void activation (){
        for (int acteur = this.env.getVague().getListEnnemis().size()-1; acteur>=0; acteur--){
            effet(acteur);
        }
        this.env.retraitArgent(this.cout);
    }
    public String getNom() {
        return nom;
    }

    public int getCout() {
        return cout;
    }


}
