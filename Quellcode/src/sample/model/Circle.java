package sample.model;

/**
 * @author Simon
 */

public class Circle extends javafx.scene.shape.Circle {
	public final double mDampening;

	public Circle() {
		super();
		mDampening = 1.0;
	}

	public Circle(Vector2D center, double dampening, double radius) {
		super(center.mX, center.mY, radius);
		mDampening = dampening;
	}

	public Vector2D getCenter() {
		return new Vector2D(this.getCenterX(), this.getCenterY());
	}

	@Override
	public String toString() {
		return "Circle ( Center " + getCenter() + " | Radius " + this.getRadius() + " | Dampening " + mDampening + " )";
	}
}