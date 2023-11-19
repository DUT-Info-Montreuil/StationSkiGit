package fr.iut.montreuil.stationski.Vue;

import fr.iut.montreuil.stationski.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Sprite extends ImageView {
    private Image image;

    public Sprite (String entiteNom){
        this.image = new Image(donnerSourceImage(entiteNom));
        this.setImage(this.image);
    }
    public Sprite (String entiteNom, String direction){
        this.image = new Image (donnerSourceImage(entiteNom, direction));
        this.setImage(this.image);
    }
    public String donnerSourceImage (String entiteNom){

        switch (entiteNom){
            case "moniteur" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/mono.png"));
            case "skieur" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/skieur1.png"));
            case "luge" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/luge2.png"));
            case "bobsleigh" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/bobsleigh2.png"));
            case "yeti" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/yeti3.png"));
            case "snowboarder" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/snowboarder.png"));
            case "projectileAllier" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/balle.png"));
            case "projectileCanonNeige" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/projneige.png"));
            case "projectileCanonEau" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/jetEau.png"));
            case "projectileTelesiege" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/cabineTelesiege.png"));
            case "projectileTeleski" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/cabineTeleski2.png"));
            case "projectileBiathlon" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/balle.png"));
            case "biathlon" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/biathlon3.png"));
            case "cahute" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/cahute3.png"));
            case "canonEau" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/canoneauv3.png"));
            case "telesiege" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/telesiege3.png"));
            case "donotcross" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/DoNotCross2.png"));
            case "canonNeige" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/canonNeige3.png"));
            case "teleski" :
                return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/teleski3.png"));
        }
        return "";
    }

    public String donnerSourceImage (String entiteNom, String direction){

        if (entiteNom.equals("moniteur") && direction.equals("g")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/monoG.png"));
        else if (entiteNom.equals("moniteur") && direction.equals("d")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/mono.png"));

        else if (entiteNom.equals("skieur") && direction.equals("g")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/skieur1G.png"));
        else if (entiteNom.equals("skieur") && direction.equals("d")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/skieur1.png"));

        else if (entiteNom.equals("yeti") && direction.equals("h")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/yeti3Dos.png"));
        else if (entiteNom.equals("yeti") && direction.equals("b")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/yeti3.png"));

        else if (entiteNom.equals("bobsleigh") && direction.equals("g")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/bobsleigh2G.png"));
        else if (entiteNom.equals("bobsleigh") && direction.equals("d")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/bobsleigh2.png"));

        else if (entiteNom.equals("luge") && direction.equals("g")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/luge2G.png"));
        else if (entiteNom.equals("luge") && direction.equals("d")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/luge2.png"));

        else if (entiteNom.equals("snowboarder") && direction.equals("g")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/snowboarderG.png"));
        else if (entiteNom.equals("snowboarder") && direction.equals("d")) return String.valueOf(Main.class.getResource("/fr/iut/montreuil/stationski/images/snowboarder.png"));

        return "";
    }
}
