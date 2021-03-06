/**
 * @author Simon
 * JENDRIK
 * This class contains the elements that can be found in every level
 * Radierer,Lineal,Ball,Powerbar,Sidebar,Windmesser
 */
/*used*/

package sample.model;

import javafx.scene.shape.Line;
import sample.Main;

public class Playingfield {
	private final double field_width  = 800;
	private final double field_height = 600;
	//The different elements that can be found in every level: Radierer,Lineal,Ball,Powerbar,Sidebar,Windmesser
	private javafx.scene.shape.Circle    m_Ball;
	private javafx.scene.image.ImageView m_Ball_Image;
	private javafx.scene.image.ImageView m_Lineal_Image;
	private javafx.scene.shape.Line      m_Lineal;
	private javafx.scene.shape.Line      m_Radierer;
	private javafx.scene.shape.Line      m_Ground;
	private javafx.scene.shape.Polygon   m_Windmesser;
	private javafx.scene.shape.Rectangle m_Powerbar;
	private javafx.scene.shape.Rectangle m_Sidebar;
	private javafx.scene.image.ImageView m_Radierer_Image;

	public Playingfield() {
		restore();
	}

	//Loads the elements from the current scene
	public void restore() {
		m_Ball = (javafx.scene.shape.Circle) Main.getScene(Main.getGamelogic().getCurrentSceneName()).lookup("#circle");

		m_Ball_Image = (javafx.scene.image.ImageView) Main.getScene(Main.getGamelogic().getCurrentSceneName()).lookup("#paperball");
		m_Lineal = (javafx.scene.shape.Line) Main.getScene(Main.getGamelogic().getCurrentSceneName()).lookup("#lineal");
		m_Radierer = (javafx.scene.shape.Line) Main.getScene(Main.getGamelogic().getCurrentSceneName()).lookup("#radierer");
		m_Ground = (javafx.scene.shape.Line) Main.getScene(Main.getGamelogic().getCurrentSceneName()).lookup("#ground");
		m_Windmesser = (javafx.scene.shape.Polygon) Main.getScene(Main.getGamelogic().getCurrentSceneName()).lookup("#windmesser");
		m_Powerbar = (javafx.scene.shape.Rectangle) Main.getScene(Main.getGamelogic().getCurrentSceneName()).lookup("#powerbar");
		m_Sidebar = (javafx.scene.shape.Rectangle) Main.getScene(Main.getGamelogic().getCurrentSceneName()).lookup("#sidebar");
		m_Lineal_Image = (javafx.scene.image.ImageView) Main.getScene(Main.getGamelogic().getCurrentSceneName()).lookup("#linealimage");
		m_Radierer_Image = (javafx.scene.image.ImageView) Main.getScene(Main.getGamelogic().getCurrentSceneName()).lookup("#radiererimage");
	}


	public double getScene_width() {
		return field_width - m_Sidebar.getWidth();
	}

	public double getScene_height() {
		return field_height;
	}

	public javafx.scene.shape.Circle getBall() {
		return m_Ball;
	}

	public javafx.scene.image.ImageView getRadierer_Image() {
		return m_Radierer_Image;
	}

	public javafx.scene.image.ImageView getLineal_Image() {
		return m_Lineal_Image;
	}

	public javafx.scene.image.ImageView getBall_Image() {
		return m_Ball_Image;
	}

	public javafx.scene.shape.Rectangle getPowerbar() {
		return m_Powerbar;
	}

	public javafx.scene.shape.Polygon getWindmesser() {
		return m_Windmesser;
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
//This part is only left in for the sake of completeness.This is the old class Playingfield which is no longer in use
//But apparently SOMEONE was too lazy,to just create a new class and instead reused the old class


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