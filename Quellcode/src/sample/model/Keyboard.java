package sample.model;

import javafx.scene.input.KeyCode;

/**
 * @author Nils
 */
public class Keyboard {

	private static KeyCode launchKey = KeyCode.SPACE,
			moveLeftKey              = KeyCode.A,
			moveRightKey             = KeyCode.D,
			moveUpKey                = KeyCode.W,
			moveDownKey              = KeyCode.S;

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
