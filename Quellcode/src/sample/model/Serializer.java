package sample.model;

import java.io.*;

/**
 * @author Nils
 */
public class Serializer {

	private static final File path = new File(System.getProperty("user.dir") + "/savegames");
	private static final File file = new File(path, "/saves.gg");

	/**
	 * is called everytime the application is started
	 */
	public static Savegames load() {
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
		createFileIfNecessary();
		SerializableSavegames output = new SerializableSavegames(saves.savegames);
		try (FileOutputStream fos = new FileOutputStream(file);
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(output);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void createFileIfNecessary() {
		if (!file.exists()) {
			path.mkdirs();
			try {
				file.createNewFile();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
