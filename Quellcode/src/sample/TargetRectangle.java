/**
 * @author Simon
 */

import java.util.ArrayList;

public class TargetRectangle extends Rectangle {
	public TargetRectangle() {
		super();
	}

	public TargetRectangle(ArrayList<Vector2D> corners, double dampening) {
		super(corners, dampening);
	}

	@Override
	public String toString() {
		return "TargetRectangle( Corners: " + (mPositions.get(0)) + " " + (mPositions.get(1)) + " " + (mPositions.get(2)) + " " + (mPositions.get(3)) + " | Dampening: " + mDampening + " )";
	}

	public static void main(String[] args) {
		System.out.println(new TargetRectangle());
	}
}