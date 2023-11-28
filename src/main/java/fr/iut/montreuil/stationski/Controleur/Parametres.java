package fr.iut.montreuil.stationski.Controleur;

public class Parametres {
    String url;
    String user;
    String pwd;
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public Parametres(){

        this.url = "jdbc:mysql://localhost:3306/bdjava";
        //this.url = "jdbc:postgresql://database-etudiants.iut.univ-paris8.fr/gpons";
        this.user = "root";


        this.pwd = "";
    }

    public String getPwd(){
        return pwd;
    }

    public String getUrl(){return this.url;}

    public String getUser(){return this.user;}

}