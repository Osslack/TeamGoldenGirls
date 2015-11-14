package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.Difficulty;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by JJ on 14.11.2015.
 */
public class Level1Controller implements Initializable {
    @FXML
    private Pane gamePane2;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        System.out.println(fxmlFileLocation.toString());
        gamePane2.getChildren().addAll(Main.getScene("BaseGame1").getRoot().getChildrenUnmodifiable());
    }
}
