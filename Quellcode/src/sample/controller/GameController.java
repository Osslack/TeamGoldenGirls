package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 * Created by JJ on 09.11.2015.
 */
public class GameController implements Initializable  {
    @FXML
    private Rectangle s_rectangle;

    @FXML
    private Circle s_circle;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

    }

}
