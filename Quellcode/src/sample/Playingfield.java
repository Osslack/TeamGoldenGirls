/**
 * @author Simon
 */
package sample;

import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Playingfield {
	public static final double scene_width = 800;
	public static final double scene_height = 600;
	public ArrayList<Shape> mObstacles;
	public Ball              mBall;
	public Shape            mTarget;
	public Powerbar          mPowerbar;
	public Scoreboard        mScoreboard;
	public Windmeter         mWindmeter;
	public Catapult          mCatapult;
	public double            mAirDensity;

	public Playingfield() {
		mObstacles = new ArrayList<Shape>();
		mObstacles.add(new Rectangle());
		mBall = new Ball();
		mTarget = new TargetRectangle();
		mPowerbar = new Powerbar();
		mScoreboard = new Scoreboard();
		mWindmeter = new Windmeter();
		mCatapult = new Catapult();
		mAirDensity = 0.0;
	}

	public Playingfield(ArrayList<Shape> obstacles, Ball ball, Shape target, Powerbar powerbar, Scoreboard scoreboard, Windmeter windmeter, Catapult catapult, double airdensity) {
		mObstacles = obstacles;
		mBall = ball;
		mTarget = target;
		mPowerbar = powerbar;
		mScoreboard = scoreboard;
		mWindmeter = windmeter;
		mCatapult = catapult;
		mAirDensity = airdensity;
	}

	@Override
	public String toString() {
		return "Playingfield ( Obstacles " + mObstacles.toString() + " | " + mBall + " | " + mTarget + " | " + mPowerbar + " )";
	}

	public static void main(String[] args) {
		System.out.println(new Playingfield());
	}
}