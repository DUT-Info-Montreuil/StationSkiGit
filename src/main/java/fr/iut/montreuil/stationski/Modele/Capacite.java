package fr.iut.montreuil.stationski.Modele;

public abstract class Capacite {
    protected String nom;
    protected int cout;
    protected Environnement env;

    // ATTENTION : à chaque nouvelle capacité : prog la méthode activation + relier vue et activation +
    // creer la capa et la placer dans env dans le controleur
    public Capacite (String nom, int cout, Environnement env){
        this.nom =nom;
        this.cout = cout;
        this.env=env;
    }

    public abstract void activation ();
    public String getNom() {
        return nom;
    }

    public int getCout() {
        return cout;
    }


}
