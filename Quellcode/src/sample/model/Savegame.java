package sample.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.*;

/**
 * @author Nils
 */
public class Savegame {
	private IntegerProperty level, score;
	private StringProperty username, form, date;

	public Savegame(SerializableSavegame save) {
		this.level = new SimpleIntegerProperty(save.getLevel());
		this.score = new SimpleIntegerProperty(save.getScore());
		this.username = new SimpleStringProperty(save.getUsername());
		this.form = new SimpleStringProperty(save.getForm());
		this.date = new SimpleStringProperty(save.getDate());
	}

	public Savegame(int level, int score, String username, String form) {
		this.level = new SimpleIntegerProperty(level);
		this.score = new SimpleIntegerProperty(score);
		this.username = new SimpleStringProperty(username);
		this.form = new SimpleStringProperty(form);
		this.date = getTimestamp();
	}

	private SimpleStringProperty getTimestamp() {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		final Calendar cal = Calendar.getInstance();
		final Date now = cal.getTime();
		return new SimpleStringProperty(dateFormat.format(now));
	}

	public IntegerProperty getLevel() {
		return level;
	}

	public IntegerProperty getScore() {
		return score;
	}

	public StringProperty getUsername() {
		return username;
	}

	public StringProperty getForm() {
		return form;
	}

	public StringProperty getDate() {
		return date;
	}
}
