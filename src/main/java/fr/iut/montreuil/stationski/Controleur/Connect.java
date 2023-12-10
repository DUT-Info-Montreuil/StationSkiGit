package fr.iut.montreuil.stationski.Controleur;


import java.sql.*;
import java.util.Objects;



public class Connect {
    private static Connection conn;
    private static Object[] result;

    Parametres para = new Parametres();
    public Connect(){
        System.out.println("lancement de la connexion");
        try {
            // Class.forName(para.JDBC_DRIVER);
            conn = DriverManager.getConnection(para.getUrl(), para.getUser(), para.getPwd());

            //Connection idConnect = DriverManager.getConnection(para.getUrl(), para.getUser(), para.getPwd());
            System.out.println("Connexion établie");

        } catch (SQLException  e ) {
            System.out.println("Erreur de connexion : "+ e.getMessage());
        }


    }


    public static Connection getConn(){return conn;}

    public static void executeQuery(String query, Object[] tab) {
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            for(int i = 0; i<tab.length; i++) {
                stmt.setString(i+1, tab[i].toString());
            }
            stmt.execute();



        } catch (SQLException e) {
            System.out.println(e);
        }

    }


    public static String executeQueryWithResult(String query, Object[] tab){

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            for(int i = 0; i<tab.length; i++) {
                stmt.setString(i+1, tab[i].toString());
            }
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                return res.getString(1);
            }
            else return null;


        } catch (SQLException e) {
            System.out.println(e);
            return "Error";
        }

    }

    public static void startGame(int map){
        Object [] tab = {};
        executeQuery("Insert into partie (score, victoire) value(0, NULL);", tab );
        Partie.setNumeroPartie(Integer.parseInt(Objects.requireNonNull(Connect.executeQueryWithResult("SELECT idPartie FROM partie WHERE idPartie = (SELECT MAX(idPartie) FROM partie)", tab))));
        Object[] tab2 = {"" + Partie.getNumeroPartie(), ""+map};
        executeQuery("Insert into a_eu_lieu_dans (idPartie, idMap) value(?, ?);", tab2);
        Object[] tab3 = {"" + Partie.getNumeroPartie(), ""+Partie.getIdJoueur()};
        executeQuery("Insert into a_joué (idPartie, idUtilisateur) value(?, ?);", tab3);

    }
}