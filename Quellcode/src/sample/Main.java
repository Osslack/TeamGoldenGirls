package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Main extends Application {

	final int xScreenSize = 800;
	final int yScreenSize = 280;

	Scene s_mainMenu, s_newGame, s_loadGame, s_settings, s_highscore, s_game, s_difficulty;
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Kugellineal");

		//primaryStage.setMaximized(true);

		//--Main Menu--
		//Text in Window
		Label label1 = new Label("Main Menu");
		//create buttons and give them names
		Button b_newGame, b_loadGame, b_settings, b_highscore, b_exit;
		b_newGame = new Button("New Game");
		b_loadGame = new Button("Load Game");
		b_settings = new Button("Settings");
		b_highscore = new Button("Highscore");
		b_exit = new Button("Exit");
		//actionhandlers
		b_newGame.setOnAction(e -> primaryStage.setScene(s_newGame));
		b_loadGame.setOnAction(e -> primaryStage.setScene(s_loadGame));
		b_settings.setOnAction(e -> primaryStage.setScene(s_settings));
		b_highscore.setOnAction(e -> primaryStage.setScene(s_highscore));
		b_exit.setOnAction(e -> primaryStage.close());
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(label1, b_newGame, b_loadGame, b_settings, b_highscore, b_exit);
		layout1.setAlignment(Pos.TOP_CENTER);
		s_mainMenu = new Scene(layout1, xScreenSize, yScreenSize);
		//ImageIcon imgIco = new ImageIcon(getClass().getResource("images/Kugel.png"));

		//--Game--
		//Text in Window
		Group groot = new Group();
		Path level1 = new Path();
		javafx.scene.shape.Rectangle goal = new javafx.scene.shape.Rectangle(20,20,40,60);
		goal.setFill(Color.BLACK);
		Ellipse ball = new Ellipse();
		ball.setCenterX(10);
		ball.setCenterY(40);
		ball.setRadiusX(5);
		ball.setRadiusY(5);
		ball.setFill(Color.BLACK);
		groot.getChildren().addAll(ball,goal);
		s_game = new Scene(groot, xScreenSize, yScreenSize);

		//--New Game--
		//Text in Window
		Label label2 = new Label("New Game");
		//create buttons and give them names
		Button b_cancel, b_continue;
		b_cancel = new Button("Cancel");
		b_continue = new Button("Continue");
		//actionhandlers
		b_cancel.setOnAction(e -> primaryStage.setScene(s_mainMenu));
		b_continue.setOnAction(e -> primaryStage.setScene(s_difficulty));
		VBox layout2 = new VBox(20);
		layout2.getChildren().addAll(label2, b_cancel, b_continue);
		s_newGame = new Scene(layout2, xScreenSize, yScreenSize);


		//--Choose a Difficulty--
		//Text in Window
		Label label7 = new Label("Choose a Difficulty");
		//create buttons and give them names
		Button b_cancel7, b_continue7;
		b_cancel7 = new Button("Cancel");
		b_continue7 = new Button("Continue");
		//actionhandlers
		b_cancel7.setOnAction(e -> primaryStage.setScene(s_newGame));
		b_continue7.setOnAction(e -> primaryStage.setScene(s_game));
		VBox layout7 = new VBox(20);
		layout7.getChildren().addAll(label7, b_cancel7, b_continue7);
		s_difficulty = new Scene(layout7, xScreenSize, yScreenSize);





		//--Load Game--
		//Text in Window
		Label label4 = new Label("Load Game");
		//create buttons and give them names
		Button b_cancel3, b_continue3;
		b_cancel3 = new Button("Cancel");
		b_continue3 = new Button("Continue");
		//actionhandlers
		b_cancel3.setOnAction(e -> primaryStage.setScene(s_mainMenu));
		b_continue3.setOnAction(e -> primaryStage.setScene(s_game));
		VBox layout3 = new VBox(20);
		layout3.getChildren().addAll(label4, b_cancel3, b_continue3);
		s_loadGame = new Scene(layout3, xScreenSize, yScreenSize);


		//--Settings--
		Label label5 = new Label("Settings");
		//create buttons and give them names
		Button b_continue5;
		b_continue5 = new Button("Continue");
		//actionhandlers
		b_continue5.setOnAction(e -> primaryStage.setScene(s_mainMenu));
		VBox layout5 = new VBox(20);
		layout5.getChildren().addAll(label5, b_continue5);
		s_settings = new Scene(layout5, xScreenSize, yScreenSize);


		//--Highscore--
		//Text in Window
		Label label3 = new Label("Highscore");
		//create buttons and give them names
		Button b_cancel2;
		b_cancel2 = new Button("Cancel");
		//actionhandlers
		b_cancel2.setOnAction(e -> primaryStage.setScene(s_mainMenu));
		VBox layout4 = new VBox(20);
		layout4.getChildren().addAll(label3, b_cancel2);
		s_highscore = new Scene(layout4, xScreenSize, yScreenSize);




		primaryStage.setScene(s_mainMenu);
		primaryStage.show();
	}


	public static void main(String[] args) {
		int x = 10;
		launch(args);
		while(true){
			x+=1;
		}
	}
}
