import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import model.Vector2D;

/**
 * @author Nils Wende
 * assuming coordinate system with point of origin in lower left corner
 */
public class Catapult {
	Polygon  rubber;
	Line     ruler;
	Vector2D pivotPoint;

	private final double rulerLength = 15.0, // in cm
			rubberSideLength         = 3.0, // in cm
			minRulerOverhang         = rulerLength * 0.1,
			step                     = 0.1; // in cm
	private double rubberLeftVertexX;

	public Catapult() {
		final double paddingToLeftScreenEdge = 0.5;
		final double rubberTipPositionX = rulerLength - minRulerOverhang + paddingToLeftScreenEdge;
		final double rubberHeight = Math.sqrt(3) * rubberSideLength / 2.0;

		setRubberTo(rubberTipPositionX, rubberHeight);
		setRulerTo(rubberTipPositionX, rubberHeight);
		setPivotPointTo(rubberTipPositionX, rubberHeight);
	}

	private void setRubberTo(double tipPositionX, double rubberHeight) {
		rubberLeftVertexX = tipPositionX - rubberSideLength / 2.0;
		rubber = new Polygon(tipPositionX, rubberHeight,
							 tipPositionX + rubberSideLength / 2.0, 0.0,
							 rubberLeftVertexX, 0.0
		); // equilateral triangle
	}

	private void setRulerTo(double tipPositionX, double rubberHeight) {
		final double deltaX = Math.sqrt(rulerLength * rulerLength / 4.0 - rubberHeight * rubberHeight);
		final double startX = tipPositionX - deltaX;
		final double endX = tipPositionX + deltaX;
		ruler = new Line(startX, 0.0, endX, 2.0 * rubberHeight);
		// tip of rubber is at half of ruler length, yStart is always 0
	}

	private void setPivotPointTo(double tipPositionX, double rubberHeight) {
		pivotPoint = new Vector2D(tipPositionX, rubberHeight);
	}


	public void fire(double power) {
		final double remainingLength = getRemainingLength();
		final Vector2D newEnd;
		if (remainingLength > rubberSideLength) {
			newEnd = getEndPointOnXAxis(remainingLength);
		}
		else {
			newEnd = getEndPointOnRubber(remainingLength);
		}
		final Vector2D line = pivotPoint.subtract(newEnd);
		final Vector2D normal = line.getNormalToRight();
		final double launchingAngle = normal.getAngleTo(Vector2D.X_AXIS);
		final double velX = power * Math.cos(launchingAngle);
		final double velY = power * Math.sin(launchingAngle);

		//TODO invoke the physics calculation for trajectory
	}

	private double getRemainingLength() {
		final Vector2D lineEnd = new Vector2D(ruler.getEndX(), ruler.getEndY());
		return pivotPoint.getDistanceTo(lineEnd);
	}

	private Vector2D getEndPointOnXAxis(double remainingLength) {
		final double radicand = remainingLength * remainingLength - pivotPoint.mY * pivotPoint.mY;
		final double positionX = pivotPoint.mX + Math.sqrt(radicand);
		return new Vector2D(positionX, 0.0);
	}

	private Vector2D getEndPointOnRubber(double remainingLength) {
		final ObservableList<Double> points = rubber.getPoints();
		final double positionX = points.get(5);
		final double positionY = points.get(6);
		final Vector2D rubberRightVertex = new Vector2D(positionX, positionY);
		return getPointOnLine(pivotPoint, rubberRightVertex, remainingLength);
	}


	public void moveRulerToLeft() {
		final double remainingLength = getRemainingLength();
		if (remainingLength > minRulerOverhang) {
			// move the start point to the left
			final double startX = ruler.getStartX();
			ruler.setStartX(startX - step);
			setNewLineEndpoint();
		}
	}

	private void setNewLineEndpoint() {
		final Vector2D lineStart = new Vector2D(ruler.getStartX(), 0.0);
		final Vector2D newLineEnd = getPointOnLine(lineStart, pivotPoint, rulerLength);
		ruler.setEndX(newLineEnd.mX);
		ruler.setEndY(newLineEnd.mY);
	}

	private Vector2D getPointOnLine(Vector2D v, Vector2D w, double distanceFromV) {
		Vector2D result = w.subtract(v);
		result = result.normalize();
		result = result.scalarMultiplication(distanceFromV);
		return v.add(result);
	}


	public void moveRulerToRight() {
		final double startX = ruler.getStartX();
		if (rubberLeftVertexX - step > startX) {
			// move the start point to the right
			ruler.setStartX(startX + step);
			setNewLineEndpoint();
		}
	}

	//TODO reasonable max height for rubber?
	public void enlargeRubber() {
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
	public void shrinkRubber() {
		// decrease the y-Coordinate of the rubber tip by the value of step
		ObservableList<Double> points = rubber.getPoints();
		final double yPos = points.get(1);
		if (yPos > step) {
			points.set(1, yPos - step);
			// decrease the y-Coordinate of the pivotPoint
			pivotPoint.mY -= step;
			// move ruler
			setNewLineEndpoint();
		}
	}
}
