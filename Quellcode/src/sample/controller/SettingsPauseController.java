package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.Main;

/**
 * @author Nils
 */
public class SettingsPauseController implements Initializable {
	public Button cancelButton;
	public Button saveButton;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		saveButton.setOnAction(event -> Main.setScene("MainGame"));
		cancelButton.setOnAction(event -> Main.setScene("MainGame"));
	}
}
