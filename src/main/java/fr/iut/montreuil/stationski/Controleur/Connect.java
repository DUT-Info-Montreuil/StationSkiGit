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

    public static void executeQuery(String query) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            statement =con.prepareStatement("SELECT * from employee WHERE  userID = ?");
            statement.setString(1, userID);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}