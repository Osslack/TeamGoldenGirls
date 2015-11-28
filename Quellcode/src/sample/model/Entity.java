/**
 * @author Simon
 */
/*unused*/

package sample.model;

import java.util.ArrayList;

public class Entity {
	protected ArrayList<Vector2D> mPositions = new ArrayList<Vector2D>();
	public double mDampening;

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

	public static void main(String[] args) {
		System.out.println(new Entity());
	}
}