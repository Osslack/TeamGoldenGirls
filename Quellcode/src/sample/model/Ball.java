/*unused*/
package sample.model;

/**
 * @author Simon
 * @author Nils Wende
 */

public class Ball extends Circle {
	private final Vector2D mVelocity;
	private final double   mMass;

	public Ball() {
		super();
		mVelocity = new Vector2D();
		mMass = 0.0;
	}

	public Ball(Vector2D center, double dampening, double radius, Vector2D position, Vector2D velocity, double mass) {
		super(center, dampening,radius);
		mVelocity = velocity;
		mMass = mass;
	}


	@Override
	public String toString() {
		return "Ball ( Center " + getCenter() + " | Radius " + this.getRadius() + " | Speed " + mVelocity + " | Mass " + mMass + " )";
	}
}
