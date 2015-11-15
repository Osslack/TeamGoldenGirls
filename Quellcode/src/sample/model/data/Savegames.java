package sample.model.data;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Difficulty;
import sample.model.serialization.SerializableSavegame;

/**
 * @author Nils
 */
public class Savegames {

	private ObservableList<Savegame> savegames;
	private Savegame                 loadedSave;
	private Savegame                 tempSave;

	public Savegames() {
		savegames = FXCollections.observableArrayList();
	}

	public Savegames(List<SerializableSavegame> in) {
		savegames = FXCollections.observableArrayList();
		in.forEach(savegame -> savegames.add(new Savegame(savegame)));
	}


	public ObservableList<Savegame> getSavegames() {
		return savegames;
	}

	public ObservableList<Savegame> getEasySavegames() {
		return getSpecificSavegames(Difficulty.EASY);
	}

	private ObservableList<Savegame> getSpecificSavegames(Difficulty diff) {
		ObservableList<Savegame> out = FXCollections.observableArrayList();
		savegames.forEach(savegame -> {
			if (savegame.getDifficulty().equals(diff)) {
				out.add(savegame);
			}
		});
		return out;
	}

	public ObservableList<Savegame> getMediumSavegames() {
		return getSpecificSavegames(Difficulty.MEDIUM);
	}

	public ObservableList<Savegame> getHardSavegames() {
		return getSpecificSavegames(Difficulty.HARD);
	}

	public ObservableList<Savegame> getExtremeSavegames() {
		return getSpecificSavegames(Difficulty.EXTREME);
	}


	/**
	 * is called everytime a level is finished
	 */
	public void cacheSavegame(int level, int score, String username, String form, Difficulty diff) {
		tempSave = new Savegame(level, score, username, form, diff);
	}


	/**
	 * is called when game is exited
	 */
	public void finalizeSavegame() {
		if (tempSave != null) {
			if (loadedSave != null) {
				savegames.remove(loadedSave);
				loadedSave = null;
			}
			savegames.add(tempSave);
		}
	}

	public void setLoadedSave(final Savegame loadedSave) {
		this.loadedSave = loadedSave;
	}
}
