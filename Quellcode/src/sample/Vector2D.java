/**
 * @author Simon
 * @author Nils Wende
 */

public class Vector2D {
	public static final Vector2D X_AXIS = new Vector2D(1.0, 0.0);
	public static final Vector2D Y_AXIS = new Vector2D(0.0, 1.0);

	public double mX;
	public double mY;

	public Vector2D() {
	}

	public Vector2D(double x, double y) {
		mX = x;
		mY = y;
	}

	public Vector2D add(Vector2D v) {
		Vector2D result = new Vector2D();
		result.mX = this.mX + v.mX;
		result.mY = this.mY + v.mY;
		return result;
	}

	public Vector2D subtract(Vector2D v) {
		Vector2D result = new Vector2D();
		result.mX = this.mX - v.mX;
		result.mY = this.mY - v.mY;
		return result;
	}

	public double scalarProduct(Vector2D v) {
		return this.mX * v.mX + this.mY * v.mY;
	}

	public Vector2D scalarMultiplication(double factor) {
		Vector2D result = new Vector2D();
		result.mX = this.mX * factor;
		result.mY = this.mY * factor;
		return result;
	}

	public double length() {
		return Math.sqrt(square(this.mX) + square(this.mY));
	}

	private double square(double a) {
		return a * a;
	}

	public double getDistanceTo(Vector2D v) {
		final Vector2D connectingVector = this.subtract(v);
		return connectingVector.length();
	}

	public double getAngleTo(Vector2D v) {
		final double cosPhi = this.scalarProduct(v) / (this.length() * v.length());
		return Math.acos(cosPhi);
	}

	public Vector2D getNormalToLeft() {
		return new Vector2D(-this.mY, this.mX);
	}

	public Vector2D getNormalToRight() {
		return new Vector2D(this.mY, -this.mX);
	}

	@Override
	public String toString() {
		return "(" + mX + ", " + mY + ")";
	}

	public static void main(String[] args) {
	}
}