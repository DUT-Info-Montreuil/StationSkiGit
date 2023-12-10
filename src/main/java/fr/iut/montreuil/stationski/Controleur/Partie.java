package fr.iut.montreuil.stationski.Controleur;

public class Partie {

    private static int numeroPartie = 0;
    private static int idJoueur = 0;

    public static int getNumeroPartie(){
        return numeroPartie;
    }

    public static void setNumeroPartie(int nm){numeroPartie = nm;}

    public static void setIdJoueur(int id){idJoueur= id;}

    public static int getIdJoueur(){return idJoueur;}
}
