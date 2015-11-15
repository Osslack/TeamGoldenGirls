package sample.model.serialization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import sample.model.data.Savegame;

/**
 * Serializable representation of Savegames, since its ObservableLists are not serializable.
 *
 * @author Nils
 */
public class SerializableSavegames implements Serializable {

	public List<SerializableSavegame> savegames;

	public SerializableSavegames(ObservableList<Savegame> in) {
		this.savegames = new ArrayList<>(in.size());
		in.forEach(savegame -> savegames.add(new SerializableSavegame(savegame)));
	}
}
