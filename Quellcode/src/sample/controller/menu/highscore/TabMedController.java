/*used*/
package sample.controller.menu.highscore;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.data.Savegame;
import sample.model.data.Savegames;

/**
 * @author Nils
 */
public class TabMedController implements Initializable {

	@FXML
	private TableView<Savegame> table;

	@FXML
	private TableColumn<Savegame, String> userCol;

	@FXML
	private TableColumn<Savegame, String> classCol;

	@FXML
	private TableColumn<Savegame, Integer> scoreCol;

	@FXML
	private TableColumn<Savegame, String> dateCol;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		userCol.setCellValueFactory(data -> data.getValue().usernameProperty());
		classCol.setCellValueFactory(data -> data.getValue().formProperty());
		scoreCol.setCellValueFactory(data -> data.getValue().scoreProperty().asObject());
		dateCol.setCellValueFactory(data -> data.getValue().dateProperty());

		Savegames saves = Main.getSavegames();
		ObservableList<Savegame> allSaves = saves.getSavegames();
		allSaves.addListener((ListChangeListener<Savegame>)
									 c -> table.getItems().setAll(saves.getMediumSavegames()));
		table.getItems().addAll(saves.getMediumSavegames());
	}
}
