package fr.iut.montreuil.stationski.Vue;

import fr.iut.montreuil.stationski.Modele.Acteur;
import fr.iut.montreuil.stationski.Modele.Entite;
import fr.iut.montreuil.stationski.Modele.Environnement;
import javafx.scene.layout.Pane;

public abstract class VueGenerique {

    private Pane panneauJeu;
    private Environnement env;

    public VueGenerique(Pane panneauJeu, Environnement env){
        this.env = env;
        this.panneauJeu = panneauJeu;
    }

    public abstract  void construitSprite(Acteur e);

    public Pane getPanneauJeu() {
        return panneauJeu;
    }
}
