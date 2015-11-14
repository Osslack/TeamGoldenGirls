package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.Difficulty;
import sample.Main;

/**
 * @author Nils
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

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        cancelButton.setOnAction(event -> Main.setScene("UserScreen"));

        easyButton.setOnAction(event -> {
            Main.getGamelogic().setLevel("MainGame",Difficulty.EASY);
        });
        medButton.setOnAction(event -> {
            Main.getGamelogic().setLevel("Level1",Difficulty.MEDIUM);
        });
        hardButton.setOnAction(event -> {
            Main.getGamelogic().setLevel("Level1",Difficulty.HARD);
        });
        exButton.setOnAction(event -> {
            Main.getGamelogic().setLevel("Level1",Difficulty.EXTREME);
        });
    }

}
