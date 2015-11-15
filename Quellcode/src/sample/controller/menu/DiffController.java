package sample.controller.menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.Difficulty;
import sample.Main;

/**
 * @author Nils
 * JENDRIK
 */
public class DiffController implements Initializable {

	@FXML
	private Button cancelButton;

	@FXML
	private Button easyButton;

	@FXML
	private Button medButton;

	@FXML
	private Button hardButton;

	@FXML
	private Button exButton;

	private Main main;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		cancelButton.setOnAction(event -> Main.setScene("UserScreen"));

		easyButton.setOnAction(event -> {
			prepGame();
			Main.getGamelogic().setLevel("Level1", Difficulty.EASY);
		});
		medButton.setOnAction(event -> {
			prepGame();
			Main.getGamelogic().setLevel("Level1", Difficulty.MEDIUM);
		});
		hardButton.setOnAction(event -> {
			prepGame();
			Main.getGamelogic().setLevel("Level1", Difficulty.HARD);
		});
		exButton.setOnAction(event -> {
			prepGame();
			Main.getGamelogic().setLevel("Level1", Difficulty.EXTREME);
		});
	}

	private void prepGame() {
		Main.getGamelogic().reset();
		Main.getKeyboardmanager().closePauseMenu();
	}
}
