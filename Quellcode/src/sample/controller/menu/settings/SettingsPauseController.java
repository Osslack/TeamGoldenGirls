package sample.controller.menu.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.model.data.Preferences;

/**
 * @author Nils
 */
public class SettingsPauseController implements Initializable {

	@FXML
	private Button cancelButton;

	@FXML
	private Button saveButton;

	@FXML
	private GridPane settings;

	private Label       launchField;
	private Label       leftField;
	private Label       rightField;
	private Label       upField;
	private Label       downField;
	private Label       startField;
	private Label       retryField;
	private RadioButton radioOff;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		getGridpaneChilds();

		saveButton.setOnAction(event -> {
			Preferences.setLaunchKey(KeyCode.getKeyCode(launchField.getText()));

			Preferences.setMoveLeftKey(KeyCode.getKeyCode(leftField.getText()));
			Preferences.setMoveRightKey(KeyCode.getKeyCode(rightField.getText()));
			Preferences.setMoveUpKey(KeyCode.getKeyCode(upField.getText()));
			Preferences.setMoveDownKey(KeyCode.getKeyCode(downField.getText()));

			Preferences.setStartKey(KeyCode.getKeyCode(startField.getText()));
			Preferences.setRetryKey(KeyCode.getKeyCode(retryField.getText()));

			if (radioOff.isSelected()) {
				Preferences.setMuted(true);
			}
			else {
				Preferences.setMuted(false);
			}
			Main.setScene(Main.getGamelogic().getCurrentSceneName());
		});

		cancelButton.setOnAction(event -> Main.setScene(Main.getGamelogic().getCurrentSceneName()));
	}

	private void getGridpaneChilds() {
		launchField = (Label) settings.lookup("#launchField");
		leftField = (Label) settings.lookup("#leftField");
		rightField = (Label) settings.lookup("#rightField");
		upField = (Label) settings.lookup("#upField");
		downField = (Label) settings.lookup("#downField");
		startField = (Label) settings.lookup("#startField");
		retryField = (Label) settings.lookup("#retryField");
		radioOff = (RadioButton) settings.lookup("#radioOff");
	}
}
