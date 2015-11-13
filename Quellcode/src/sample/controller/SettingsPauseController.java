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
		initGridpaneChilds();

		saveButton.setOnAction(event -> {
			Keyboard.setLaunchKey(KeyCode.valueOf(launchField.getText()));
			Keyboard.setMoveLeftKey(KeyCode.valueOf(leftField.getText()));
			Keyboard.setMoveRightKey(KeyCode.valueOf(rightField.getText()));
			Keyboard.setMoveUpKey(KeyCode.valueOf(upField.getText()));
			Keyboard.setMoveDownKey(KeyCode.valueOf(downField.getText()));
			//TODO set sound on or off
			Main.setScene("MainGame");
		});

		cancelButton.setOnAction(event -> Main.setScene("MainGame"));
	}

	private void initGridpaneChilds() {
		launchField = (Label) settings.lookup("#launchField");
		leftField = (Label) settings.lookup("#leftField");
		rightField = (Label) settings.lookup("#rightField");
		upField = (Label) settings.lookup("#upField");
		downField = (Label) settings.lookup("#downField");
		radioOff = (RadioButton) settings.lookup("#radioOff");
	}
}
