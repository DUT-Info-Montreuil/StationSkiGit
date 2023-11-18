package fr.iut.montreuil.stationski.tdtest;

import fr.iut.montreuil.stationski.Modele.DijsktraClasses.Sommet;
import fr.iut.montreuil.stationski.Modele.Ennemis.Yeti;
import fr.iut.montreuil.stationski.Modele.Environnement;
import fr.iut.montreuil.stationski.Modele.Terrain;
import fr.iut.montreuil.stationski.Modele.Tour;
import fr.iut.montreuil.stationski.Modele.Tours.CanonEau;
import fr.iut.montreuil.stationski.*;
import fr.iut.montreuil.stationski.Modele.Tours.CanonNeige;
import fr.iut.montreuil.stationski.Modele.Vague;
import fr.iut.montreuil.stationski.Vue.VueTerrain;
import fr.iut.montreuil.stationski.Vue.VueTerrainAléatoire;
import javafx.scene.layout.TilePane;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YetiTest {
    private Environnement env;

    @Test
    void créerTerrainPourYeti(){
        boolean correct = true;
        TilePane root = new TilePane();
        VueTerrain vueTerrain = new VueTerrain(env, root, ChoixMap.getChoix());
        Terrain terrain = new Terrain(45, 45, ChoixMap.getChoix(), vueTerrain.créerListeTerrain());
        env = new Environnement(terrain);
        Vague vague = new Vague(env);

        Yeti yeti = new Yeti(10, 350, env, vague);
        int compte = 0;


        for(Integer i :  yeti.getTerrain().getTerrain()){
            if(i !=0 ){
                correct = false;
            }

        }

        assertTrue(correct);
    }



    @Test
    void seDeplace() {
        TilePane root = new TilePane();
        VueTerrain vueTerrain = new VueTerrain(env, root, ChoixMap.getChoix());
        Terrain terrain = new Terrain(45, 45, ChoixMap.getChoix(), vueTerrain.créerListeTerrain());
        env = new Environnement(terrain);
        Vague vague = new Vague(env);

        Tour tour1 = new CanonEau(496, 64, env);
        //Tour tour2 = new CanonNeige(400, 350, env);
        env.addTour(tour1);

        Yeti yeti = new Yeti(10, 10, env, vague);
        while(yeti.getPosX()!= tour1.getPosX() || yeti.getPosY()!= tour1.getPosY()){
            yeti.seDeplace();
        }
        Assertions.assertTrue(yeti.getPosX()== tour1.getPosX() && yeti.getPosY()== tour1.getPosY(), "Le yéti se déplace jusque la Tour");
        env.mortTour(0);
        yeti.renouvelerTerrain();
        while(yeti.getPosX()!=env.getTerrain().getCible().getX()*16 || yeti.getPosY()!=env.getTerrain().getCible().getY()*16) yeti.seDeplace();
        yeti.seDeplace();
        Assertions.assertEquals(0, yeti.getPV(), "le Yeti se déplace jusqu'à la station en bas de la piste lorsqu'il n'y a pas de tours.");

    }

    @Test
    void renouvelerTerrain() {
        TilePane root = new TilePane();
        VueTerrain vueTerrain = new VueTerrain(env, root, ChoixMap.getChoix());
        Terrain terrain = new Terrain(45, 45, ChoixMap.getChoix(), vueTerrain.créerListeTerrain());
        env = new Environnement(terrain);
        Vague vague = new Vague(env);

        Tour tour1 = new CanonEau(500, 65, env);
        //Tour tour2 = new CanonNeige(400, 350, env);
        env.addTour(tour1);

        Yeti yeti = new Yeti(10, 10, env, vague);

        //yeti.seDeplace();
        yeti.renouvelerTerrain();
        assertEquals(yeti.getDijkstra().getCible().getX(), tour1.getPosX()/16 );
        assertEquals(yeti.getDijkstra().getCible().getY(), tour1.getPosY()/16 );
        assertEquals(yeti.getDijkstra().getSource().getX(), yeti.getPosX()/16 );
        assertEquals(yeti.getDijkstra().getSource().getY(), yeti.getPosY()/16 );


        env.mortTour(0);
        yeti.renouvelerTerrain();
        assertEquals(yeti.getDijkstra().getCible().getX(), env.getTerrain().getCible().getX());
        assertEquals(yeti.getDijkstra().getCible().getY(), env.getTerrain().getCible().getY());
        assertEquals(yeti.getDijkstra().getSource().getX(), yeti.getPosX()/16);
        assertEquals(yeti.getDijkstra().getSource().getY(), yeti.getPosY()/16);


    }

    @Test
    void attaque() {

        TilePane root = new TilePane();
        VueTerrain vueTerrain = new VueTerrain(env, root, ChoixMap.getChoix());
        Terrain terrain = new Terrain(45, 45, ChoixMap.getChoix(), vueTerrain.créerListeTerrain());
        env = new Environnement(terrain);
        Vague vague = new Vague(env);

        Tour tour1 = new CanonEau(500, 65, env);
        Tour tour2 = new CanonNeige(400, 350, env);
       env.addTour(tour2);
        env.addTour(tour1);


        Yeti yeti = new Yeti(10, 10, env, vague);




        yeti.attaque(tour1);
        assertEquals(tour1.getPV(), tour1.getPVMax()- 1);
        yeti.attaque((tour1));
        assertNotEquals(tour1.getPV(), tour1.getPVMax()- 1);


    }

    @Test
    void avancer() {

        TilePane root = new TilePane();
        VueTerrain vueTerrain = new VueTerrain(env, root, ChoixMap.getChoix());
        Terrain terrain = new Terrain(45, 45, ChoixMap.getChoix(), vueTerrain.créerListeTerrain());
        env = new Environnement(terrain);
        Vague vague = new Vague(env);

        Tour tour2 = new CanonNeige(400, 350, env);
        env.addTour(tour2);


        Yeti yeti = new Yeti(10, 350, env, vague);

        yeti.renouvelerTerrain();
        yeti.avancer();



        assertTrue(encadrement(yeti.getPosX(), 11));

        env.mortTour(0);

        Tour tour1 = new CanonNeige(11, 600, env);
        env.addTour(tour1); //tour cible, se déplace vers les tours



        yeti.renouvelerTerrain();
        yeti.avancer();


        assertTrue(encadrement(yeti.getPosY(), 350));


    }

    public boolean encadrement (int x, int expected){
        return x <= expected +1 && x>= expected-1;
    }
}