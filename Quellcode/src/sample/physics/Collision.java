/*used*/

package sample.physics;

import javafx.scene.shape.Shape;
import sample.model.Vector2D;

/**
 * @author Nils Wende
 * @author Simon Oswald
 * @author Jendrik Jordan
 */
class Collision {
	//checks if 2 JavaFX Shapes are colliding
	public boolean isColliding(Shape obj1, Shape obj2) {
		return Shape.intersect(obj1, obj2).getBoundsInLocal().getWidth() != -1;
	}
	//checks if the point y is above or below the window
	public boolean isOutsideVerticalBounds(double y, double height) {
		return ((y < 0) || (y > height));
	}
	//checks if the point x is left or right of the window
	public boolean isOutsideHorizontalBounds(double x, double width) {
		return ((x < 0) || (x > width));
	}
	//calculates the velocity after a collision
	public void getPostCollisionVelocity(Vector2D velocity, double dampening, Vector2D normalUnitVector) {
		final double factor = (1 + dampening) * velocity.scalarProduct(normalUnitVector);
		final Vector2D vec = normalUnitVector.scalarMultiplication(factor);
		velocity.subtract2(vec);
	}
}
