package sample.model;

import java.io.*;

/**
 * @author Nils
 */
public class Serializer {

	private static final File path = new File(System.getProperty("user.dir") + "/savegames");
	private static final File file = new File(path, "/saves.ser");

	/**
	 * is called everytime the application is started
	 */
	public static Savegames load() {
		if (file.exists()) {
			try (FileInputStream fis = new FileInputStream(file);
				 ObjectInputStream ois = new ObjectInputStream(fis)) {
				return (Savegames) ois.readObject();
			}
			catch (IOException | ClassNotFoundException | ClassCastException ex) {
				ex.printStackTrace();
			}
		}
		return new Savegames();
	}

	/**
	 * is called everytime the application is exited
	 */
	public static void save(Savegames saves) {
		createFileIfNecessary();
		try (FileOutputStream fos = new FileOutputStream(file);
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(saves);
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
