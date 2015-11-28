/**
 * @author Simon
 */
/*unused*/

package sample.model;

import java.util.ArrayList;

class Rectangle extends javafx.scene.shape.Rectangle {
	final double mDampening;
	ArrayList<Vector2D> mCorners;

	Rectangle() {
		super();
		mDampening = 1.0;
	}

	Rectangle(double x, double y, double width, double height, double dampening) {
		super(x,y,width,height);
		mDampening = dampening;
		mCorners.add(new Vector2D(x,y));
		mCorners.add(new Vector2D(x,y-height));
		mCorners.add(new Vector2D(x+width,y));
		mCorners.add(new Vector2D(x+width,y-height));
	}

	public ArrayList<Vector2D> getCorners() {
		return mCorners;
	}

	@Override
	public String toString() {
		return "Rectangle( Corners: " + (mCorners.get(0)) + " " + (mCorners.get(1)) + " " + (mCorners.get(2)) + " " + (mCorners.get(3)) + " | Dampening: " + mDampening + " )";
	}

	public static void main(String[] args) {
		System.out.println(new Rectangle());
	}
}