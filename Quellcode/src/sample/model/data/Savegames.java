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
		String diff = Difficulty.EASY.toString();
		return getSpecificSavegames(diff);
	}

	private ObservableList<Savegame> getSpecificSavegames(String diff) {
		ObservableList<Savegame> out = FXCollections.observableArrayList();
		savegames.forEach(savegame -> {
			if (savegame.getDifficulty().equals(diff)) {
				out.add(savegame);
			}
		});
		return out;
	}

	public ObservableList<Savegame> getMediumSavegames() {
		String diff = Difficulty.MEDIUM.toString();
		return getSpecificSavegames(diff);
	}

	public ObservableList<Savegame> getHardSavegames() {
		String diff = Difficulty.HARD.toString();
		return getSpecificSavegames(diff);
	}

	public ObservableList<Savegame> getExtremeSavegames() {
		String diff = Difficulty.EXTREME.toString();
		return getSpecificSavegames(diff);
	}


	/**
	 * is called everytime a level is finished
	 */
	public void cacheSavegame(int level, int score, String username, String form, String diff) {
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
