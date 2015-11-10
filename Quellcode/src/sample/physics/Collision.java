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
	public static final Vector2D normalUnitVectorTopBorder    = new Vector2D(0.0, -1.0);
	public static final Vector2D normalUnitVectorLeftBorder   = new Vector2D(1.0, 0.0);
	public static final Vector2D normalUnitVectorRightBorder  = new Vector2D(-1.0, 0.0);
	public static final Vector2D normalUnitVectorBottomBorder = new Vector2D(0.0, 1.0);

	public boolean collisionDetected(Ball ball, Shape obstacle) {
		final Shape intersection = Shape.intersect(ball, obstacle);
		return intersection.getBoundsInLocal().getWidth() != -1;
	}

	/**
	 * @param velocity         velocity vector of the ball
	 * @param dampening        == 0 => ball will stop				  (no speed)
	 *                         == 1 => fully elastical collision	(same speed)
	 *                         > 1 => positive acceleration of ball	(more speed)
	 * @param normalUnitVector the normal unit vector of the line, facing the ball
	 * @return the new velocity vector
	 */
	public Vector2D getPostCollisionVelocity(Vector2D velocity, double dampening, Vector2D normalUnitVector) {
		final double length = (1 + dampening) * velocity.scalarProduct(normalUnitVector);
		final Vector2D direction = normalUnitVector.scalarMultiplication(length);
		return velocity.subtract(direction);
	}

	public boolean checkBorderCollision(Ball ball) {
		final double radius = ball.getRadius();
		final double posX = ball.getCenterX();
		final double posY = ball.getCenterY();

		return checkTopBorderCollision(posY + radius)
				|| checkLeftBorderCollision(posX - radius)
				|| checkRightBorderCollision(posX + radius)
				|| checkBottomBorderCollision(posY - radius);
	}

	private boolean checkTopBorderCollision(double ballEdge) {
		return ballEdge >= Playingfield.scene_height;
	}

	private boolean checkLeftBorderCollision(double ballEdge) {
		return ballEdge >= 0.0;
	}

	private boolean checkRightBorderCollision(double ballEdge) {
		return ballEdge >= Playingfield.scene_width;
	}

	private boolean checkBottomBorderCollision(double ballEdge) {
		return ballEdge >= 0.0;
	}
}
