package sample.model;
/*unused*/

/**
 * @author Simon
 */

public class TargetCircle extends Circle {
	public TargetCircle() {
		super();
	}

	public TargetCircle(Vector2D center, double dampening, double radius) {
		super(center, dampening, radius);
	}

	@Override
	public String toString() {
		return "TargetCircle ( Center " + getCenter() + " | Radius " + this.getRadius() + " | Dampening " + mDampening + " )";
	}

	public static void main(String[] args) {
		System.out.println(new TargetCircle());
	}
}