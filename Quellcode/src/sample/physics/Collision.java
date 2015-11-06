package sample.physics;

import javafx.scene.shape.Shape;
import sample.Ball;
import sample.Playingfield;
import sample.model.Vector2D;

/**
 * @author Nils Wende
 * @author Simon Oswald
 */
public class Collision {
	public static final Vector2D normalUnitVectorWallLeft = new Vector2D(1.0,0.0);
	public static final Vector2D normalUnitVectorWallRight = new Vector2D(-1.0,0.0);
	public static final Vector2D normalUnitVectorFloor = new Vector2D(0.0,1.0);
	public static final Vector2D normalUnitVectorCeiling = new Vector2D(0.0,-1.0);

	//TODO change Ball to extend javafx.scene.shape.Circle DONE BITCH
	public boolean collisionDetected(Ball ball, Shape obstacle) {
		Shape intersection = Shape.intersect(ball, obstacle);
		return intersection.getBoundsInLocal().getWidth() != -1;
	}

	/**
	 * @param velocity velocity vector of the ball
	 * @param dampening == 0 => ball will stop					(no speed)
	 *                  == 1 => fully elastical collision		(same speed)
	 *                   > 1 => positive acceleration of ball	(more speed)
	 * @param normalUnitVector the normal unit vector of the line, facing the ball
	 * @return the new velocity vector
	 */
	public Vector2D getPostCollisionVelocity(Vector2D velocity, double dampening, Vector2D normalUnitVector) {
		final double length = (1 + dampening) * velocity.scalarProduct(normalUnitVector);
		final Vector2D direction = normalUnitVector.scalarMultiplication(length);
		return velocity.subtract(direction);
	}
	public boolean checkBorderCollision(Ball ball) {
		double radius = ball.getRadius();
		double x_coord = ball.getCenterX();
		double y_coord = ball.getCenterY();

		if (x_coord - radius >= 0.0									//left wall
			|| x_coord + radius >= Playingfield.scene_width 		//right wall
			||y_coord - radius >= 0									//floor
			|| y_coord + radius >= Playingfield.scene_height ) { 	//ceiling
			return true;
		}
	 	else return false;
	}
}
