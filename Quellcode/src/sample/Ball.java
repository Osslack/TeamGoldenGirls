// Create by Simon on  30/10
// Worked on by:
// Simon
public class Ball extends Circle{
	public Vector2D mDirection;
	public double mSpeed;
	public double mWeight;
	public Ball(){
		super();
		mDirection = new Vector2D();
		mSpeed = 0.0;
		mWeight = 0.0;
	}
	public Ball(Vector2D center,double dampening,double radius,Vector2D direction,double speed,double weight){
		super(center,dampening,radius);
		mDirection = direction;
		mSpeed = speed;
		mWeight = weight;
	}

}
