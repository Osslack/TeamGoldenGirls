package sample.sounds;


import javafx.scene.media.AudioClip;
import sample.Main;

import java.io.File;

/**
 * Created by simon on 11.11.15.
 */
public class Soundmanager {
    private String appendPathLinux = "src/sample/sounds/";
    private String appendPathWin = "Quellcode/src/sample/sounds/";
    private String appendPath = null;
    private String FILE1 = "sound1.mp3";
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
