/**
 * @author Simon
 */

import java.util.ArrayList;

public class Rectangle extends Entity {
	public Rectangle() {
		super();
		ArrayList<Vector2D> corners = new ArrayList<Vector2D>();
		corners.add(new Vector2D());
		corners.add(new Vector2D());
		corners.add(new Vector2D());
		corners.add(new Vector2D());
		mPositions = corners;

	}

	public Rectangle(ArrayList<Vector2D> corners, double dampening) {
		super(corners, dampening);
	}

	public ArrayList<Vector2D> getCorners() {
		return mPositions;
	}

	@Override
	public String toString() {
		return "Rectangle( Corners: " + (mPositions.get(0)) + " " + (mPositions.get(1)) + " " + (mPositions.get(2)) + " " + (mPositions.get(3)) + " | Dampening: " + mDampening + " )";
	}

	public static void main(String[] args) {
		System.out.println(new Rectangle());
	}
}