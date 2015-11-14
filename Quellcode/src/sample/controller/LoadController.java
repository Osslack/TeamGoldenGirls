package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Difficulty;
import sample.Main;
import sample.model.Savegame;
import sample.model.UserData;

/**
 * @author Nils
 *         JENDRIK
 */
public class LoadController implements Initializable {

	public  Button              addBtn;
	@FXML
	private TableView<Savegame> table;

	@FXML
	private TableColumn<Savegame, Integer> levelCol;

	@FXML
	private TableColumn<Savegame, Integer> scoreCol;

	@FXML
	private TableColumn<Savegame, String> userCol;

	@FXML
	private TableColumn<Savegame, String> classCol;

	@FXML
	private TableColumn<Savegame, String> diffCol;

	@FXML
	private TableColumn<Savegame, String> dateCol;

	@FXML
	private Button loadButton;

	@FXML
	private Button cancelButton;

	//TODO delete addBtn
	int i = 0;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		setTableViewContent();

		addBtn.setOnAction(event -> Main.getSavegames().getSavegames().add(new Savegame(1, i++, "gg", "tinf", Difficulty.EASY.toString())));

		loadButton.setOnAction(event -> {
			Savegame selected = table.getSelectionModel().getSelectedItem();
			if (selected != null) {
				Main.user = new UserData(selected.getUsername(), selected.getForm());
				Main.getGamelogic().setLevel("Level" + selected.getLevel(), Main.getGamelogic().getDifficulty());
			}
		});

		cancelButton.setOnAction(event -> Main.setScene("MainMenu"));
	}

	private void setTableViewContent() {
		levelCol.setCellValueFactory(data -> data.getValue().levelProperty().asObject());
		scoreCol.setCellValueFactory(data -> data.getValue().scoreProperty().asObject());
		userCol.setCellValueFactory(data -> data.getValue().usernameProperty());
		classCol.setCellValueFactory(data -> data.getValue().formProperty());
		diffCol.setCellValueFactory(data -> data.getValue().difficultyProperty());
		dateCol.setCellValueFactory(data -> data.getValue().dateProperty());

		ObservableList<Savegame> saves = Main.getSavegames().getSavegames();
		saves.addListener((ListChangeListener<Savegame>) c -> table.getItems().setAll(saves));
		table.getItems().addAll(saves);
	}
}
