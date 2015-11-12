package sample.model;

import java.io.Serializable;

/**
 * @author Nils
 */
public class SerializableSavegame implements Serializable {
	private int level, score;
	private String username, form, date;

	public SerializableSavegame(Savegame save) {
		this.level = save.getLevel().intValue();
		this.score = save.getScore().intValue();
		this.username = save.getUsername().getValue();
		this.form = save.getForm().getValue();
		this.date = save.getDate().getValue();
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
}
