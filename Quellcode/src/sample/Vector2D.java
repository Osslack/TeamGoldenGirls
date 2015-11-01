/**
 * @author Simon
 * @author Nils Wende
 */

public class Vector2D {
	public double mX;
	public double mY;

	public Vector2D() {
		mX = 0.0;
		mY = 0.0;
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

	// if Vector2D is a line
	public double length() {
		return Math.sqrt(square(this.mX) + square(this.mY));
	}

	// if we have two Vector2Ds representing positions
	public double distanceTo(Vector2D b) {
		Vector2D ab = this.subtract(b);
		return ab.length();
	}

	@Override
	public String toString() {
		return "(" + mX + "," + mY + ")";
	}

	private static double square(double a) {
		return a * a;
	}

	public static void main(String[] args) {
	}
}