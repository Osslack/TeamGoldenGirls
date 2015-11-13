package sample;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.controller.SettingsController;
import sample.model.*;
import sample.physics.Physics;
import sample.sounds.Soundmanager;

public class
Main extends Application {
	private static Map<String,Scene> m_ScenesMap = new HashMap<>();
	private static Stage m_PrimaryStage;
	static private Physics m_Physics;
	static private Soundmanager m_Soundmanager;
	private static String OS = null;
	public static String PATH_SEPARATOR = null;

	private static Savegames  savegames;
	public static  Difficulty chosenDifficulty;
	public static  UserData   user;

	@Override
	public void start(Stage primaryStage) throws Exception {
		savegames = Serializer.load();
		m_PrimaryStage = primaryStage;
		loadScenes();
		primaryStage.setScene(getScene("MainMenu"));
		primaryStage.setTitle("Kugellineal PhysX Sim");
		primaryStage.show();

		m_Physics = new Physics(this);
		m_Soundmanager = new Soundmanager();
		setIngameListener();
		setPauseListener();
	}

	public void setIngameListener() {
		Scene scene = getScene("MainGame");
		Pane pauseMenuPane = (Pane) scene.lookup("#pauseMenuPane");

		scene.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				if (pauseMenuPane.isVisible()) {
					hidePauseMenuAndResume(pauseMenuPane);
				}
				else {
					m_Physics.stop();
					pauseMenuPane.setVisible(true);
				}
			}
		});
	}

	public void setPauseListener() {
		Scene scene = getScene("MainGame");
		Pane pauseMenuPane = (Pane) scene.lookup("#pauseMenuPane");
		Button mainMenuButton = (Button) scene.lookup("#mainMenuButton");
		Button settingsButton = (Button) scene.lookup("#settingsButton");
		Button resumeButton = (Button) scene.lookup("#resumeButton");

		mainMenuButton.setOnAction(event -> {
			final FXMLLoader loader = new FXMLLoader(
					getClass().getResource("MainMenu.fxml"));
			final SettingsController controller = loader.getController();
			controller.setCaller("PauseMenu");
			Main.setScene("MainMenu");
		});

		settingsButton.setOnAction(event -> {
			final FXMLLoader loader = new FXMLLoader(
					getClass().getResource("SettingsScreen.fxml"));
			final SettingsController controller = loader.getController();
			controller.setCaller("PauseMenu");
			Main.setScene("SettingsScreen");
		});

		resumeButton.setOnAction(event -> hidePauseMenuAndResume(pauseMenuPane));
	}

	private void hidePauseMenuAndResume(Pane pauseMenuPane) {
		pauseMenuPane.setVisible(false);
		m_Physics.start();
	}

	public static Stage getPrimaryStage() {
		return m_PrimaryStage;
	}

	public Soundmanager getSoundmanager(){return m_Soundmanager;}

	public static void setScene(String name) {
		m_PrimaryStage.setScene(getScene(name));
		if (name.equals("MainGame")) {
			m_Physics.start();
		}
		else {
			m_Physics.stop();
		}
	}

	public static Scene getScene(String name) { //unsauber!!!
		return m_ScenesMap.get(name);
	}

	public List<String> getallFilesOfFolder(final File folder) {
		List<String> files = new LinkedList<>();
		for (final File fileEntry : folder.listFiles()) {
			if (!fileEntry.isDirectory()) {
				files.add(fileEntry.getName());
			}
		}
		return files;
	}

	private void loadScenes() {
		String path = "";
		try {
			path = new File(".").getCanonicalPath();
			System.out.println(path);
		}
		catch (Exception e) {

		}
		List<String> files;
		if(isWindows()) {
			 PATH_SEPARATOR = "\\";
			files = getallFilesOfFolder(new File(path + "\\Quellcode\\src\\sample\\view"));
		} else {
			 PATH_SEPARATOR = "/";
			files = getallFilesOfFolder(new File(path + "/src/sample/view"));
		}

		String name;
		for (String filename : files) {
			try {
				name = filename.split("\\.")[0];
				m_ScenesMap.put(name, loadSceneFromFXML(filename));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Scene loadSceneFromFXML(String name) throws IOException {
		return new Scene(FXMLLoader.load(getClass().getResource("view/" + name)));
	}
	public static String getOsName()
	{
		if(OS == null) { OS = System.getProperty("os.name"); }
		return OS;
	}
	public static boolean isWindows()
	{
		return getOsName().startsWith("Windows");
	}

	public static Savegames getSavegames() {
		return savegames;
	}

	public static void setDifficulty(Difficulty diff) {
		chosenDifficulty = diff;
	}

	@Override
	public void stop() {
		savegames.finalizeSavegame();
		Serializer.save(savegames);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
