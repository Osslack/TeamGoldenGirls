package sample;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Gamelogic.Gamelogic;
import sample.input.KeyboardManager;
import sample.model.Playingfield;
import sample.model.data.Savegames;
import sample.model.data.UserData;
import sample.model.serialization.Serializer;
import sample.physics.Physics;
import sample.sound.Soundmanager;

public class
Main extends Application {
	private static Map<String, Scene> m_ScenesMap = new HashMap<>();
	private static Stage            m_PrimaryStage;
	private static Physics          m_Physics;
	private static Soundmanager     m_Soundmanager;
	private static Playingfield     m_Playingfield;
	private        Animationmanager m_Animationmanager;
	private        KeyboardManager  m_Keyboardmanager;
	private static Gamelogic        m_Gamelogic;
	private static String OS             = null;
	public static  String PATH_SEPARATOR = null;

	private static Savegames savegames;
	public static  UserData  user;

	@Override
	public void start(Stage primaryStage) throws Exception {
		savegames = Serializer.load();
		m_PrimaryStage = primaryStage;
		loadScenes();
		primaryStage.setScene(getScene("MainMenu"));
		primaryStage.setTitle("Kugellineal PhysX Sim");
		primaryStage.setResizable(false);
		primaryStage.show();
		m_Gamelogic = new Gamelogic(this);
		m_Playingfield = new Playingfield(this);
		m_Physics = new Physics(this);
		m_Soundmanager = new Soundmanager();
		m_Animationmanager = new Animationmanager(this);
		m_Keyboardmanager = new KeyboardManager(this);
	}

	public static Gamelogic getGamelogic() {
		return m_Gamelogic;
	}

	public static Stage getPrimaryStage() {
		return m_PrimaryStage;
	}

	public Soundmanager getSoundmanager() {
		return m_Soundmanager;
	}

	public Playingfield getPlayingfield() {
		return m_Playingfield;
	}

	public Animationmanager getAnimationmanager() {
		return m_Animationmanager;
	}

	public KeyboardManager getKeyboardmanager() {
		return m_Keyboardmanager;
	}

	public static boolean setScene(String name) {
		Scene scene = getScene(name);
		if (scene == null) {
			return false;
		}
		m_PrimaryStage.setScene(scene);
		return true;
	}

	public static void mapScene(String name, Scene scene) {
		m_ScenesMap.put(name, scene);
	}

	public static void killScene(String name) {
		m_ScenesMap.remove(name);
	}

	public static void setScene(Scene scene) {
		m_PrimaryStage.setScene(scene);
	}

	public static Physics getPhysics() {
		return m_Physics;
	}

	public static Scene getScene(String name) { //unsauber!!!
		return m_ScenesMap.get(name);
	}

	public List<String> getAllFilesOfFolder(final File folder) {
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
		if (isWindows()) {
			PATH_SEPARATOR = "\\";
			files = getAllFilesOfFolder(new File(path + "\\Quellcode\\src\\sample\\view"));
		}
		else {
			PATH_SEPARATOR = "/";
			files = getAllFilesOfFolder(new File(path + "/src/sample/view"));
		}

		String name;
		for (String filename : files) {
			try {
				name = filename.split("\\.")[0];
				if (name.startsWith("Level") && (!name.equals("Level1")) && isWindows()) {
					m_ScenesMap.put("BaseGame" + name.substring(name.lastIndexOf('l') + 1), loadSceneFromFXML("BaseGame.fxml"));
				}
				else if (name.startsWith("Level") && !isWindows()) {
					m_ScenesMap.put("BaseGame" + name.substring(name.lastIndexOf('l') + 1), loadSceneFromFXML("BaseGame.fxml"));
				}
				m_ScenesMap.put(name, loadSceneFromFXML(filename));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Scene loadSceneFromFXML(String name) throws IOException {
		return new Scene(FXMLLoader.load(getClass().getResource("view/" + name)));
	}

	public static String getOsName() {
		if (OS == null) {
			OS = System.getProperty("os.name");
		}
		return OS;
	}

	public static boolean isWindows() {
		return getOsName().startsWith("Windows");
	}

	public static Savegames getSavegames() {
		return savegames;
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
