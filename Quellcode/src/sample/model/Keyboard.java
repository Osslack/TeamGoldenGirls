package sample.model;

import javafx.scene.input.KeyCode;

/**
 * @author Nils
 * JENDRIK
 */
public class Keyboard {

	private static KeyCode launchKey     = KeyCode.SPACE;
	private static KeyCode moveLeftKey   = KeyCode.A;
	private static KeyCode moveRightKey  = KeyCode.D;
	private static KeyCode moveUpKey     = KeyCode.W;
	private static KeyCode moveDownKey   = KeyCode.S;
	private static KeyCode startRoundKey = KeyCode.ENTER;
	private static KeyCode pauseMenuKey  = KeyCode.ESCAPE;
	private static KeyCode newRoundKey   = KeyCode.R;

	public static KeyCode getNewRoundKey() {
		return newRoundKey;
	}

	public static KeyCode getStartRoundKey() {
		return startRoundKey;
	}

	public static KeyCode getPauseMenuKey() {
		return pauseMenuKey;
	}

	public static KeyCode getLaunchKey() {
		return launchKey;
	}

	public static void setLaunchKey(final KeyCode launchKey) {
		Keyboard.launchKey = launchKey;
	}

	public static KeyCode getMoveLeftKey() {
		return moveLeftKey;
	}

	public static void setMoveLeftKey(final KeyCode moveLeftKey) {
		Keyboard.moveLeftKey = moveLeftKey;
	}

	public static KeyCode getMoveRightKey() {
		return moveRightKey;
	}

	public static void setMoveRightKey(final KeyCode moveRightKey) {
		Keyboard.moveRightKey = moveRightKey;
	}

	public static KeyCode getMoveUpKey() {
		return moveUpKey;
	}

	public static void setMoveUpKey(final KeyCode moveUpKey) {
		Keyboard.moveUpKey = moveUpKey;
	}

	public static KeyCode getMoveDownKey() {
		return moveDownKey;
	}

	public static void setMoveDownKey(final KeyCode moveDownKey) {
		Keyboard.moveDownKey = moveDownKey;
	}
}
