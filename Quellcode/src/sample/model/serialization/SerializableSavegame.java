package sample.model.serialization;

import java.io.Serializable;
import sample.Difficulty;
import sample.model.data.Savegame;

/**
 * @author Nils
 */
public class SerializableSavegame implements Serializable {
	private final int        level;
	private final int        score;
	private final String     username;
	private final String     form;
	private final String     date;
	private final Difficulty difficulty;

	public SerializableSavegame(Savegame save) {
		this.level = save.getLevel();
		this.score = save.getScore();
		this.username = save.getUsername();
		this.form = save.getForm();
		this.date = save.getDate();
		this.difficulty = save.getDifficulty();
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public String getUsername() {
		return username;
	}

	public String getForm() {
		return form;
	}

	public String getDate() {
		return date;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}
}
