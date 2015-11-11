package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Nils
 */
public class DiffController implements Initializable {

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        cancelButton.setOnAction(event -> Main.setScene("UserScreen"));
    }

}
