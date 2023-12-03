package fr.iut.montreuil.stationski.Controleur;

public class SessionStart {

    public static Session userSession;

    public static void startSession(String session){
        userSession = new Session(session);
    }

    public static void endSession(){
        userSession = null;
    }

    public static boolean sessionOn(){return userSession != null;}
}
