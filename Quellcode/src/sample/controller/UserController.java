package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Main;
import sample.model.UserData;

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
			String user = usernameTextfield.getText();
			String form = classTextfield.getText();
			if (!user.equals("") && !form.equals("")) {
				Main.user = new UserData(user, form);
				Main.setScene("DifficultyScreen");
			}
		});

        cancelButton.setOnAction(event -> Main.setScene("MainMenu"));
    }

}
