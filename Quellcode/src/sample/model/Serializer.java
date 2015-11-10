package sample.model;

import java.io.*;

/**
 * @author Nils
 */
public class Serializer {

	/**
	 * is called everytime the application is started
	 */
	public static Savegames load() {
		Savegames saves = null;
		try {
			FileInputStream fis = new FileInputStream("/savegames/saves.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			saves = (Savegames) ois.readObject();
			ois.close();
			fis.close();
		}
		catch (IOException | ClassNotFoundException | ClassCastException ex) {
			ex.printStackTrace();
		}
		return saves != null ? saves : new Savegames();
	}

	/**
	 * is called everytime the application is exited
	 */
	public static void save(Savegames saves) {
		try {
			FileOutputStream fos = new FileOutputStream("/savegames/saves.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(saves);
			oos.close();
			fos.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
