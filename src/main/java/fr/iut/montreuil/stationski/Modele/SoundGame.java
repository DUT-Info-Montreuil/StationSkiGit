package fr.iut.montreuil.stationski.Modele;

import fr.iut.montreuil.stationski.Modele.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundGame {

    public static Sound sound = new Sound();

    public static void loop(){
        sound.getClip().loop(Clip.LOOP_CONTINUOUSLY);
    }

    public  static void stop(){
        sound.getClip().stop();
    }


    public static void playSoundEffect(int i){
        sound.setFile(i);
        sound.play();
    }

}
