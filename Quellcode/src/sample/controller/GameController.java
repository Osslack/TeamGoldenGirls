package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author Jendrik
 * @author Nils
 */
public class GameController implements Initializable  {

	@FXML
	private Pane gamePane;

	@FXML
    private Rectangle s_rectangle;

    @FXML
    private Circle s_circle;

	@FXML
	private Pane pauseMenuPane;

	@FXML
	private Pane pauseMenu;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

	}
}
