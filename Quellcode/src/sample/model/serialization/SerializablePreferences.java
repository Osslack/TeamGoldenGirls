package sample.model.serialization;

import java.io.Serializable;
import javafx.scene.input.KeyCode;

/**
 * @author Nils
 */
public class SerializablePreferences implements Serializable {

	public boolean muted;
	public KeyCode launchKey,
			moveLeftKey,
			moveRightKey,
			moveUpKey,
			moveDownKey;

	public SerializablePreferences(final KeyCode launchKey, final KeyCode moveLeftKey, final KeyCode moveRightKey, final KeyCode moveUpKey, final KeyCode moveDownKey, final boolean muted) {
		this.muted = muted;
		this.launchKey = launchKey;
		this.moveLeftKey = moveLeftKey;
		this.moveRightKey = moveRightKey;
		this.moveUpKey = moveUpKey;
		this.moveDownKey = moveDownKey;
	}
}
