package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import sample.Main;


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
        b_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Stage)b_exit.getScene().getWindow()).close();
            }
        });
        b_newgame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.setScene("MainGame"); //unsauber!!!
            }
        });
    }

}
