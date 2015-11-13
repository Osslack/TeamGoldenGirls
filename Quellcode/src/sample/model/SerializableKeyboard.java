package sample.model;

import java.io.Serializable;
import javafx.scene.input.KeyCode;

/**
 * @author Nils
 */
public class SerializableKeyboard implements Serializable {

	public KeyCode launchKey,
			moveLeftKey,
			moveRightKey,
			moveUpKey,
			moveDownKey;

	public SerializableKeyboard(final KeyCode launchKey, final KeyCode moveLeftKey, final KeyCode moveRightKey, final KeyCode moveUpKey, final KeyCode moveDownKey) {
		this.launchKey = launchKey;
		this.moveLeftKey = moveLeftKey;
		this.moveRightKey = moveRightKey;
		this.moveUpKey = moveUpKey;
		this.moveDownKey = moveDownKey;
	}
}
