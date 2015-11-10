package sample.model;

import java.io.Serializable;

/**
 * @author Nils
 */
public class Savegame implements Serializable {
	public int level, score;
	public String username, form, date, difficulty;
}
