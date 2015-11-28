package sample.model.serialization;

import java.io.*;
import sample.model.data.Preferences;
import sample.model.data.Savegames;

/**
 * @author Nils
 */
public class Serializer {

	private static final File path  = new File(System.getProperty("user.dir") + "/savegames");
	private static final File file  = new File(path, "/saves.gg");
	private static final File prefs = new File(path, "/prefs.gg");

	/**
	 * is called everytime the application is started
	 */
	public static Savegames load() {
		if (prefs.exists()) {
			try (FileInputStream fis = new FileInputStream(prefs);
				 ObjectInputStream ois = new ObjectInputStream(fis)) {
				SerializablePreferences in = (SerializablePreferences) ois.readObject();
				Preferences.setLaunchKey(in.launchKey);
				Preferences.setMoveLeftKey(in.moveLeftKey);
				Preferences.setMoveRightKey(in.moveRightKey);
				Preferences.setMoveUpKey(in.moveUpKey);
				Preferences.setMoveDownKey(in.moveDownKey);
				Preferences.setStartKey(in.startKey);
				Preferences.setRetryKey(in.retryKey);
				Preferences.setMuted(in.muted);
			}
			catch (IOException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			catch (ClassCastException ex) {
				prefs.delete();
				ex.printStackTrace();
			}
		}

		if (file.exists()) {
			try (FileInputStream fis = new FileInputStream(file);
				 ObjectInputStream ois = new ObjectInputStream(fis)) {
				SerializableSavegames box = (SerializableSavegames) ois.readObject();
				return wrapObservables(box);
			}
			catch (IOException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			catch (ClassCastException ex) {
				file.delete();
				ex.printStackTrace();
			}
		}
		return new Savegames();
	}

	private static Savegames wrapObservables(SerializableSavegames box) {
		return new Savegames(box.savegames);
	}

	/**
	 * is called everytime the application is exited
	 */
	public static void save(Savegames saves) {
		createFilesIfNecessary();
		saveSettings();
		SerializableSavegames output = new SerializableSavegames(saves.getSavegames());
		try (FileOutputStream fos = new FileOutputStream(file);
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(output);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void createFilesIfNecessary() {
		if (!file.exists() || !prefs.exists()) {
			path.mkdirs();
			try {
				file.createNewFile();
				prefs.createNewFile();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static void saveSettings() {
		SerializablePreferences out = new SerializablePreferences(Preferences.getLaunchKey(), Preferences.getMoveLeftKey(),
																  Preferences.getMoveRightKey(), Preferences.getMoveUpKey(),
																  Preferences.getMoveDownKey(), Preferences.getStartKey(),
																  Preferences.getRetryKey(), Preferences.isMuted());
		try (FileOutputStream fos = new FileOutputStream(prefs);
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(out);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
