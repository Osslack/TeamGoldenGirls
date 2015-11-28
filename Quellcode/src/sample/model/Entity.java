/**
 * @author Simon
 */
/*unused*/

package sample.model;

import java.util.ArrayList;

class Entity {
	private ArrayList<Vector2D> mPositions = new ArrayList<Vector2D>();
	private final double mDampening;

	public Entity() {
		mPositions.add(new Vector2D());
		mDampening = 0.0;
	}

	public Entity(Vector2D position, double dampening) {
		mPositions.add(position);
		mDampening = dampening;
	}

	public Entity(ArrayList<Vector2D> listPositions, double dampening) {
		mPositions = listPositions;
		mDampening = dampening;
	}

	@Override
	public String toString() {
		return "Entity ( Positions: " + mPositions.toString() + " | Dampening " + mDampening + " )";
	}
}