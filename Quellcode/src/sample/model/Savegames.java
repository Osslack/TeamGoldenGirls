package sample.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Nils
 */
public class Savegames implements Serializable {

	private           List<Savegame> savegames;
	private transient Savegame       tempSave;

	public Savegames() {
		savegames = new LinkedList<Savegame>();
	}

	/**
	 * is called everytime a level is finished
	 */
	public void cacheSavegame(Object obj) {
		if (tempSave == null) {
			tempSave = new Savegame();
		}
		tempSave.date = getTimestamp();
		//TODO get other data
	}

	/**
	 * is called when game is exited
	 */
	public void finalizeSavegame() {
		if (tempSave != null) {
			savegames.add(tempSave);
		}
	}

	private String getTimestamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		return dateFormat.format(now);
	}
}
