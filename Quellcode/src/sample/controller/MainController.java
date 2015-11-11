package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private Button b_highscore;

    @FXML
    private Button b_newgame;

    @FXML
    private Button b_settings;

    @FXML
    private Button b_loadgame;

    @FXML
    private Button b_exit;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        b_highscore.setOnAction(event -> Main.setScene("HighscoreScreen"));
        b_newgame.setOnAction(event -> Main.setScene("MainGame"));
        b_settings.setOnAction(event -> Main.setScene("SettingsScreen"));
        b_loadgame.setOnAction(event -> Main.setScene("LoadScreen"));
        b_exit.setOnAction(event -> ((Stage)b_exit.getScene().getWindow()).close());
    }

}
