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

	public GridPane    grid;
	public Label       launchField;
	public Label       leftField;
	public Label       rightField;
	public Label       upField;
	public Label       downField;
	public RadioButton radioOff;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		launchField.setText(Preferences.getLaunchKey().getName());
		leftField.setText(Preferences.getMoveLeftKey().getName());
		rightField.setText(Preferences.getMoveRightKey().getName());
		upField.setText(Preferences.getMoveUpKey().getName());
		downField.setText(Preferences.getMoveDownKey().getName());
		radioOff.setSelected(Preferences.isMuted());

		launchField.setOnMouseClicked(event -> launchField.requestFocus());

		leftField.setOnMouseClicked(event -> leftField.requestFocus());

		rightField.setOnMouseClicked(event -> rightField.requestFocus());

		upField.setOnMouseClicked(event -> upField.requestFocus());

		downField.setOnMouseClicked(event -> downField.requestFocus());

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
	}

	private boolean isAllowedKey(final KeyCode code) {
		return code.isLetterKey() || code.isArrowKey() || code.isDigitKey() || code.isWhitespaceKey();
	}

	private boolean alreadyAssigned(final String name) {
		return name.equals(launchField.getText())
				|| name.equals(leftField.getText())
				|| name.equals(rightField.getText())
				|| name.equals(upField.getText())
				|| name.equals(downField.getText());
	}
}
