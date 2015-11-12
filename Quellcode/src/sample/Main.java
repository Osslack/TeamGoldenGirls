package sample;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
	private static Difficulty chosenDifficulty;

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

	}

	public Stage getPrimaryStage() {
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

	public static ObservableList<Savegame> getSavegamesFor(Difficulty diff) {
		switch (diff) {
			case EASY:
				return savegames.easySavegames;
			case MEDIUM:
				return savegames.mediumSavegames;
			case HARD:
				return savegames.hardSavegames;
			case EXTREME:
				return savegames.extremeSavegames;
		}
		return null;
	}

	public static void setDifficulty(Difficulty diff) {
		chosenDifficulty = diff;
	}

	@Override
	public void stop() {
		savegames.finalizeSavegame(chosenDifficulty);
		Serializer.save(savegames);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
