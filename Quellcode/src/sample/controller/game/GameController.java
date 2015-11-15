package sample.controller.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author Nils
 * @author Jendrik
 */
public class GameController implements Initializable  {

	public Label scoreField;
	public Label timeField;
	public Label levelField;

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

	@FXML
	private Pane endScreenPane;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
	}
}
