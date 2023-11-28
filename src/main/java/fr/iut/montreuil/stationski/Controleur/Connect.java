package fr.iut.montreuil.stationski.Controleur;


import java.sql.*;


public class Connect {
    private static Connection conn;

    Parametres para = new Parametres();
    public Connect(){
        System.out.println("lancement de la connexion");
        try {
            //bn Class.forName(para.JDBC_DRIVER);
            conn = DriverManager.getConnection(para.getUrl(), para.getUser(), para.getPwd());

            //Connection idConnect = DriverManager.getConnection(para.getUrl(), para.getUser(), para.getPwd());
            System.out.println("Connexion établie");
        } catch (SQLException  e ) {
            System.out.println("Erreur de connexion : "+ e.getMessage());
        }


    }


    public static Connection getConn(){return conn;}
}