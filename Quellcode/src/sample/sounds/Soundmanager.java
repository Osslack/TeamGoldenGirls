package sample.sounds;


import javafx.scene.media.AudioClip;
import sample.Main;

import java.io.File;

/**
 * Created by simon on 11.11.15.
 */
public class Soundmanager {
    public static int CLICK_SOUND = 11;
    private String appendPathLinux = "src/sample/sounds/";
    private String appendPathWin = "Quellcode/src/sample/sounds/";
    private String appendPath = null;
    private String generalNamePre = "sound";
    private String generalNameSuf = ".mp3";
    private int number_of_sounds = 11;
    private AudioClip[] sounds;
    public Soundmanager(){
        if(appendPath == null){
            if(Main.isWindows()){
                appendPath = appendPathWin;
            } else {
                appendPath = appendPathLinux;
            }
        }
        sounds = new AudioClip[number_of_sounds];
        for(int i=0;i<number_of_sounds;++i){
            sounds[i] = new AudioClip(new File(appendPath + generalNamePre + (i+1) + generalNameSuf).toURI().toString());
        }
    }
    public void playSound(int number){
        if(number <= number_of_sounds){
            sounds[number - 1].play();
        }
    }
    public void playRandSound(){
        sounds[(int)(Math.random()*number_of_sounds)].play();
    }


}
