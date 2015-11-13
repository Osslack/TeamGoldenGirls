package sample;

import sample.model.Vector2D;

/**
 * @author Simon
 * @author Nils Wende
 */

public class Ball extends Circle {
	public       Vector2D mVelocity;
	public final double   mMass;

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

	public static void main(String[] args) {
		System.out.println(new Ball());
	}
}
