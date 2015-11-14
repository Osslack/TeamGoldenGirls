package sample.Gamelogic;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import sample.Difficulty;
import sample.Main;
import sample.model.Keyboard;
import sample.sounds.Soundmanager;

/**
 * Created by JJ on 14.11.2015.
 */
public class Gamelogic{
    private Main m_Main;
    public static  Difficulty m_difficulty;
    private String m_currentSceneName ="BaseGame";
    private double m_linealpower = 0;
    private double m_radierervelocity = 2;
    private double m_radierersizingspeed = 1;

    public Gamelogic(Main main){
        m_Main = main;
    }

    public double getRadierersizingspeed() {
        return m_radierersizingspeed;
    }

    public void setLinealpower(double linealpower) {
        m_linealpower = linealpower;
    }

    public Difficulty getDifficulty() {
        return m_difficulty;
    }

    public void setDifficulty(Difficulty d) {
        m_difficulty = d;
    }

    public void startGrowingRadierer(){
        m_Main.getAnimationmanager().startSizingRadierer();
        m_radierersizingspeed = -Math.abs(m_radierersizingspeed);
    }

    public void startShrinkingRadierer(){
        m_Main.getAnimationmanager().startSizingRadierer();
        m_radierersizingspeed = Math.abs(m_radierersizingspeed);
    }

    public void stopShrinkingRadierer(){
        m_Main.getAnimationmanager().stopSizingRadierer();
    }

    public void stopGrowingRadierer(){
        m_Main.getAnimationmanager().stopSizingRadierer();
    }

    public void setLevel(String scenename, Difficulty d){
        m_difficulty = d;
        m_currentSceneName = scenename;
        m_Main.setScene(scenename);
        m_Main.getPlayingfield().restore();
        m_Main.getPlayingfield().getBall().setLayoutX(0);
        m_Main.getPlayingfield().getBall().setLayoutY(0);
        m_Main.getPlayingfield().getBall_Image().setLayoutX(m_Main.getPlayingfield().getBall().getLayoutX());
        m_Main.getPlayingfield().getBall_Image().setLayoutY(m_Main.getPlayingfield().getBall().getLayoutY());
        m_Main.getKeyboardmanager().applyControlsToCurrentScene();
        m_Main.getAnimationmanager().start();
        newRound();
    }

    public void newRound(){
        m_Main.getPhysics().setBallPosition(100,200);
        m_Main.getPhysics().setBallVelocity(0,10);
        m_Main.getAnimationmanager().reset();
        m_linealpower = 0;
        Pause();
    }

    public void startRound(){ //Enter
        UnPause();
        m_Main.getAnimationmanager().startResettingLineal();
    }

    public double getLinealpower(){
        return m_linealpower;
    }

    public void UnPause(){
        m_Main.getPhysics().start();
    }

    public void Pause(){ //escape
        m_Main.getPhysics().stop();
    }

    public void GotoMainMenu(){
        Pause();
        m_Main.getAnimationmanager().stop();
        m_Main.setScene("MainMenu");
    }

    public String getCurrentSceneName(){
        return m_currentSceneName;
    }

//    public static void setDifficulty(Difficulty diff) {
//        chosenDifficulty = diff;
//    }

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

    public void onLinealHitsGround(){
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
}

