/**
 * @author Simon
 * @author Nils Wende
 */

public class Ball extends Circle{
	public Vector2D mPosition;
	public Vector2D mVelocity;
	public final double   mMass;

	public Ball() {
		super();
		mPosition = new Vector2D();
		mVelocity = new Vector2D();
		mMass = 0.0;
	}
	public Ball(Vector2D center, double dampening, double radius, Vector2D position, Vector2D velocity, double mass){
		super(center,dampening,radius);
		mPosition = position;
		mVelocity = velocity;
		mMass = mass;
	}
	@Override
	public String toString(){
		return "Ball ( Center " + getCenter() + " | Radius " + mRadius + " | Direction " + mPosition + " | Speed " + mVelocity + " | Mass " + mMass + " )";
	}
	public static void main(String[] args){
		System.out.println(new Ball());
	}
}
