package sample.model.serialization;

import java.io.Serializable;
import javafx.scene.input.KeyCode;

/**
 * @author Nils
 */
public class SerializablePreferences implements Serializable {

	public final boolean muted;
	public final KeyCode launchKey;
	public final KeyCode moveLeftKey;
	public final KeyCode moveRightKey;
	public final KeyCode moveUpKey;
	public final KeyCode moveDownKey;

	public final KeyCode startKey;
	public final KeyCode retryKey;

	public SerializablePreferences(final KeyCode launchKey, final KeyCode moveLeftKey, final KeyCode moveRightKey, final KeyCode moveUpKey, final KeyCode moveDownKey, final KeyCode startKey, final KeyCode retryKey, final boolean muted) {
		this.muted = muted;
		this.launchKey = launchKey;
		this.moveLeftKey = moveLeftKey;
		this.moveRightKey = moveRightKey;
		this.moveUpKey = moveUpKey;
		this.moveDownKey = moveDownKey;
		this.startKey = startKey;
		this.retryKey = retryKey;
	}
}
