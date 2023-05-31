module fr.iut.montreuil.stationski {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.iut.montreuil.stationski to javafx.fxml;
    exports fr.iut.montreuil.stationski;
    exports fr.iut.montreuil.stationski.Modele;
    opens fr.iut.montreuil.stationski.Modele to javafx.fxml;
    exports fr.iut.montreuil.stationski.Controleur;
    opens fr.iut.montreuil.stationski.Controleur to javafx.fxml;
}