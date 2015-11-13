package sample.physics;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import sample.Main;
import sample.model.Vector2D;
import sample.sounds.Soundmanager;

/**
 * Created by JJ on 10.11.2015.
 * Worked on by Simon
 */
public class Physics extends AnimationTimer {
	private long lastNano;
	private Main m_Main;
	private Vector2D m_Velocity     = new Vector2D(Math.random()*20-10, Math.random()*40);
	private Vector2D m_lastPosition = new Vector2D();
	private Vector2D m_Position     = new Vector2D(100, 100);
	private DragTrajectory            m_DragTrajectory;
	private javafx.scene.shape.Circle m_Circle;
	private javafx.scene.image.ImageView m_Paperball;
	private javafx.scene.shape.Line m_Lineal;
	private int animCounter = 0;
	private Collision m_Collision;
	private double m_LinealVelocity = 2.0;
	private double m_Dampening = 0.7;
	private int m_Screenwidth = 800;
	private int m_Screenheight = 600;
	private String m_hitlastframe = "";



	public Physics(Main main) {
		m_Main = main;
		m_Circle = (javafx.scene.shape.Circle) m_Main.getScene("MainGame").lookup("#circle");
		m_Paperball = (javafx.scene.image.ImageView) m_Main.getScene("MainGame").lookup("#paperball");
		m_Lineal = (javafx.scene.shape.Line) m_Main.getScene("MainGame").lookup("#lineal");
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
		m_Paperball.setX(m_Circle.getCenterX());
		m_Paperball.setY(m_Circle.getCenterY());
	}

	public void handle(long nowNano) {
		double deltaSecs = ((double) (nowNano - lastNano)) / 1000000000.0;
		lastNano = nowNano;

		simBall(deltaSecs);

		checkShapeCollisions();

		checkBounds();

		doAnimations();

		if(!isMoving()){
			m_Main.getSoundmanager().playSound(Soundmanager.CLICK_SOUND);
		}else{
			System.out.println((m_Circle.getCenterY()-m_Circle.getRadius()));
		}
	}

	private void checkBounds(){
		boolean outsidehorizontalbounds = m_Collision.isOutsideHorizontalBounds(m_Circle.getCenterX()+m_Circle.getLayoutX(),m_Screenwidth);
		boolean outsideverticalbounds = m_Collision.isOutsideVerticalBounds(m_Circle.getCenterY()+m_Circle.getLayoutY(),m_Screenheight);
		Vector2D normal = new Vector2D();
		if(outsidehorizontalbounds){
			normal.mX = 1;
			normal.mY = 0;
		}
		if(outsideverticalbounds){
			normal.mX = 0;
			normal.mY = 1;
		}
		if(outsidehorizontalbounds || outsideverticalbounds){
			resetBall();
			m_Collision.getPostCollisionVelocity(m_Velocity,m_Dampening,normal);
		}
	}

	private void doAnimations(){
		++animCounter;
		animBall();
		animLineal();
	}

	private void animBall(){
		m_Paperball.setRotate(animCounter);
	}

	private void animLineal(){
		m_Lineal.setRotate(animCounter*m_LinealVelocity);
	}

	private void simBall(double deltaSecs) {
		m_lastPosition.mX = m_Position.mX;
		m_lastPosition.mY = m_Position.mY;
		m_DragTrajectory.simNext(m_Velocity, m_Position, deltaSecs * 5);
		updateBallPos();
	}

	private void resetBall() {
		m_Position.mX = m_lastPosition.mX;
		m_Position.mY = m_lastPosition.mY;
		updateBallPos();
	}

	private boolean isMoving(){
		return m_Velocity.length() >= 15.5 || (m_Circle.getCenterY() - m_Circle.getRadius() < 378.0); //Check Speed and Position, if lying on floor and speed below threshhold->false
		}


	private void updateBallPos(){
		m_Circle.setCenterX(m_Position.mX);
		m_Circle.setCenterY(m_Position.mY);
		m_Paperball.setX(m_Position.mX-m_Circle.getRadius());
		m_Paperball.setY(m_Position.mY-m_Circle.getRadius());
	}

	private void checkShapeCollisions() {
		boolean collisionhappened = false;
		for (Node child : m_Main.getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable()) {
			if (child instanceof javafx.scene.shape.Line) {
				javafx.scene.shape.Line line = (javafx.scene.shape.Line) child;
				if (m_Collision.isColliding(m_Circle,line)) {
					Vector2D normal = new Vector2D();
					double yx = line.getLocalToSceneTransform().getMyx();
					double yy = line.getLocalToSceneTransform().getMyy();
					double angle = Math.atan2(yx, yy);
					normal.rotate(angle);
					if(m_hitlastframe == line.getId()){
						Vector2D n2 = normal.scalarMultiplication(10);
						m_Position.add2(n2);
						m_Collision.getPostCollisionVelocity(m_Velocity,1,normal);
					}else{
						m_Collision.getPostCollisionVelocity(m_Velocity,m_Dampening,normal);
						if(line.getId()==m_Lineal.getId()){
							Vector2D linestart = new Vector2D();
							Vector2D lineend = new Vector2D();
							linestart.mX = line.getStartX();
							linestart.mY = line.getStartY();
							lineend.mX = line.getEndX();
							lineend.mY = line.getEndY();
							Vector2D linedelta = lineend.subtract(linestart);
							linedelta.scalarMultiplication2(0.5);
							Vector2D linecenter = linestart.add(linedelta);
							double lineradius = linedelta.length();
							double distance = linecenter.getDistanceTo(m_Position);
							normal.scalarMultiplication2(-200*m_LinealVelocity*(lineradius/distance));
							m_Velocity.add2(normal);
						}
						m_Main.getSoundmanager().playRandSound();
						resetBall();
					}
					m_hitlastframe = line.getId();
					collisionhappened = true;
				}
			}
		}
		if(collisionhappened==false){
			m_hitlastframe = "";
		}
	}
}
