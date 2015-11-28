package sample;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.gamelogic.Gamelogic;
import sample.input.KeyboardManager;
import sample.model.Playingfield;
import sample.model.data.Savegames;
import sample.model.serialization.Serializer;
import sample.physics.Physics;
import sample.sound.Soundmanager;

/**
 * JENDRIK
 *
 * @author Nils
 */
public class Main extends Application {
	private static final Map<String, Scene> m_ScenesMap = new HashMap<>();
	private static Stage            m_PrimaryStage;
	private static Physics          m_Physics;
	private static Soundmanager     m_Soundmanager;
	private static Playingfield     m_Playingfield;
	private        Animationmanager m_Animationmanager;
	private static KeyboardManager  m_Keyboardmanager;
	private static Gamelogic        m_Gamelogic;
	private static String OS             = null;
	private static String PATH_SEPARATOR = null;
	private static int    maxLevel       = 0;

	private static Savegames savegames;

	@Override
	public void start(Stage primaryStage) throws Exception {
		m_Soundmanager = new Soundmanager();
		savegames = Serializer.load();
		m_PrimaryStage = primaryStage;
		loadScenes();
		primaryStage.setScene(getScene("MainMenu"));
		primaryStage.setTitle("Kugellineal PhysX Sim");
		primaryStage.setResizable(false);
		primaryStage.show();
		m_Gamelogic = new Gamelogic(this);
		m_Playingfield = new Playingfield();
		m_Physics = new Physics(this);
		m_Animationmanager = new Animationmanager(this);
		m_Keyboardmanager = new KeyboardManager();
	}

	private void loadScenes() {
		String path = "";
		try {
			path = new File(".").getCanonicalPath();
			System.out.println(path);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		List<String> files = getFilesOsDependent(path);
		fillScenesMap(files);
	}

	private List<String> getFilesOsDependent(final String path) {
		if (isWindows()) {
			PATH_SEPARATOR = "\\";
			return getAllFilesOfFolder(new File(path + "\\Quellcode\\src\\sample\\view"));
		}
		else {
			PATH_SEPARATOR = "/";
			return getAllFilesOfFolder(new File(path + "/src/sample/view"));
		}
	}

	public static boolean isWindows() {
		return getOsName().startsWith("Windows");
	}

	private static String getOsName() {
		if (OS == null) {
			OS = System.getProperty("os.name");
		}
		return OS;
	}

	private List<String> getAllFilesOfFolder(final File folder) {
		List<String> files = new LinkedList<>();
		for (final File fileEntry : folder.listFiles()) {
			if (!fileEntry.isDirectory()) {
				files.add(fileEntry.getName());
			}
		}
		return files;
	}

	private void fillScenesMap(List<String> files) {
		String name;
		for (String filename : files) {
			try {
				name = filename.split("\\.")[0];
				if (name.startsWith("Level") && (!name.equals("Level1")) && isWindows()) {
					m_ScenesMap.put("BaseGame" + name.substring(name.lastIndexOf('l') + 1), loadSceneFromFXML("BaseGame.fxml"));
					++maxLevel;
				}
				else if (name.startsWith("Level") && !isWindows()) {
					m_ScenesMap.put("BaseGame" + name.substring(name.lastIndexOf('l') + 1), loadSceneFromFXML("BaseGame.fxml"));
					++maxLevel;
				}
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

	public static boolean setScene(String name) {
		Scene scene = getScene(name);
		if (scene == null) {
			return false;
		}
		m_PrimaryStage.setScene(scene);
		return true;
	}

	public static Scene getScene(String name) { //unsauber!!!
		return m_ScenesMap.get(name);
	}

	public static void setScene(Scene scene) {
		m_PrimaryStage.setScene(scene);
	}

	public static void mapScene(String name, Scene scene) {
		m_ScenesMap.put(name, scene);
	}

	public static void killScene(String name) {
		m_ScenesMap.remove(name);
	}

	public static Gamelogic getGamelogic() {
		return m_Gamelogic;
	}

	public static Stage getPrimaryStage() {
		return m_PrimaryStage;
	}

	public static Soundmanager getSoundmanager() {
		return m_Soundmanager;
	}

	public Playingfield getPlayingfield() {
		return m_Playingfield;
	}

	public Animationmanager getAnimationmanager() {
		return m_Animationmanager;
	}

	public static KeyboardManager getKeyboardmanager() {
		return m_Keyboardmanager;
	}

	public static Physics getPhysics() {
		return m_Physics;
	}

	public static Savegames getSavegames() {
		return savegames;
	}

	@Override
	public void stop() {
		Serializer.save(savegames);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
