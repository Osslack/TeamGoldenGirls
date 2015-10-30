// Create by Simon on  30/10
// Worked on by:
// Simon
public class Windmeter{
	public Vector2D mDirection;
	public double mStrength;

	public Windmeter(){
		mStrength = 0.0;
		mDirection = new Vector2D();
	}
	public Windmeter(Vector2D direction,double strength){
		mDirection = direction;
		mStrength = strength;
	}
}