package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * @author Nils
 */
public class PauseMenuController implements Initializable {
	@FXML
	public Button mainMenuButton;
	@FXML
	public Button settingsButton;
	@FXML
	public Button resumeButton;
	@FXML
	public Button tryagainButton;
	@FXML
	public Button nextlevelButton;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

	}
}
