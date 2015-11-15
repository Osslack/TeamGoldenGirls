package sample.model.serialization;

import java.io.Serializable;
import sample.model.data.Savegame;

/**
 * @author Nils
 */
public class SerializableSavegame implements Serializable {
	private int level, score;
	private String username, form, date, difficulty;

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

	public String getDifficulty() {
		return difficulty;
	}
}
