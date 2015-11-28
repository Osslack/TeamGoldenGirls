/*unused*/

package sample.model;

/**
 * @author Simon
 */

class Windmeter {
	private final Vector2D mDirection;
	private final double   mStrength;

	public Windmeter() {
		mStrength = 0.0;
		mDirection = new Vector2D();
	}

	public Windmeter(Vector2D direction, double strength) {
		mDirection = direction;
		mStrength = strength;
	}
}