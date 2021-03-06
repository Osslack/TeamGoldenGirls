/*used*/

package sample.physics;

import sample.model.Vector2D;

/**
 * @author Nils Wende
 *         JENDRIK
 *         Describes the trajectory of a sphere possibly influenced by drag.
 **/
class DragTrajectory {
	private static final double DRAG_CONST = 0.5, // drag-coefficient for a sphere
			GRAV_CONST                     = 9.81;


	private final double capitalD;

	private final double deltaT = 0.000001,
			timeFactor          = deltaT * deltaT / 2;

	private final int iterationsPerFrame = 1;

	/**
	 * @param mass       in kilograms
	 * @param radius     in meters
	 * @param airDensity usually 1.2; Set to 0.0 for a drag-free calculation
	 */
	public DragTrajectory(double mass, double radius, double airDensity) {
		final double area = Math.PI * radius * radius;
		capitalD = -(airDensity * DRAG_CONST * area) / (((double) 2) * mass);
	}
	//simulate moving the sphere for the number of steps specified
	public Vector2D[] simulateNextSteps(Vector2D velocity, Vector2D position, int steps) {
		Vector2D[] arr = {velocity, position};
		for (int i = 0; i < steps; ++i) {
			arr = simulateNext(arr[0], arr[1]);
		}
		return arr;
	}
	//simulates moving the sphere and returns the new velocity and position
	private Vector2D[] simulateNext(Vector2D velocity, Vector2D position) {
		/* since we only simulate, we dont want to change the objects referenced by the parameters directly */
		Vector2D newVel = new Vector2D(velocity.mX, velocity.mY),
				newPos = new Vector2D(position.mX, position.mY);
		double factor,
				acc;
		for (int i = 0; i < iterationsPerFrame; ++i) {
			factor = capitalD * Math.sqrt(newVel.mX * newVel.mX + newVel.mY * newVel.mY);
			// x-coordinates
			acc = factor * newVel.mX;
			newVel.mX += acc * deltaT;
			newPos.mX += (newVel.mX * deltaT) + (acc * timeFactor);
			// y-coordinates
			acc = factor * newVel.mY - GRAV_CONST;
			newVel.mY += acc * deltaT;
			newPos.mY += (newVel.mY * deltaT) + (acc * timeFactor);
		}
		return new Vector2D[]{newVel, newPos};
	}
	//moves the sphere
	public void simNext(Vector2D velocity, Vector2D position, double timeelapsed) {
		double factor,
				acc;
		double timeFactor2 = timeelapsed * timeelapsed / 2;
		factor = capitalD * Math.sqrt(velocity.mX * velocity.mX + velocity.mY * velocity.mY);
		// x-coordinates
		acc = factor * velocity.mX;
		velocity.mX += acc * timeelapsed;
		position.mX += (velocity.mX * timeelapsed) + (acc * timeFactor2);
		// y-coordinates
		acc = factor * velocity.mY + GRAV_CONST;
		velocity.mY += acc * timeelapsed;
		position.mY += (velocity.mY * timeelapsed) + (acc * timeFactor2);
	}

}
