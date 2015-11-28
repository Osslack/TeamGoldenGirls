package sample.model;

/**
 * @author Simon
 */

public class Windmeter{
	public final Vector2D mDirection;
	public final double   mStrength;

	public Windmeter() {
		mStrength = 0.0;
		mDirection = new Vector2D();
	}
	public Windmeter(Vector2D direction,double strength){
		mDirection = direction;
		mStrength = strength;
	}
}