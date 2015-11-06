package sample;

import sample.model.Vector2D;

/**
 * @author Simon
 */
import sample.model.Vector2D;

public class Windmeter{
	public Vector2D mDirection;
	public double   mStrength;

	public Windmeter() {
		mStrength = 0.0;
		mDirection = new Vector2D();
	}
	public Windmeter(Vector2D direction,double strength){
		mDirection = direction;
		mStrength = strength;
	}
}