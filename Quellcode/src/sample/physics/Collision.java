package sample.physics;

import javafx.scene.shape.Shape;
import sample.Ball;
import sample.Playingfield;
import sample.model.Vector2D;

/**
 * @author Nils Wende
 * @author Simon Oswald
 * @author Jendrik Jordan
 */
public class Collision {
	public boolean isColliding(Shape obj1, Shape obj2) {return Shape.intersect(obj1, obj2).getBoundsInLocal().getWidth() != -1;}
	public boolean isOutsideVerticalBounds(double y, double height) {return((y < 0) || (y > height));}
	public boolean isOutsideHorizontalBounds(double x, double width ) {return((x < 0) || (x > width));}
	public void getPostCollisionVelocity(Vector2D velocity, double dampening, Vector2D normalUnitVector) {velocity.subtract2(normalUnitVector.scalarMultiplication((1 + dampening) * velocity.scalarProduct(normalUnitVector)));}
}
