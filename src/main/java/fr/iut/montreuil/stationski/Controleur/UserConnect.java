package fr.iut.montreuil.stationski.Controleur;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UserConnect {

    public static boolean connectUser(String login, String mdp) {

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = digest.digest(mdp.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);

        String[] param = {mdp};
        String realmdp = Connect.executeQueryWithResult("SELECT motdepasse FROM utilisateur WHERE identifiant= ? ;", param);
        if (realmdp != null && realmdp.equals(mdp)) {
            System.out.println("Connexion");
            SessionStart.startSession(login);
            return true;
        } else System.out.println("echec de la connexion au compte");
        return false;
    }
}
/*
    public static boolean signupUser(String login, String mdp){

        try{

        }catch

    }

*/


/*
$sth = self::$bdd->prepare('SELECT mdp FROM Utilisateur WHERE login=:login;');
        $sth->bindValue(':login', $_POST['login']);
        $sth->execute();
        $res = $sth->fetch();



        if(password_verify($_POST['mdp'], $res['mdp'])){
        $_SESSION["login"] = $_POST['login'];
        echo 'connecté en tant que '.$_SESSION["login"];

*/