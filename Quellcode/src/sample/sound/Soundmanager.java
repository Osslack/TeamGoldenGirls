/*used*/

package sample.sound;


import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import javafx.scene.media.AudioClip;
import sample.Main;

/**
 * Created by simon on 11.11.15.
 * This class is responsible for playing sounds
 */
public class Soundmanager {
	private final String appendPathLinux  = "src/sample/sound/sounds/";
	private final String appendPathWin    = "Quellcode/src/sample/sound/sounds/";
	private       String appendPath       = null;
	private final String generalNamePre   = "sound";
	private final String generalNameSuf   = ".mp3";
	private final int    number_of_sounds = 11;
	private final AudioClip[] sounds;
	private Date lastSoundPlayed = null;
	//load all sounds
	public Soundmanager() {
		if (appendPath == null) {
			if (Main.isWindows()) {
				appendPath = appendPathWin;
			}
			else {
				appendPath = appendPathLinux;
			}
		}
		sounds = new AudioClip[number_of_sounds];
		for (int i = 0; i < number_of_sounds; ++i) {
			sounds[i] = new AudioClip(new File(appendPath + generalNamePre + (i + 1) + generalNameSuf).toURI().toString());
		}
	}
	//play the specified sound
	public void playSound(int number) {
		if (lastSoundPlayed == null) {
			lastSoundPlayed = new Timestamp(System.currentTimeMillis());
		}
		if (number <= number_of_sounds && okayToPlay()) {

			sounds[number - 1].play();

		}
	}
    //play a sound in the specified range
	public void playRandSound(int min, int max) {
		if (max <= number_of_sounds && okayToPlay()) {
			sounds[(int) (Math.random() * max) + min - 1].play();
		}
	}
    //make sure that there is a break inbetween sounds
	private boolean okayToPlay() {
		if (lastSoundPlayed == null) {
			lastSoundPlayed = new Date(System.currentTimeMillis());
			return true;
		}
		Date now = new Date(System.currentTimeMillis());
		long difference = now.getTime() - lastSoundPlayed.getTime();
		lastSoundPlayed = now;
		return difference >= 500;

	}
    //mute all sounds
	public void muteAll() {
		for (AudioClip a : sounds) {
			a.setVolume(0.0);
		}
	}
    //unmute all sounds
	public void unmuteAll() {
		for (AudioClip a : sounds) {
			a.setVolume(1.0);
		}
	}


}
