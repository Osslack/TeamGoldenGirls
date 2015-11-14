package sample;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Gamelogic.Gamelogic;
import sample.input.KeyboardManager;
import sample.model.*;
import sample.physics.Physics;
import sample.sounds.Soundmanager;

public class
Main extends Application {
	private static Map<String,Scene> m_ScenesMap = new HashMap<>();
	private static Stage        m_PrimaryStage;
	private static Physics      m_Physics;
	private static Soundmanager m_Soundmanager;
	private static Playingfield m_Playingfield;
	private Animationmanager m_Animationmanager;
	private KeyboardManager m_Keyboardmanager;
	private static Gamelogic m_Gamelogic;
	private static String OS = null;
	public static String PATH_SEPARATOR = null;

	private static Savegames  savegames;
	public static  UserData   user;

	@Override
	public void start(Stage primaryStage) throws Exception {
		savegames = Serializer.load();
		m_PrimaryStage = primaryStage;
		loadScenes();
		primaryStage.setScene(getScene("MainMenu"));
		primaryStage.setTitle("Kugellineal PhysX Sim");
		primaryStage.show();
		m_Playingfield = new Playingfield(this);
		m_Physics = new Physics(this);
		m_Soundmanager = new Soundmanager();
		m_Gamelogic = new Gamelogic(this);
		m_Animationmanager = new Animationmanager(this);
		m_Keyboardmanager = new KeyboardManager(this);
	}

	public static Gamelogic getGamelogic() {
		return m_Gamelogic;
	}

	public static Stage getPrimaryStage() {
		return m_PrimaryStage;
	}

	public Soundmanager getSoundmanager(){return m_Soundmanager;}

	public Playingfield getPlayingfield(){return m_Playingfield;}

	public Animationmanager getAnimationmanager() {return m_Animationmanager;}

	public KeyboardManager getKeyboardmanager() {return m_Keyboardmanager;}

	public static void setScene(String name) {
		Scene scene = getScene(name);
		m_PrimaryStage.setScene(scene);
//		if (name.equals("MainGame") && !scene.lookup("#pauseMenuPane").isVisible()) {
//			m_Physics.start();
//		}
//		else {
//			m_Physics.stop();
//		}
	}

	public Physics getPhysics() {
		return m_Physics;
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

	@Override
	public void stop() {
		savegames.finalizeSavegame();
		Serializer.save(savegames);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Scene fusionSceneToCurrentScene(String scenename) {
		Group allelems = new Group();
		Scene scenetofusion = getScene(scenename);
		for (Node child : scenetofusion.getRoot().getChildrenUnmodifiable()) {
//			if (!(child instanceof javafx.scene.layout.Pane)) {


//			}
		}
		return new Scene(allelems);
	}
}
