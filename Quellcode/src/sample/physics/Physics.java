package sample.physics;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import sample.Main;
import sample.model.Vector2D;

/**
 * Created by JJ on 10.11.2015.
 * Worked on by Simon
 */
public class Physics extends AnimationTimer {
	private long lastNano;
	private Main m_Main;
	private Vector2D m_Velocity     = new Vector2D(0, 10);
	private Vector2D m_lastPosition = new Vector2D();
	private Vector2D m_Position     = new Vector2D(100, 100);
	private DragTrajectory            m_DragTrajectory;
	private javafx.scene.shape.Circle m_Circle;
	private javafx.scene.image.ImageView m_Paperball;
	private int animCounter = 0;
	private Collision m_Collision;


	public Physics(Main main) {
		m_Main = main;
		m_Circle = (javafx.scene.shape.Circle) m_Main.getScene("Game").lookup("#circle");
		m_Paperball = (javafx.scene.image.ImageView) m_Main.getScene("Game").lookup("#paperball");
		m_DragTrajectory = new DragTrajectory(5, (m_Circle.getRadius()) * (5 / 100), 1.2);
		m_Collision = new Collision();
	}

	@Override
	public void start() {
		super.start();
		lastNano = System.nanoTime();
		m_Position.mX = m_Circle.getCenterX();
		m_Position.mY = m_Circle.getCenterY();
		m_Paperball.setLayoutX(m_Circle.getLayoutX());
		m_Paperball.setLayoutY(m_Circle.getLayoutY());
	}

	public void handle(long nowNano) {
		double deltaSecs = ((double) (nowNano - lastNano)) / 1000000000.0;
		lastNano = nowNano;

		simBall(deltaSecs);

		animBall();

		checkShapeCollisions();

		checkBounds();

	}

	private void checkBounds(){
		boolean outsidehorizontalbounds = m_Collision.isOutsideHorizontalBounds(m_Circle.getCenterX()-m_Circle.getRadius()+m_Circle.getLayoutX(),800-m_Circle.getRadius());
		boolean outsideverticalbounds = m_Collision.isOutsideVerticalBounds(m_Circle.getCenterY()-m_Circle.getRadius()+m_Circle.getLayoutY(),600-m_Circle.getRadius());
		if(outsidehorizontalbounds){m_Velocity.mX *= -1;}
		if(outsideverticalbounds){m_Velocity.mY *= -1;}
		if(outsidehorizontalbounds || outsideverticalbounds){resetBall();}
	}

	private void animBall(){
		m_Paperball.setRotate((++animCounter) );
	}

	private void simBall(double deltaSecs) {
		m_lastPosition.mX = m_Position.mX;
		m_lastPosition.mY = m_Position.mY;
		m_DragTrajectory.simNext(m_Velocity, m_Position, deltaSecs * 4);
		updateBallPos();
	}

	private void resetBall() {
		m_Position.mX = m_lastPosition.mX;
		m_Position.mY = m_lastPosition.mY;
		updateBallPos();
	}

	private void updateBallPos(){
		m_Circle.setCenterX(m_Position.mX);
		m_Circle.setCenterY(m_Position.mY);
		m_Paperball.setX(m_Position.mX-m_Circle.getRadius());
		m_Paperball.setY(m_Position.mY-m_Circle.getRadius());
	}

	private void checkShapeCollisions() {
		for (Node child : m_Main.getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable()) {
			if (child instanceof javafx.scene.shape.Line) {
				javafx.scene.shape.Line line = (javafx.scene.shape.Line) child;
				if (m_Collision.isColliding(m_Circle,line)) {
					m_Main.getSoundmanager().playSound1();
					resetBall();
					Vector2D normal = new Vector2D();
					double yx = line.getLocalToSceneTransform().getMyx();
					double yy = line.getLocalToSceneTransform().getMyy();
					double angle = Math.atan2(yx, yy);
					normal.rotate(angle);
					m_Collision.getPostCollisionVelocity(m_Velocity,0.5,normal);
				}
			}
		}
	}
}
