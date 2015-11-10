package sample.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Nils
 */
public class Serializer implements Serializable {

	private List<Savegame> savegames;

	Serializer() {
		savegames = load();
	}

	public void addToSavegames(Object obj) {
		Savegame save = new Savegame();
		save.date = getTimestamp();
		//TODO get other data
		savegames.add(save);
	}

	private String getTimestamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		return dateFormat.format(now);
	}

	public List<Savegame> load() {
		List<Savegame> savegames = null;
		try {
			FileInputStream fis = new FileInputStream("/savegames/saves.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			savegames = ((Serializer) ois.readObject()).savegames;
			ois.close();
			fis.close();
		}
		catch (IOException | ClassNotFoundException | ClassCastException ex) {
			ex.printStackTrace();
		}
		return savegames != null ? savegames : new LinkedList<Savegame>();
	}

	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream("/savegames/saves.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			fos.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
