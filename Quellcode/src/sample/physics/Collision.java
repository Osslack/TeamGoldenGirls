package physics;

import model.Vector2D;

/**
 * @author Nils Wende
 */
public class Collision {

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
		final Vector2D movement = normalUnitVector.scalarMultiplication(length);
		return velocity.subtract(movement);
	}
}
