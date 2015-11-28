package sample.model;

/**
 * @author Simon
 */

class Circle extends javafx.scene.shape.Circle {
	final double mDampening;

	Circle() {
		super();
		mDampening = 1.0;
	}

	Circle(Vector2D center, double dampening, double radius) {
		super(center.mX, center.mY, radius);
		mDampening = dampening;
	}

	Vector2D getCenter() {
		return new Vector2D(this.getCenterX(), this.getCenterY());
	}

	@Override
	public String toString() {
		return "Circle ( Center " + getCenter() + " | Radius " + this.getRadius() + " | Dampening " + mDampening + " )";
	}
}