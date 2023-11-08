package fr.iut.montreuil.stationski.Controleur;

public class Parametres {
    String url;
    String user;
    String pwd;

    public Parametres(){
        this.url = "jdbc:postgresql://database-etudiants.iut.univ-paris8.fr/gpons";
        this.user = "gpons";













        this.pwd = "nananed";
    }

    public String getPwd(){
        return pwd;
    }

    public String getUrl(){return this.url;}

    public String getUser(){return this.user;}

}
