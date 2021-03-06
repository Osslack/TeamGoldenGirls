/**
 * @author Simon
 */
/*unused*/

package sample.model;

public class TargetRectangle extends Rectangle {
	public TargetRectangle() {
		super();
	}

	public TargetRectangle(double x, double y, double width, double height, double dampening) {
		super(x, y, width, height, dampening);
	}

	@Override
	public String toString() {
		return "TargetRectangle( Corners: " + (mCorners.get(0)) + " " + (mCorners.get(1)) + " " + (mCorners.get(2)) + " " + (mCorners.get(3)) + " | Dampening: " + mDampening + " )";
	}
}