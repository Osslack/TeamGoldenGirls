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
public class Level1Controller implements Initializable {
    @FXML
    private Pane gamePane2;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
<<<<<<< HEAD:Quellcode/src/sample/controller/Level1Controller.java
=======
        System.out.println(fxmlFileLocation.toString());
>>>>>>> 8fd7427a4d7e23a5f6f38ffdc5c8e3f290fe7460:Quellcode/src/sample/controller/menu/game/Level1Controller.java
        gamePane2.getChildren().addAll(Main.getScene("BaseGame1").getRoot().getChildrenUnmodifiable());
    }
}
