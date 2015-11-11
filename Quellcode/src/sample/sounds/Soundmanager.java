package sample.sounds;


import sample.Main;

import java.io.File;
import javafx.scene.media.AudioClip;

/**
 * Created by simon on 11.11.15.
 */
public class Soundmanager {
    private String appendPathLinux = "src/sample/sounds/";
    private String appendPathWin = "Quellcode/src/sample/sounds/";
    private String appendPath = null;
    private String FILE1 = "output.mp3";
    private AudioClip m_PlonkSound;
    public Soundmanager(){
        if(appendPath == null){
            if(Main.isWindows()){
                appendPath = appendPathWin;
            } else {
                appendPath = appendPathLinux;
            }
        }
        m_PlonkSound = new AudioClip(new File(appendPath + FILE1).toURI().toString());
    }
    public void playSound1(){
        m_PlonkSound.play();
    }
}
