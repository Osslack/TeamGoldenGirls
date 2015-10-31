/**
 * @author Nils Wende
 */

/**
 * Describes the trajectory of a sphere influenced by drag.
 **/
class DragTrajectory
{
	private static final double DRAG_CONST = 0.5,
			NEG_GRAV_CONST                 = -9.81;

	private final double bigD;

	private final double deltaT             = 0.000001;
	private final double timeFactor         = deltaT * deltaT / 2;
	private final int    iterationsPerFrame = 400;

	DragTrajectory(double mass, double radius, double airDensity)
	{
		final double area = Math.PI * radius * radius;
		bigD = -airDensity * DRAG_CONST * area / (2 * mass);
	}

	Vector2D[] simulateNextSteps(Vector2D velocity, Vector2D position, int steps)
	{
		Vector2D[] arr = {velocity, position};
		for (int i = 0; i < steps; ++i)
		{
			arr = simulateNext(arr[0], arr[1]);
		}
		return arr;
	}

	Vector2D[] simulateNext(Vector2D velocity, Vector2D position)
	{
		/* since we only simulate, we dont want to change the objects referenced by the parameters directly
		 * if only call is from simulateNextSteps(), declarations can be put there */
		Vector2D newVel = new Vector2D(velocity.mX, velocity.mY),
				newPos = new Vector2D(position.mX, position.mY);
		double freq,
				acc;
		for (int i = 0; i < iterationsPerFrame; ++i)
		{
			freq = bigD * Math.sqrt(newVel.mX * newVel.mX + newVel.mY * newVel.mY);
			// x-coordinate
			acc = freq * newVel.mX;
			newVel.mX += acc * deltaT;
			newPos.mX += (newVel.mX * deltaT) + (acc * timeFactor);
			// y-coordinate
			acc = NEG_GRAV_CONST + freq * newVel.mY;
			newVel.mY += acc * deltaT;
			newPos.mY += (newVel.mY * deltaT) + (acc * timeFactor);
		}
		return new Vector2D[]{newVel, newPos};
	}
}
