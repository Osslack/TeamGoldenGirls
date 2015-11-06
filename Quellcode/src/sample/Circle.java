package sample;

import sample.model.Vector2D;

/**
 * @author Simon
 */

public class Circle extends javafx.scene.shape.Circle{
	public double mDampening;
	public Circle(){
		super();
		mDampening = 1.0;
	}
	public Circle(Vector2D center,double dampening,double radius){
		super(center.mX,center.mY,radius);
		mDampening = dampening;
	}
	public Vector2D getCenter(){
		Vector2D center = new Vector2D(this.getCenterX(),this.getCenterY());
		return center;
	}
	@Override 
	public String toString(){
		return "Circle ( Center " + getCenter() + " | Radius " + this.getRadius() + " | Dampening " + mDampening + " )";
	}
	public static void main(String[] args){
		System.out.println(new Circle());
	}
}