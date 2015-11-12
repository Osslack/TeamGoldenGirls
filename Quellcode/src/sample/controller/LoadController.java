package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Main;
import sample.model.Savegame;

/**
 * @author Nils
 */
public class LoadController implements Initializable {

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

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		levelCol.setCellValueFactory(data -> data.getValue().getLevel().asObject());
		scoreCol.setCellValueFactory(data -> data.getValue().getScore().asObject());
		userCol.setCellValueFactory(data -> data.getValue().getUsername());
		classCol.setCellValueFactory(data -> data.getValue().getForm());
		diffCol.setCellValueFactory(data -> data.getValue().getDifficulty());
		dateCol.setCellValueFactory(data -> data.getValue().getDate());

		ObservableList<Savegame> saves = Main.getSavegames().getSavegames();
		table.getItems().setAll(saves);

        //loadButton.setOnAction(event -> );

        cancelButton.setOnAction(event -> Main.setScene("MainMenu"));
    }
}
