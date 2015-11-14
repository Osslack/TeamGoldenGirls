package sample.controller.menu.highscore;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.Main;

/**
 * @author Nils
 */
public class HighController implements Initializable {

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        cancelButton.setOnAction(event -> Main.setScene("MainMenu"));
    }

}
