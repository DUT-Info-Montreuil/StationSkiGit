package fr.iut.montreuil.stationski.Modele;

import fr.iut.montreuil.stationski.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public  class Sound {
    private  Clip clip;
    private URL soundUrl[] = new URL[20];
    public Sound(){
        soundUrl[0] = Main.class.getResource("/Sound/theme.wav");
        soundUrl[1] = getClass().getResource("/fr/iut/montreuil/stationski/Sound/clicSouris_1.wav");
        soundUrl[2] = Main.class.getResource("/fr/iut/montreuil/stationski/Sound/ecranAccueilValider.wav");
        soundUrl[3] = Main.class.getResource("/fr/iut/montreuil/stationski/Sound/pendantJeu.wav");
        soundUrl[4] = Main.class.getResource("/fr/iut/montreuil/stationski/Sound/TirGun.wav");
        soundUrl[5] = Main.class.getResource("/Sound/theme.wav");


    }

    public void setFile(int i){
        try{


            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }catch(Exception e){

        }
    }


    public void play(){
        clip.start();
    }
    public Clip getClip(){return this.clip;}


}