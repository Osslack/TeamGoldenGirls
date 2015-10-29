/** @author: Nils Wende
*/

package sample;

import java.util.Observable;

import javafx.scene.Line;
import javafx.scene.Polygon;

/** assuming coordinate system with point of origin in lower left corner
*/
class Catapult extends Observable
{
	Polygon rubber;
	Line ruler;
	Vector pivotPoint;
	
	private final double rulerLength = 15.0; // in cm
	private final double rubberSidelength = 3.0; // in cm
	private final double rubberLeftVertexX = tipPositionX - rubberSidelength/2;
	private final double minRulerOverhang = rulerLength * 0.1;
	private final double step = 0.1;
	
	Catapult()
	{
		final double paddingToLeftScreenEdge = 0.5;
		final double tipPositionX = rulerLength - minRulerOverhang + paddingToLeftScreenEdge;
		final double rubberHeight = Math.sqrt(3) * rubberSidelength / 2;
		rubber = new Polygon(); // equilateral triangle
		rubber.getPoints().addAll(new Double[] {tipPositionX                     , rubberHeight,
												tipPositionX + rubberSidelength/2, 0.0,
												rubberLeftVertexX, 0.0}
									);
									
		final double deltaX = Math.sqrt(rulerLength*rulerLength/4 - rubberHeight*rubberHeight);
		final double xStart = tipPositionX - deltaX;
		final double xEnd = tipPositionX + deltaX;
		ruler = new Line(xStart, 0, xEnd, 2*rubberHeight); // tip of rubber is at half of ruler length, yStart is always 0
		
		pivotPoint = new Vector(tipPositionX, rubberHeight); // set to tip of rubber
		
		this.setChanged();
		this.notifyObservers();
	}
	
	void fire()
	{
		//TODO invoke the physics calculation for trajectory
	}
	
	void moveRulerToLeft()
	{
		final Vector lineEnd = new Vector(ruler.getEndX(), ruler.getEndY());
		final double remainingLength = getDistanceBetween(pivotPoint, lineEnd);
		if (remainingLength > minRulerOverhang)
		{
			// move the start point to the left
			final double startX = ruler.getStartX();
			ruler.setStartX(startX - step);
			// set the new end point
			final Vector lineStart = new Vector(ruler.getStartX(), 0);
			final Vector newLineEnd = getPointOnLine(lineStart, pivotPoint, rulerLength);
			ruler.setEndX(newLineEnd.x);
			ruler.setEndY(newLineEnd.y);
		}
	}
	
	void moveRulerToRight()
	{
		final double startX = ruler.getStartX();
		if (rubberLeftVertexX - step > startX)
		{
			// move the start point to the right
			ruler.setStartX(startX + step);
			// set the new end point
			final Vector lineStart = new Vector(ruler.getStartX(), 0);
			final Vector newLineEnd = getPointOnLine(lineStart, pivotPoint, rulerLength);
			ruler.setEndX(newLineEnd.x);
			ruler.setEndY(newLineEnd.y);
		}
	}
	
	void enlargeRubber()
	{
		ObservableList<Double> points = rubber.getPoints();
		final double yPos = points.get(1);
		points.set(1, yPos + step); //increases the y-Coordinate of the rubber tip by the value of step
		pivotPoint.y += step;
		//TODO move ruler too
		
		this.setChanged();
		this.notifyObservers();
	}
	
	void shrinkRubber()
	{
		ObservableList<Double> points = rubber.getPoints();
		final double yPos = points.get(1);
		points.set(1, yPos - step); //decreases the y-Coordinate of the rubber tip by the value of step
		pivotPoint.y -= step;
		//TODO move ruler too
		
		this.setChanged();
		this.notifyObservers();
	}
	
	private Vector getPointOnLine(Vector v, Vector w, double distanceFromV)
	{
		final double length = getDistanceBetween(v, w);
		double deltaX = v.x - w.x;
		double deltaY = v.y - w.y;
		// normalize the vectors
		deltaX /= length;
		deltaY /= length;
		// scale the vectors with the desired length
		deltaX *= distanceFromV;
		deltaY *= distanceFromV;
		
		return new Vector(v.x + deltaX, v.y + deltaY);
	}
	
	private Vector getPointOnLine(double x1, double y1, double x2, double y2, double distanceFrom1)
	{
		final double deltaX = x1 - x2;
		final double deltaY = y1 - y2;
		final double length = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		// normalize the vectors
		deltaX /= length;
		deltaY /= length;
		// scale the vectors with the desired length
		deltaX *= distanceFrom1;
		deltaY *= distanceFrom1;
		
		return new Vector(x1 + deltaX, y1 + deltaY);
	}
	
	private double getDistanceBetween(Vector v, Vector w)
	{
		double deltaX = v.x - w.x;
		double deltaY = v.y - w.y;
		return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
	}
	
	private double getDistanceBetween(double x1, double y1, double x2, double y2)
	{
		double deltaX = x1 - x2;
		double deltaY = y1 - y2;
		return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
	}
}
