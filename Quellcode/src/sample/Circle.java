package sample;

import sample.model.Vector2D;

/**
 * @author Simon
 */

public class Circle extends Entity{
	public double mRadius;
	public Circle(){
		super();
		mRadius = 0.0;
	}
	public Circle(Vector2D center,double dampening,double radius){
		super(center,dampening);
		mRadius = radius;
	}
	public Vector2D getCenter(){
		return mPositions.get(0);
	}
	@Override 
	public String toString(){
		return "Circle ( Center " + getCenter() + " | Radius " + mRadius + " | Dampening " + mDampening + " )";
	}
	public static void main(String[] args){
		System.out.println(new Circle());
	}
}