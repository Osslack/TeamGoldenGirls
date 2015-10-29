/** @author: Nils Wende
*/

package sample;

import java.util.Observable;

import javafx.scene.Line;
import javafx.scene.Polygon;

/** assuming coordinate system with point of origin in lower left corner */
class Catapult extends Observable
{
	Polygon rubber;
	Line ruler;
	Vector pivotPoint;
	
	private final double rulerLength = 15.0; // in cm
	private final double rubberSidelength = 3.0; // in cm
	private final double minRulerOverhang = rulerLength * 0.1;
	
	Catapult()
	{
		final double tipPositionX = rulerLength - minRulerOverhang + 0.5;
		final double rubberHeight = Math.sqrt(3) * rubberSidelength / 2;
		rubber = new Polygon(); // equilateral triangle
		rubber.getPoints().addAll(new Double[] {tipPositionX                     , rubberHeight,
												tipPositionX + rubberSidelength/2, 0.0,
												tipPositionX - rubberSidelength/2, 0.0}
									);
									
		final double deltaX = Math.sqrt(rulerLength*rulerLength/4 - rubberHeight*rubberHeight);
		final double xStart = tipPositionX - deltaX;
		final double xEnd = tipPositionX + deltaX;
		ruler = new Line(xStart, 0, xEnd, 2*rubberHeight); // tip of rubber is at half of ruler length
		
		pivotPoint = new Vector(tipPositionX, rubberHeight); // set to tip of rubber
		
		this.setChanged();
		this.notifyObservers();
	}
	
	void fire()
	{
		// invoke the physics calculation for trajectory
	}
	
	void moveRulerLeft()
	{
		// move the ruler further to the left
	}
	
	void moveRulerRight()
	{
		// move the ruler further to the right
	}
	
	void enlargeRubber()
	{
		ObservableList<Double> points = rubber.getPoints();
		final double yPos = points.get(1);
		points.set(1, yPos + 0.1); //increases the y-Coordinate of the rubber tip by 0.1 cm
		
		this.setChanged();
		this.notifyObservers();
	}
	
	void shrinkRubber()
	{
		ObservableList<Double> points = rubber.getPoints();
		final double yPos = points.get(1);
		points.set(1, yPos - 0.1); //decreases the y-Coordinate of the rubber tip by 0.1 cm
		
		this.setChanged();
		this.notifyObservers();
	}
}
