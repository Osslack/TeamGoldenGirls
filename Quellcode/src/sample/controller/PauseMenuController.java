package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.Main;

/**
 * @author Nils
 */
public class PauseMenuController implements Initializable {

	public Button mainMenuButton;
	public Button settingsButton;
	public Button resumeButton;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		mainMenuButton.setOnAction(event -> Main.setScene("MainMenu"));
		settingsButton.setOnAction(event -> Main.setScene("SettingsScreen"));
	}
}
