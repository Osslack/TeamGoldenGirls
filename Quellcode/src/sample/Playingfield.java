/**
 * @author Simon
 */
package sample;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Playingfield {
	public static final double scene_width = 800;
	public static final double scene_height = 600;
	private javafx.scene.shape.Circle    m_Ball;
	private javafx.scene.image.ImageView m_Ball_Image;
	private javafx.scene.shape.Line      m_Lineal;
	private javafx.scene.shape.Line      m_Radierer;
	private javafx.scene.shape.Line      m_Ground;
	private Main m_Main;

	public Playingfield(Main main){
		m_Main = main;
		m_Ball = (javafx.scene.shape.Circle) m_Main.getScene("MainGame").lookup("#circle");
		m_Ball_Image = (javafx.scene.image.ImageView) m_Main.getScene("MainGame").lookup("#paperball");
		m_Lineal = (javafx.scene.shape.Line) m_Main.getScene("MainGame").lookup("#lineal");
		m_Radierer = (javafx.scene.shape.Line) m_Main.getScene("MainGame").lookup("#radierer");
		m_Ground = (javafx.scene.shape.Line) m_Main.getScene("MainGame").lookup("#ground");
	}

	public static double getScene_width() {
		return scene_width;
	}

	public static double getScene_height() {
		return scene_height;
	}

	public javafx.scene.shape.Circle getBall(){
		return m_Ball;
	}

	public javafx.scene.image.ImageView getBall_Image(){
		return m_Ball_Image;
	}

	public Line getLineal() {
		return m_Lineal;
	}

	public Line getRadierer() {
		return m_Radierer;
	}

	public Line getGround() {
		return m_Ground;
	}
//	public ArrayList<Shape> mObstacles;
//	public Ball              mBall;
//	public Shape            mTarget;
//	public Powerbar          mPowerbar;
//	public Scoreboard        mScoreboard;
//	public Windmeter         mWindmeter;
//	public Catapult          mCatapult;
//	public double            mAirDensity;
//
//	public Playingfield() {
//		mObstacles = new ArrayList<Shape>();
//		mObstacles.add(new Rectangle());
//		mBall = new Ball();
//		mTarget = new TargetRectangle();
//		mPowerbar = new Powerbar();
//		mScoreboard = new Scoreboard();
//		mWindmeter = new Windmeter();
//		mCatapult = new Catapult();
//		mAirDensity = 0.0;
//	}
//
//	public Playingfield(ArrayList<Shape> obstacles, Ball ball, Shape target, Powerbar powerbar, Scoreboard scoreboard, Windmeter windmeter, Catapult catapult, double airdensity) {
//		mObstacles = obstacles;
//		mBall = ball;
//		mTarget = target;
//		mPowerbar = powerbar;
//		mScoreboard = scoreboard;
//		mWindmeter = windmeter;
//		mCatapult = catapult;
//		mAirDensity = airdensity;
//	}
//
//	@Override
//	public String toString() {
//		return "Playingfield ( Obstacles " + mObstacles.toString() + " | " + mBall + " | " + mTarget + " | " + mPowerbar + " )";
//	}
//
//	public static void main(String[] args) {
//		System.out.println(new Playingfield());
//	}
}