package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import sample.model.Keyboard;

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

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		launchField.setText(Keyboard.getLaunchKey().getName());
		leftField.setText(Keyboard.getMoveLeftKey().getName());
		rightField.setText(Keyboard.getMoveRightKey().getName());
		upField.setText(Keyboard.getMoveUpKey().getName());
		downField.setText(Keyboard.getMoveDownKey().getName());

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
