package sample.Gamelogic;

import sample.Difficulty;
import sample.Main;
import sample.model.data.UserData;

/**
 * Created by JJ on 14.11.2015.
 *
 * @author Nils
 */
public class Gamelogic {

	private Main       m_Main;
	private Difficulty m_difficulty;
	private UserData   user;

	private String m_currentSceneName    = "BaseGame";
	private double m_linealpower         = 0;
	private double m_radierervelocity    = 2;
	private double m_radierersizingspeed = 1;
	private int m_level;
	private int m_score;
	private int     windDirectionInDegrees = 0;
	private boolean isBallKicked           = false;
	private int ballsUsed;

	public Gamelogic(Main main) {
		m_Main = main;
		reset();
	}

	public void reset() {
		m_score = 0;
		ballsUsed = 0;
	}

	public int getScore() {
		return m_score;
	}

	public void setScore(int s) {
		this.m_score = s;
	}

	public double getRadiererSizingSpeed() {
		return m_radierersizingspeed;
	}

	public void setLinealPower(double linealpower) {
		m_linealpower = linealpower;
	}

	public Difficulty getDifficulty() {
		return m_difficulty;
	}

	public void setDifficulty(Difficulty d) {
		m_difficulty = d;
	}

	public void startGrowingRadierer() {
		m_Main.getAnimationmanager().startSizingRadierer();
		m_radierersizingspeed = -Math.abs(m_radierersizingspeed);
	}

	public void startShrinkingRadierer() {
		m_Main.getAnimationmanager().startSizingRadierer();
		m_radierersizingspeed = Math.abs(m_radierersizingspeed);
	}

	public void stopShrinkingRadierer() {
		m_Main.getAnimationmanager().stopSizingRadierer();
	}

	public void stopGrowingRadierer() {
		m_Main.getAnimationmanager().stopSizingRadierer();
	}

	public int getLevel() {
		return m_level;
	}

	public void setLevel(String scenename, Difficulty d) {
		if (Main.setScene(scenename)) {
			m_difficulty = d;
			m_currentSceneName = scenename;
			m_level = Integer.parseInt(m_currentSceneName.substring(m_currentSceneName.lastIndexOf('l') + 1));
			m_Main.getPlayingfield().restore();
			m_Main.getPlayingfield().getBall().setLayoutX(0);
			m_Main.getPlayingfield().getBall().setLayoutY(0);
			m_Main.getPlayingfield().getBall_Image().setLayoutX(m_Main.getPlayingfield().getBall().getLayoutX());
			m_Main.getPlayingfield().getBall_Image().setLayoutY(m_Main.getPlayingfield().getBall().getLayoutY());
			Main.getKeyboardmanager().applyControlsToCurrentScene();
			m_Main.getAnimationmanager().start();
			Main.getPhysics().setWindfactor(((float) Difficulty.toInteger(d)) / ((float) Difficulty.toInteger(Difficulty.EXTREME)));
			windDirectionInDegrees = (int) (Math.random() * 360);
			m_Main.getPlayingfield().getWindmesser().setRotate(windDirectionInDegrees);
			retry();
		}
	}

	public int getWinddirection() {
		return windDirectionInDegrees;
	}

	//TODO last level intercept in menu
	public void nextLevel() {
		ballsUsed = 0;
		String nextLevel = "Level" + (m_level + 1);
		setLevel(nextLevel, m_difficulty);
	}

	public void restart() {
		ballsUsed = 0;
		setLevel("Level" + m_level, m_difficulty);
	}

	public void retry() {
		Main.getPhysics().setBallPosition(100, 200);
		Main.getPhysics().setBallVelocity(0, 10);
		m_Main.getAnimationmanager().reset();
		isBallKicked = false;
		m_linealpower = 0;
		++ballsUsed;
		pause();
	}

	public boolean getIsBallKicked() {
		return isBallKicked;
	}

	public void onLinealHit() {
		isBallKicked = true;
	}

	public void onGoalHit() { //Enter
		pause();
		Main.getKeyboardmanager().openEndScreen();
		m_score += Score.getScore(Main.getKeyboardmanager().getTimeLeft(), ballsUsed);
		Main.getSavegames().cacheSavegame(m_level+1, m_score, user.name, user.form, m_difficulty);
	}

	public void onDeathHit() { //Enter
		//onGoalHit();
		//m_Main.getKeyboardmanager().openPauseAfterFail();
	}

	public void startRound() { //Enter
		unpause();
		m_Main.getAnimationmanager().startResettingLineal();
	}

	public double getLinealPower() {
		return m_linealpower;
	}

	public void unpause() {
		Main.getPhysics().start();
	}

	public void pause() { //escape
		Main.getPhysics().stop();
	}

	public void goToMainMenu() {
		pause();
		m_Main.getAnimationmanager().stop();
		Main.setScene("MainMenu");
	}

	public String getCurrentSceneName() {
		return m_currentSceneName;
	}

	public void launchLineal() { //space loslassen
		m_Main.getAnimationmanager().launchLineal();
	}

	public void startMovingRadiererToLeft() { //a drücken
		m_Main.getAnimationmanager().startMovingRadierer();
		m_radierervelocity = -Math.abs(m_radierervelocity);
	}

	public void startMovingRadiererToRight() { //d drücken
		m_Main.getAnimationmanager().startMovingRadierer();
		m_radierervelocity = Math.abs(m_radierervelocity);
	}

	public void onLinealHitsGround() {
		m_Main.getAnimationmanager().stopLineal();
	}

	public void stopMovingRadiererToLeft() {// a loslassen
		m_Main.getAnimationmanager().stopMovingRadierer();
	}

	public void stopMovingRadiererToRight() {// d loslassen
		m_Main.getAnimationmanager().stopMovingRadierer();
	}

	public double getRadiererVelocity() {
		return m_radierervelocity;
	}

	public void startIncreasingPower() { //space drücken
		m_Main.getAnimationmanager().startIncreasingPower();
	}

	public void stopIncreasingPower() { //space loslassen
		m_Main.getAnimationmanager().stopIncreasingPower();
	}

	public void setUser(final UserData user) {
		this.user = user;
	}
}

