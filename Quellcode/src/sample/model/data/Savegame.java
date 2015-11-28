/*used*/
package sample.model.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.*;
import sample.Difficulty;
import sample.model.serialization.SerializableSavegame;

/**
 * @author Nils
 */
public class Savegame {
	private final IntegerProperty            level;
	private final IntegerProperty            score;
	private final StringProperty             username;
	private final StringProperty             form;
	private final StringProperty             date;
	private final ObjectProperty<Difficulty> difficulty;

	public Savegame(SerializableSavegame save) {
		this.level = new SimpleIntegerProperty(save.getLevel());
		this.score = new SimpleIntegerProperty(save.getScore());
		this.username = new SimpleStringProperty(save.getUsername());
		this.form = new SimpleStringProperty(save.getForm());
		this.date = new SimpleStringProperty(save.getDate());
		this.difficulty = new SimpleObjectProperty<>(save.getDifficulty());
	}

	public Savegame(int level, int score, String username, String form, Difficulty diff) {
		this.level = new SimpleIntegerProperty(level);
		this.score = new SimpleIntegerProperty(score);
		this.username = new SimpleStringProperty(username);
		this.form = new SimpleStringProperty(form);
		this.date = getTimestamp();
		this.difficulty = new SimpleObjectProperty<>(diff);
	}

	private SimpleStringProperty getTimestamp() {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		final Calendar cal = Calendar.getInstance();
		final Date now = cal.getTime();
		return new SimpleStringProperty(dateFormat.format(now));
	}

	public IntegerProperty levelProperty() {
		return level;
	}

	public IntegerProperty scoreProperty() {
		return score;
	}

	public StringProperty usernameProperty() {
		return username;
	}

	public StringProperty formProperty() {
		return form;
	}

	public StringProperty dateProperty() {
		return date;
	}

	public ObjectProperty difficultyProperty() {
		return difficulty;
	}


	public int getLevel() {
		return level.get();
	}

	public int getScore() {
		return score.get();
	}

	public String getUsername() {
		return username.get();
	}

	public String getForm() {
		return form.get();
	}

	public String getDate() {
		return date.get();
	}

	public Difficulty getDifficulty() {
		return difficulty.get();
	}
}
