package sample;

import javafx.animation.AnimationTimer;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.model.Vector2D;
import sample.physics.Physics;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.io.IOException;
import java.util.Map;


public class Main extends Application {
	private static Map<String,Scene> m_ScenesMap = new HashMap<>(); //unsauber!!!
	private static Stage m_PrimaryStage;
	static private Physics m_Physics;

	@Override
	public void start(Stage primaryStage) throws Exception {
		m_PrimaryStage = primaryStage;
		loadScenes();
		primaryStage.setScene(getScene("MainMenu"));
		primaryStage.setTitle("Kugellineal PhysX Sim");
		primaryStage.show();

		m_Physics = new Physics(this);

	}

	public Stage getPrimaryStage(){
		return m_PrimaryStage;
	}

	public static void setScene(String name){ //unsauber!!!
		m_PrimaryStage.setScene(getScene(name));
		if(name == "MainGame"){
			m_Physics.start();
		}else{
			m_Physics.stop();
		}
	}

	public static Scene getScene(String name){ //unsauber!!!
		return m_ScenesMap.get(name);
	}

	private void loadScenes(){
		String path = "";
		try {
			path = new File(".").getCanonicalPath();
			System.out.println(path);
		}catch (Exception e){

		}
		List<String> files = getallFilesofFolder(new File(path+"\\Quellcode\\src\\sample\\view"));
		String name;
		for (String filename : files) {
			try {
				name = filename.split("\\.")[0];
				m_ScenesMap.put(name,loadSceneFromFXML(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<String> getallFilesofFolder(final File folder) {
		List<String> files = new LinkedList<>();
		for (final File fileEntry : folder.listFiles()) {
			if (!fileEntry.isDirectory()) {
				files.add(fileEntry.getName());
			}
		}
		return files;
	}

	private Scene loadSceneFromFXML(String name) throws IOException {
		return new Scene(FXMLLoader.load(getClass().getResource("view/"+name)));
	}

//	private void setupScenes(Stage primaryStage){
//		Label label1 = new Label("Main Menu");
//		Button b_newGame, b_loadGame, b_settings, b_highscore, b_exit;
//		b_newGame = new Button("New Game");
//		b_loadGame = new Button("Load Game");
//		b_settings = new Button("Settings");
//		b_highscore = new Button("Highscore");
//		b_exit = new Button("Exit");
//		b_newGame.setOnAction(e -> primaryStage.setScene(s_newGame));
//		b_loadGame.setOnAction(e -> primaryStage.setScene(s_loadGame));
//		b_settings.setOnAction(e -> primaryStage.setScene(s_settings));
//		b_highscore.setOnAction(e -> primaryStage.setScene(s_highscore));
//		b_exit.setOnAction(e -> primaryStage.close());
//		VBox layout1 = new VBox(20);
//		layout1.getChildren().addAll(label1, b_newGame, b_loadGame, b_settings, b_highscore, b_exit);
//		layout1.setAlignment(Pos.TOP_CENTER);
//		s_mainMenu = new Scene(layout1, xScreenSize, yScreenSize);
//
//
//
//		Group groot = new Group();
//		Path level1 = new Path();
//		javafx.scene.shape.Rectangle goal = new javafx.scene.shape.Rectangle(20,20,40,60);
//		goal.setFill(Color.BLACK);
//		Ellipse ball = new Ellipse();
//		ball.setCenterX(10);
//		ball.setCenterY(40);
//		ball.setRadiusX(5);
//		ball.setRadiusY(5);
//		ball.setFill(Color.BLACK);
//		groot.getChildren().addAll(ball,goal);
//		s_game = new Scene(groot, xScreenSize, yScreenSize);
//
//
//
//
//		Label label2 = new Label("New Game");
//		Button b_cancel, b_continue;
//		b_cancel = new Button("Cancel");
//		b_continue = new Button("Continue");
//		b_cancel.setOnAction(e -> primaryStage.setScene(s_mainMenu));
//		b_continue.setOnAction(e -> primaryStage.setScene(s_difficulty));
//		VBox layout2 = new VBox(20);
//		layout2.getChildren().addAll(label2, b_cancel, b_continue);
//		s_newGame = new Scene(layout2, xScreenSize, yScreenSize);
//
//
//
//
//		Label label7 = new Label("Choose a Difficulty");
//		Button b_cancel7, b_continue7;
//		b_cancel7 = new Button("Cancel");
//		b_continue7 = new Button("Continue");
//		b_cancel7.setOnAction(e -> primaryStage.setScene(s_newGame));
//		b_continue7.setOnAction(e -> primaryStage.setScene(s_game));
//		VBox layout7 = new VBox(20);
//		layout7.getChildren().addAll(label7, b_cancel7, b_continue7);
//		s_difficulty = new Scene(layout7, xScreenSize, yScreenSize);
//
//
//		//Load Game
//		Label label4 = new Label("Load Game");
//		Button b_cancel3, b_continue3;
//		b_cancel3 = new Button("Cancel");
//		b_continue3 = new Button("Continue");
//		b_cancel3.setOnAction(e -> primaryStage.setScene(s_mainMenu));
//		b_continue3.setOnAction(e -> primaryStage.setScene(s_game));
//		VBox layout3 = new VBox(20);
//		layout3.getChildren().addAll(label4, b_cancel3, b_continue3);
//		s_loadGame = new Scene(layout3, xScreenSize, yScreenSize);
//
//
//		//Settings
//		Label label5 = new Label("Settings");
//		//create buttons and give them names
//		Button b_continue5;
//		b_continue5 = new Button("Continue");
//		//actionhandlers
//		b_continue5.setOnAction(e -> primaryStage.setScene(s_mainMenu));
//		VBox layout5 = new VBox(20);
//		layout5.getChildren().addAll(label5, b_continue5);
//		s_settings = new Scene(layout5, xScreenSize, yScreenSize);
//
//
//		//Highscore
//		Label label3 = new Label("Highscore");
//		Button b_cancel2;
//		b_cancel2 = new Button("Cancel");
//		b_cancel2.setOnAction(e -> primaryStage.setScene(s_mainMenu));
//		VBox layout4 = new VBox(20);
//		layout4.getChildren().addAll(label3, b_cancel2);
//		s_highscore = new Scene(layout4, xScreenSize, yScreenSize);
//	}


	public static void main(String[] args) {
		launch(args);
	}
}
