package sample.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Difficulty;

/**
 * @author Nils
 */
public class Savegames {

	public  ObservableList<Savegame> easySavegames;
	public  ObservableList<Savegame> mediumSavegames;
	public  ObservableList<Savegame> hardSavegames;
	public  ObservableList<Savegame> extremeSavegames;
	private Savegame                 tempSave;

	public Savegames() {
		easySavegames = FXCollections.observableArrayList();
		mediumSavegames = FXCollections.observableArrayList();
		hardSavegames = FXCollections.observableArrayList();
		extremeSavegames = FXCollections.observableArrayList();
	}

	public Savegames(List<SerializableSavegame> easy,
					 List<SerializableSavegame> medium,
					 List<SerializableSavegame> hard,
					 List<SerializableSavegame> ex) {
		easySavegames = convertSavegames(easy);
		mediumSavegames = convertSavegames(medium);
		hardSavegames = convertSavegames(hard);
		extremeSavegames = convertSavegames(ex);
	}

	private ObservableList<Savegame> convertSavegames(List<SerializableSavegame> in) {
		ObservableList<Savegame> out = FXCollections.observableArrayList();
		in.forEach(savegame -> out.add(new Savegame(savegame)));
		return out;
	}


	/**
	 * is called everytime a level is finished
	 */
	public void cacheSavegame(int level, int score, String username, String form) {
		tempSave = new Savegame(level, score, username, form);
	}


	/**
	 * is called when game is exited
	 */
	public void finalizeSavegame(Difficulty difficulty) {
		if (tempSave != null) {
			switch (difficulty) {
				case EASY:
					easySavegames.add(tempSave);
					break;
				case MEDIUM:
					mediumSavegames.add(tempSave);
					break;
				case HARD:
					hardSavegames.add(tempSave);
					break;
				case EXTREME:
					extremeSavegames.add(tempSave);
					break;
			}
		}
	}
}
