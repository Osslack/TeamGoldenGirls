package sample.physics;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.model.Vector2D;
import sample.Main;
import sample.physics.DragTrajectory;
/**
 * Created by JJ on 10.11.2015.
 */
public class Physics extends AnimationTimer {
    private long lastNano;
    private Main m_Main;
    private Vector2D m_Velocity = new Vector2D(20,-10);
    private Vector2D m_lastPosition = new Vector2D();
    private Vector2D m_Position = new Vector2D(100,100);
    private DragTrajectory m_DragTrajectory;
    private javafx.scene.shape.Circle m_Ball;
    private Shape m_Intersection;
    private int animCounter = 0;
    private int randomized = (int)(Math.random()*100);

    public Physics(Main main){
        m_Main = main;
        m_Ball = (javafx.scene.shape.Circle)m_Main.getScene("MainGame").lookup("#c_circle");
        m_DragTrajectory = new DragTrajectory(5,(m_Ball.getRadius())*(5/100),1.2);
    }

    @Override
    public void start(){
        super.start();
        lastNano = System.nanoTime();
        m_Position.mX = m_Ball.getCenterX();
        m_Position.mY = m_Ball.getCenterY();
    }

    public void handle(long nowNano) {
        double deltaSecs = ((double)(nowNano - lastNano))/1000000000.0;
        lastNano = nowNano;

        simBall(deltaSecs);

        animateRects();

        checkShapeCollisions();

        checkBounds();

    }

    private void checkBounds(){
        double x = m_Ball.getCenterX()+m_Ball.getLayoutX();
        double y = m_Ball.getCenterY()+m_Ball.getLayoutY();
        double r = m_Ball.getRadius();
        if ((x < r) || (x > (800-r))){
            m_Velocity.mX *= -1;
            resetBall();
        }
        if ((y < r) || (y > (600-r))){
            m_Velocity.mY *= -1;
            resetBall();
        }
    }

    private void animateRects(){
        int randomizing = 0;
        for (    Node child : m_Main.getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable()) {
            randomizing+=randomized;
            if(child instanceof javafx.scene.shape.Rectangle) {
//                javafx.scene.shape.Rectangle rect = (javafx.scene.shape.Rectangle)m_Main.getPrimaryStage().getScene().lookup("#s_rectangle");
                javafx.scene.shape.Rectangle rect = (javafx.scene.shape.Rectangle) child;
                rect.setRotate( ((++animCounter)/50)+randomizing);
            }
        }
    }

    private void simBall(double deltaSecs){
        m_lastPosition.mX = m_Position.mX;
        m_lastPosition.mY = m_Position.mY;
        m_DragTrajectory.simNext(m_Velocity,m_Position,deltaSecs*4);
        m_Ball.setCenterX(m_Position.mX);
        m_Ball.setCenterY(m_Position.mY);
    }

    private void resetBall(){
        m_Position.mX = m_lastPosition.mX;
        m_Position.mY = m_lastPosition.mY;
        m_Ball.setCenterX(m_Position.mX);
        m_Ball.setCenterY(m_Position.mY);
    }

    private void checkShapeCollisions(){

        for (    Node child : m_Main.getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable()) {
            if(child instanceof javafx.scene.shape.Rectangle) {
//                javafx.scene.shape.Rectangle rect = (javafx.scene.shape.Rectangle)m_Main.getPrimaryStage().getScene().lookup("#s_rectangle");
                javafx.scene.shape.Rectangle rect = (javafx.scene.shape.Rectangle)child;
                m_Intersection = Shape.intersect(m_Ball,rect);
                if (m_Intersection.getBoundsInLocal().getWidth()!=-1) { //collision
                    resetBall();

                    Vector2D normal = new Vector2D();

                    double yx = rect.getLocalToSceneTransform().getMyx();
                    double yy = rect.getLocalToSceneTransform().getMyy();
                    double angle = Math.atan2(yx, yy);

                    angle = Math.toDegrees(angle);
                    angle = angle - 90;
                    angle = angle < 0 ? angle + 360 : angle;
                    if (angle > 360) {
                        angle = angle - 360;
                    }
                    angle = Math.toRadians(angle);

                    normal.rotate(angle);
                    double dotproduct = m_Velocity.scalarProduct(normal);
                    m_Velocity.mX = m_Velocity.mX - 2 * dotproduct * normal.mX;
                    m_Velocity.mY = m_Velocity.mY - 2 * dotproduct * normal.mY;
                }
            }
        }
    }
}
