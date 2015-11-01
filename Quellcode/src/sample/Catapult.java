/**
 * @author Nils Wende
 */

import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 * assuming coordinate system with point of origin in lower left corner
 */
class Catapult {
	Polygon  rubber;
	Line     ruler;
	Vector2D pivotPoint;

	private final double rulerLength = 15.0, // in cm
			rubberSideLength         = 3.0, // in cm
			rubberLeftVertexX,
			minRulerOverhang         = rulerLength * 0.1,
			step                     = 0.1; // in cm

	Catapult() {
		final double paddingToLeftScreenEdge = 0.5;
		final double tipPositionX = rulerLength - minRulerOverhang + paddingToLeftScreenEdge;
		final double rubberHeight = Math.sqrt(3) * rubberSideLength / 2;
		rubberLeftVertexX = tipPositionX - rubberSideLength / 2;
		rubber = new Polygon(tipPositionX, rubberHeight,
							 tipPositionX + rubberSideLength / 2, 0.0,
							 rubberLeftVertexX, 0.0
		); // equilateral triangle

		final double deltaX = Math.sqrt(rulerLength * rulerLength / 4 - rubberHeight * rubberHeight);
		final double startX = tipPositionX - deltaX;
		final double endX = tipPositionX + deltaX;
		ruler = new Line(startX, 0, endX, 2 * rubberHeight); // tip of rubber is at half of ruler length, yStart is always 0

		pivotPoint = new Vector2D(tipPositionX, rubberHeight); // set to tip of rubber
	}

	void fire(double power) {
		//TODO invoke the physics calculation for trajectory
	}

	void moveRulerToLeft() {
		final Vector2D lineEnd = new Vector2D(ruler.getEndX(), ruler.getEndY());
		final double remainingLength = getDistanceBetween(pivotPoint, lineEnd);
		if (remainingLength > minRulerOverhang) {
			// move the start point to the left
			final double startX = ruler.getStartX();
			ruler.setStartX(startX - step);
			// set the new end point
			setNewLineEndpoint();
		}
	}

	private double getDistanceBetween(Vector2D v, Vector2D w) {
		final double deltaX = v.mX - w.mX;
		final double deltaY = v.mY - w.mY;
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}

	private void setNewLineEndpoint() {
		final Vector2D lineStart = new Vector2D(ruler.getStartX(), 0);
		final Vector2D newLineEnd = getPointOnLine(lineStart, pivotPoint, rulerLength);
		ruler.setEndX(newLineEnd.mX);
		ruler.setEndY(newLineEnd.mY);
	}

	private Vector2D getPointOnLine(Vector2D v, Vector2D w, double distanceFromV) {
		final double length = getDistanceBetween(v, w);
		double deltaX = v.mX - w.mX;
		double deltaY = v.mY - w.mY;
		// normalize the vectors
		deltaX /= length;
		deltaY /= length;
		// scale the vectors with the desired length
		deltaX *= distanceFromV;
		deltaY *= distanceFromV;

		return new Vector2D(v.mX + deltaX, v.mY + deltaY);
	}

	void moveRulerToRight() {
		final double startX = ruler.getStartX();
		if (rubberLeftVertexX - step > startX) {
			// move the start point to the right
			ruler.setStartX(startX + step);
			setNewLineEndpoint();
		}
	}

	//TODO reasonable max height for rubber?
	void enlargeRubber() {
		// increase the y-Coordinate of the rubber tip by the value of step
		ObservableList<Double> points = rubber.getPoints();
		final double yPos = points.get(1);
		if (yPos < 5.0) {
			points.set(1, yPos + step);
			// increase the y-Coordinate of the pivotPoint
			pivotPoint.mY += step;
			// move ruler
			setNewLineEndpoint();
		}
	}

	//TODO reasonable min height for rubber?
	void shrinkRubber() {
		// decrease the y-Coordinate of the rubber tip by the value of step
		ObservableList<Double> points = rubber.getPoints();
		final double yPos = points.get(1);
		if (yPos > 0.0) {
			points.set(1, yPos - step);
			// decrease the y-Coordinate of the pivotPoint
			pivotPoint.mY -= step;
			// move ruler
			setNewLineEndpoint();
		}
	}


	/* method overloads: */

	private Vector2D getPointOnLine(double x1, double y1, double x2, double y2, double distanceFrom1) {
		final double length = getDistanceBetween(x1, y1, x2, y2);
		double deltaX = x1 - x2;
		double deltaY = y1 - y2;
		// normalize the vectors
		deltaX /= length;
		deltaY /= length;
		// scale the vectors with the desired length
		deltaX *= distanceFrom1;
		deltaY *= distanceFrom1;

		return new Vector2D(x1 + deltaX, y1 + deltaY);
	}

	private double getDistanceBetween(double x1, double y1, double x2, double y2) {
		double deltaX = x1 - x2;
		double deltaY = y1 - y2;
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}
}
