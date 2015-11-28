/*used*/
package sample.model.data;

import javafx.scene.input.KeyCode;
import sample.Main;

/**
 * @author Nils
 *         JENDRIK
 */
public class Preferences {

	private static boolean muted = false;

	private static KeyCode launchKey    = KeyCode.SPACE;
	private static KeyCode moveLeftKey  = KeyCode.A;
	private static KeyCode moveRightKey = KeyCode.D;
	private static KeyCode moveUpKey    = KeyCode.W;
	private static KeyCode moveDownKey  = KeyCode.S;

	private static       KeyCode startKey     = KeyCode.ENTER;
	private static final KeyCode pauseMenuKey = KeyCode.ESCAPE;
	private static       KeyCode retryKey     = KeyCode.R;

	public static KeyCode getPauseMenuKey() {
		return pauseMenuKey;
	}

	public static KeyCode getLaunchKey() {
		return launchKey;
	}

	public static void setLaunchKey(final KeyCode launchKey) {
		Preferences.launchKey = launchKey;
	}

	public static KeyCode getMoveLeftKey() {
		return moveLeftKey;
	}

	public static void setMoveLeftKey(final KeyCode moveLeftKey) {
		Preferences.moveLeftKey = moveLeftKey;
	}

	public static KeyCode getMoveRightKey() {
		return moveRightKey;
	}

	public static void setMoveRightKey(final KeyCode moveRightKey) {
		Preferences.moveRightKey = moveRightKey;
	}

	public static KeyCode getMoveUpKey() {
		return moveUpKey;
	}

	public static void setMoveUpKey(final KeyCode moveUpKey) {
		Preferences.moveUpKey = moveUpKey;
	}

	public static KeyCode getMoveDownKey() {
		return moveDownKey;
	}

	public static void setMoveDownKey(final KeyCode moveDownKey) {
		Preferences.moveDownKey = moveDownKey;
	}

	public static void setStartKey(final KeyCode startKey) {
		Preferences.startKey = startKey;
	}

	public static void setRetryKey(final KeyCode retryKey) {
		Preferences.retryKey = retryKey;
	}

	public static KeyCode getRetryKey() {
		return retryKey;
	}

	public static KeyCode getStartKey() {
		return startKey;
	}

	public static boolean isMuted() {
		return muted;
	}

	public static void setMuted(final boolean muted) {
		Preferences.muted = muted;
		if (muted) {
			Main.getSoundmanager().muteAll();
		}
		else {
			Main.getSoundmanager().unmuteAll();
		}
	}
}
