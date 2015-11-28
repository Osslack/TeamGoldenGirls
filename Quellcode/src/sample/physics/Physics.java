package sample.physics;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import sample.Main;
import sample.model.Vector2D;

/**
 * Created by JJ on 10.11.2015.
 * Worked on by Simon
 */
public class Physics extends AnimationTimer {
	private       long lastNano;
	private final Main m_Main;
	private final Vector2D m_Velocity     = new Vector2D();
	private final Vector2D m_lastPosition = new Vector2D();
	private final Vector2D m_Position     = new Vector2D();
	private final DragTrajectory m_DragTrajectory;
	private final Collision      m_Collision;
	private final double m_Dampening    = 0.7;
	private       String m_hitlastframe = "";
	private       double windfactor     = 0;
	public boolean isActive;

	public Physics(Main main) {
		m_Main = main;
		m_DragTrajectory = new DragTrajectory(5, (10) * (5 / 100), 1.2);
		m_Collision = new Collision();
	}

	@Override
	public void start() {
		super.start();
		lastNano = System.nanoTime();
		updateBallPos();
		isActive = true;
	}

	@Override
	public void stop() {
		super.stop();
		isActive = false;
	}

	public void setWindfactor(double d){
		windfactor = d;
	}

	public void setBallPosition(double x, double y){
		m_Position.mX = x;
		m_Position.mY = y;
		updateBallPos();
	}

	public void setBallVelocity(double x, double y){
		m_Velocity.mX = x;
		m_Velocity.mY = y;
	}

	public void handle(long nowNano) {
		double deltaSecs = ((double) (nowNano - lastNano)) / 1000000000.0;
		lastNano = nowNano;
		simBall(deltaSecs);
		simWind();
		checkShapeCollisions();
		checkBounds();
		if (isStopped()) {
			//was just for testing m_Main.getSoundmanager().playSound(Soundmanager.CLICK_SOUND);
		}
	}

	private void simWind(){
		if (Main.getGamelogic().getIsBallKicked()) {
			double rotation = Math.toRadians(Main.getGamelogic().getWinddirection());
			setBallVelocity(m_Velocity.mX + (Math.sin(rotation) * windfactor*0.7), m_Velocity.mY - (Math.cos(rotation) * windfactor*0.3));
		}
	}

	private void checkBounds() {
		boolean outsidehorizontalbounds = m_Collision.isOutsideHorizontalBounds(m_Main.getPlayingfield().getBall().getCenterX() + m_Main.getPlayingfield().getBall().getLayoutX(), m_Main.getPlayingfield().getScene_width());
		boolean outsideverticalbounds = m_Collision.isOutsideVerticalBounds(m_Main.getPlayingfield().getBall().getCenterY() + m_Main.getPlayingfield().getBall().getLayoutY(), m_Main.getPlayingfield().getScene_height());
		Vector2D normal = new Vector2D();
		if (outsidehorizontalbounds) {
			normal.mX = 1;
			normal.mY = 0;
		}
		if (outsideverticalbounds) {
			normal.mX = 0;
			normal.mY = 1;
		}
		if (outsidehorizontalbounds || outsideverticalbounds) {
			if (!isStopped()) {
				Main.getSoundmanager().playRandSound(1, 10);
			}
			resetBall();
			m_Collision.getPostCollisionVelocity(m_Velocity, m_Dampening, normal);
		}
	}

	private void simBall(double deltaSecs) {
		m_lastPosition.mX = m_Position.mX;
		m_lastPosition.mY = m_Position.mY;
		m_DragTrajectory.simNext(m_Velocity, m_Position, deltaSecs * 8);
		updateBallPos();
	}

	private void resetBall() {
		m_Position.mX = m_lastPosition.mX;
		m_Position.mY = m_lastPosition.mY;
		updateBallPos();
	}

	private boolean isStopped() {
		double distanceToLastPosition = m_Position.getDistanceTo(m_lastPosition);
		if (distanceToLastPosition < 0.1) {
//			System.out.println("Distance:   " + distanceToLastPosition + "   Speed :" + m_Velocity.length() + "   |    Center Y:" + (m_Circle.getCenterY() - m_Circle.getRadius()));
		}
		return (((distanceToLastPosition < 1.2) || (m_Velocity.length() < 15.5)) && (m_Main.getPlayingfield().getBall().getCenterY() - m_Main.getPlayingfield().getBall().getRadius()) >= 377.0); //Check Speed and Position, if lying on floor and speed below threshhold->false
	}


	private void updateBallPos() {
		m_Main.getPlayingfield().getBall().setCenterX(m_Position.mX);
		m_Main.getPlayingfield().getBall().setCenterY(m_Position.mY);
		m_Main.getPlayingfield().getBall_Image().setX(m_Position.mX - m_Main.getPlayingfield().getBall().getRadius());
		m_Main.getPlayingfield().getBall_Image().setY(m_Position.mY - m_Main.getPlayingfield().getBall().getRadius());
	}

	public boolean isLinealHittingGround(){
		return(m_Collision.isColliding(m_Main.getPlayingfield().getLineal(), m_Main.getPlayingfield().getGround()));
	}

	private void checkShapeCollisions() {
		boolean collisionhappened = false;
		String id = "";

		for (Node child : Main.getScene(Main.getGamelogic().getCurrentSceneName()).getRoot().getChildrenUnmodifiable()) {
			id = child.getId();
			// collision with death and goal objects are processed
			if(id!=null){
				if (id.equals("death")){
					if (m_Collision.isColliding(m_Main.getPlayingfield().getBall(), (Rectangle) child)) {
						Main.getGamelogic().onDeathHit();
					}
				}
				else if (id.equals("goal")){
					if (m_Collision.isColliding(m_Main.getPlayingfield().getBall(), (Rectangle) child)) {
						Main.getGamelogic().onGoalHit();
					}
				}
			}
			//supported are collisions with lines only
			if (child instanceof javafx.scene.shape.Line) {
				javafx.scene.shape.Line line = (javafx.scene.shape.Line) child;
				//use colliding isColliding of Collision-Class that uses the intersects method of javafx-shapes
				if (m_Collision.isColliding(m_Main.getPlayingfield().getBall(), line)) {
					Vector2D normal = new Vector2D();
					//get the angle of the line
					double yx = line.getLocalToSceneTransform().getMyx();
					double yy = line.getLocalToSceneTransform().getMyy();
					double angle = Math.atan2(yx, yy);
					//rotate our normal vector so that it resembles the normal of the line
					normal.rotate(angle);
					//in case we hit the same object as last time (this case occurs if the ball gets stuck)
					if(line.getId()!=null) {
						if (m_hitlastframe.equals(line.getId())) {
							//we move the ball away from the line
							Vector2D n2 = normal.scalarMultiplication(-10);
							m_Position.add2(n2);
							m_Collision.getPostCollisionVelocity(m_Velocity, 1, normal);
						}
					}
					// new line is hit
					else {
						// post-collision velocity is calculated
						m_Collision.getPostCollisionVelocity(m_Velocity, m_Dampening, normal);
						// in case the ball just hit "Lineal"
						if(line.getId()!=null) {
							if (line.getId().equals(m_Main.getPlayingfield().getLineal().getId()) && m_Main.getAnimationmanager().islineallaunched()) {
								//call the game logic
								m_Main.getGamelogic().onLinealHit();
								//the next part is for calculating the velocity that is applied to the ball according to the distance, the ball is away from the center of "Lineal"
								//first we get the start of the line
								Vector2D linestart = new Vector2D(line.getStartX() + line.getLayoutX(), line.getStartY() + line.getLayoutY());
								//its rotation
								Point3D rotaxis = line.getRotationAxis();
								//we calculate the center of the line by subtracting the radius
								Vector2D linecenter = new Vector2D(rotaxis.getX() + line.getLayoutX(), rotaxis.getY() + line.getLayoutY());
								double lineradius = linestart.subtract(linecenter).length();
								//we calculate the distance between the line center and ball
								Vector2D centertocircle = m_Position.subtract(linecenter);

								//the next part might seem redundant
								//we calculate the angle to the ball two times
								//this is necessary since the angle calculated only goes from 0-180 but we have to know on which side of the line we are

								// the angle between the normal and the center of the ball is calculated
								double deltaangle = Math.toDegrees(normal.getAngleTo(centertocircle));
								double distance = centertocircle.length();
								//this flips the normal of the line by 180°
								Vector2D linevector = new Vector2D();
								linevector.rotate((Math.PI / 2.0) + angle);
								//again the angle to the ballcenter is calculated
								double deltaangle2 = Math.toDegrees(linevector.getAngleTo(centertocircle));

								boolean kickball = false;
								//the next part is for checking if the ball is actually kicked or just bounces
								//the angle-checking checks if the ball to be in front of "Lineal" and also in its direction of rotation
								if (deltaangle > 90 && deltaangle2 < 90) {
									kickball = true;
								}
								if (deltaangle < 90 && deltaangle2 > 90) {
									kickball = true;
								}
								// if the ball is about to be kicked, we take the relation of lineradius and distance into the calculation of the new velocity of the ball
								if (kickball == true) {
									normal.scalarMultiplication2(-1 * m_Main.getGamelogic().getLinealPower() * (distance / lineradius));
									m_Velocity.add2(normal);
								}
							}
						}
						Main.getSoundmanager().playRandSound(1, 10);
						resetBall();
					}
					m_hitlastframe = line.getId();
					collisionhappened = true;
				}
			}
		}
		if (!collisionhappened) {
			m_hitlastframe = "";
		}
	}
}
