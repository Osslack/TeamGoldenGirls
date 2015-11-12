package sample.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 * Serializable representation of Savegames, since its ObservableLists are not serializable.
 *
 * @author Nils
 */
public class SerializationContainer implements Serializable {
	public List<SerializableSavegame> easySavegames;
	public List<SerializableSavegame> mediumSavegames;
	public List<SerializableSavegame> hardSavegames;
	public List<SerializableSavegame> extremeSavegames;

	public SerializationContainer(ObservableList<Savegame> easy,
								  ObservableList<Savegame> medium,
								  ObservableList<Savegame> hard,
								  ObservableList<Savegame> ex) {
		this.easySavegames = convertSavegames(easy);
		this.mediumSavegames = convertSavegames(medium);
		this.hardSavegames = convertSavegames(hard);
		this.extremeSavegames = convertSavegames(ex);
	}

	private List<SerializableSavegame> convertSavegames(ObservableList<Savegame> in) {
		List<SerializableSavegame> out = new ArrayList<>(in.size());
		in.forEach(savegame -> out.add(new SerializableSavegame(savegame)));
		return out;
	}
}
