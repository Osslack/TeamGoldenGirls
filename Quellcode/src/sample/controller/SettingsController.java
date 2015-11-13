package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.Main;

/**
 * @author Nils
 */
public class SettingsController implements Initializable {

	@FXML
	private Button saveButton;

	@FXML
	private Button cancelButton;

	private String caller; //TODO set caller to MainMenu or PauseMenu


	public void setCaller(String caller) {
		this.caller = caller;
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		saveButton.setOnAction(event -> {
			if (caller.equals("MainMenu")) {
				Main.setScene("MainMenu");
			}
			else {
				Main.setScene("MainGame");
			}
		});

		cancelButton.setOnAction(event -> {
			if (caller.equals("MainMenu")) {
				Main.setScene("MainMenu");
			}
			else {
				Main.setScene("MainGame");
			}
		});
	}
}
