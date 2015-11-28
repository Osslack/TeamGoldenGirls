/*used*/
package sample.controller.menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Main;
import sample.model.data.UserData;

/**
 * @author Nils
 */
public class UserController implements Initializable {

	@FXML
	private TextField usernameTextfield;

	@FXML
	private TextField classTextfield;

	@FXML
	private Button nextButton;

	@FXML
	private Button cancelButton;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		nextButton.setOnAction(event -> {
			String username = usernameTextfield.getText();
			String form = classTextfield.getText();
			if (!username.equals("") && !form.equals("")) {
				UserData user = new UserData(username, form);
				Main.getGamelogic().setUser(user);
				Main.setScene("DifficultyScreen");
				usernameTextfield.setText("");
				classTextfield.setText("");
			}
		});

		cancelButton.setOnAction(event -> Main.setScene("MainMenu"));
	}

}
