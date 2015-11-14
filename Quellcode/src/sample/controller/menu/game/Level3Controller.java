package sample.controller.menu.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import sample.Main;

/**
 * Created by JJ on 14.11.2015.
 */
public class Level3Controller implements Initializable {
    @FXML
    private Pane gamePane2;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        gamePane2.getChildren().addAll(Main.getScene("BaseGame3").getRoot().getChildrenUnmodifiable());
    }
}
