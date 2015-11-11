package sample.sounds;


import sample.Main;

import java.io.File;
import javafx.scene.media.AudioClip;

/**
 * Created by simon on 11.11.15.
 */
public class Soundmanager {
    private static String appendPathLinux = "src/sample/sounds/";
    private static String appendPathWin = "Quellcode/src/sample/sounds/";
    private static String appendPath = null;
    private static String FILE1 = "output.mp3";
    public static void playSound1(){
        if(appendPath == null){
            if(Main.isWindows()){
                appendPath = appendPathWin;
            } else {
                appendPath = appendPathLinux;
            }
        }
        AudioClip plonkSound = new AudioClip(new File(appendPath + FILE1).toURI().toString());
        plonkSound.play();
    }
}
