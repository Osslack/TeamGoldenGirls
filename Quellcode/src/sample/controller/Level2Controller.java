package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by JJ on 14.11.2015.
 */
public class Level2Controller implements Initializable {
    @FXML
    private Pane gamePane2;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        gamePane2.getChildren().addAll(Main.getScene("BaseGame2").getRoot().getChildrenUnmodifiable());
    }
}
