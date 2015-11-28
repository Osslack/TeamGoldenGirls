/*used*/
package sample.controller.menu.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import sample.model.data.Preferences;

/**
 * @author Nils
 */
public class SettingsController implements Initializable {

	public GridPane grid;
	public Label    launchField;
	public Label    leftField;
	public Label    rightField;
	public Label    upField;
	public Label    downField;
	public Label    startField;
	public Label    retryField;

	public RadioButton radioOff;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		launchField.setText(Preferences.getLaunchKey().getName());
		leftField.setText(Preferences.getMoveLeftKey().getName());
		rightField.setText(Preferences.getMoveRightKey().getName());
		upField.setText(Preferences.getMoveUpKey().getName());
		downField.setText(Preferences.getMoveDownKey().getName());
		startField.setText(Preferences.getStartKey().getName());
		retryField.setText(Preferences.getRetryKey().getName());
		radioOff.setSelected(Preferences.isMuted());

		launchField.setOnMouseClicked(event -> launchField.requestFocus());
		leftField.setOnMouseClicked(event -> leftField.requestFocus());
		rightField.setOnMouseClicked(event -> rightField.requestFocus());
		upField.setOnMouseClicked(event -> upField.requestFocus());
		downField.setOnMouseClicked(event -> downField.requestFocus());
		startField.setOnMouseClicked(event -> startField.requestFocus());
		retryField.setOnMouseClicked(event -> retryField.requestFocus());

		launchField.setOnKeyReleased(event -> {
			KeyCode key = event.getCode();
			String name = key.getName();
			if (isAllowedKey(key) && !alreadyAssigned(name)) {
				launchField.setText(name);
			}
			grid.requestFocus();
		});

		leftField.setOnKeyReleased(event -> {
			KeyCode key = event.getCode();
			String name = key.getName();
			if (isAllowedKey(key) && !alreadyAssigned(name)) {
				leftField.setText(name);
			}
			grid.requestFocus();
		});

		rightField.setOnKeyReleased(event -> {
			KeyCode key = event.getCode();
			String name = key.getName();
			if (isAllowedKey(key) && !alreadyAssigned(name)) {
				rightField.setText(name);
			}
			grid.requestFocus();
		});

		upField.setOnKeyReleased(event -> {
			KeyCode key = event.getCode();
			String name = key.getName();
			if (isAllowedKey(key) && !alreadyAssigned(name)) {
				upField.setText(name);
			}
			grid.requestFocus();
		});

		downField.setOnKeyReleased(event -> {
			KeyCode key = event.getCode();
			String name = key.getName();
			if (isAllowedKey(key) && !alreadyAssigned(name)) {
				downField.setText(name);
			}
			grid.requestFocus();
		});

		startField.setOnKeyReleased(event -> {
			KeyCode key = event.getCode();
			String name = key.getName();
			if (isAllowedKey(key) && !alreadyAssigned(name)) {
				startField.setText(name);
			}
			grid.requestFocus();
		});

		retryField.setOnKeyReleased(event -> {
			KeyCode key = event.getCode();
			String name = key.getName();
			if (isAllowedKey(key) && !alreadyAssigned(name)) {
				retryField.setText(name);
			}
			grid.requestFocus();
		});
	}

	private boolean isAllowedKey(final KeyCode code) {
		return code.isLetterKey() || code.isArrowKey() || code.isDigitKey() || code.isWhitespaceKey();
	}

	private boolean alreadyAssigned(final String name) {
		return name.equals(launchField.getText())
				|| name.equals(leftField.getText())
				|| name.equals(rightField.getText())
				|| name.equals(upField.getText())
				|| name.equals(downField.getText())
				|| name.equals(startField.getText())
				|| name.equals(retryField.getText());
	}
}
