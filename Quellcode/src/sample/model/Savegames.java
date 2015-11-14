package sample.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Difficulty;

/**
 * @author Nils
 */
public class Savegames {

	private ObservableList<Savegame> savegames;
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
		ObservableList<Savegame> out = FXCollections.observableArrayList();
		savegames.forEach(savegame -> {
			if (savegame.getDifficulty().equals(diff)) {
				out.add(savegame);
			}
		});
		return out;
	}

	public ObservableList<Savegame> getHardSavegames() {
		String diff = Difficulty.HARD.toString();
		ObservableList<Savegame> out = FXCollections.observableArrayList();
		savegames.forEach(savegame -> {
			if (savegame.getDifficulty().equals(diff)) {
				out.add(savegame);
			}
		});
		return out;
	}

	public ObservableList<Savegame> getExtremeSavegames() {
		String diff = Difficulty.EXTREME.toString();
		ObservableList<Savegame> out = FXCollections.observableArrayList();
		savegames.forEach(savegame -> {
			if (savegame.getDifficulty().equals(diff)) {
				out.add(savegame);
			}
		});
		return out;
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
			savegames.add(tempSave);
		}
	}
}
