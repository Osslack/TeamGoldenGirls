package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.model.Keyboard;

/**
 * @author Nils
 */
public class SettingsPauseController implements Initializable {

	public Button   cancelButton;
	public Button   saveButton;
	public GridPane settings;

	public Label       launchField;
	public Label       leftField;
	public Label       rightField;
	public Label       upField;
	public Label       downField;
	public RadioButton radioOff;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		getGridpaneChilds();

		saveButton.setOnAction(event -> {
			Keyboard.setLaunchKey(KeyCode.getKeyCode(launchField.getText()));
			Keyboard.setMoveLeftKey(KeyCode.getKeyCode(leftField.getText()));
			Keyboard.setMoveRightKey(KeyCode.getKeyCode(rightField.getText()));
			Keyboard.setMoveUpKey(KeyCode.getKeyCode(upField.getText()));
			Keyboard.setMoveDownKey(KeyCode.getKeyCode(downField.getText()));
			//TODO set sound on or off
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
		radioOff = (RadioButton) settings.lookup("#radioOff");
	}
}
