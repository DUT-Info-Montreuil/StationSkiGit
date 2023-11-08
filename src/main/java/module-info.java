module fr.iut.montreuil.stationski {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens fr.iut.montreuil.stationski to javafx.fxml;
    exports fr.iut.montreuil.stationski;
    exports fr.iut.montreuil.stationski.Modele;
    opens fr.iut.montreuil.stationski.Modele to javafx.fxml;
    exports fr.iut.montreuil.stationski.Controleur;
    opens fr.iut.montreuil.stationski.Controleur to javafx.fxml;
    exports fr.iut.montreuil.stationski.Modele.Ennemis;
    opens fr.iut.montreuil.stationski.Modele.Ennemis to javafx.fxml;
    exports fr.iut.montreuil.stationski.Modele.Projectiles;
    opens fr.iut.montreuil.stationski.Modele.Projectiles to javafx.fxml;
    exports fr.iut.montreuil.stationski.Modele.Competences;
    opens fr.iut.montreuil.stationski.Modele.Competences to javafx.fxml;
    exports fr.iut.montreuil.stationski.Modele.DijsktraClasses;
    opens fr.iut.montreuil.stationski.Modele.DijsktraClasses to javafx.fxml;
    exports fr.iut.montreuil.stationski.Modele.Ambiance;
    opens fr.iut.montreuil.stationski.Modele.Ambiance to javafx.fxml;
}