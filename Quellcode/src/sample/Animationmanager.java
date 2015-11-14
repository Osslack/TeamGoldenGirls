package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
//import javafx.scene.transform.

/**
 * Created by JJ on 14.11.2015.
 */
public class Animationmanager extends AnimationTimer {
    private Main m_Main;
    private boolean islineallaunched;
    private boolean isLinealresetting;
    private boolean isRadierermoving;
    private boolean isIncreasingPower;
    private boolean isSizingRadierer;
    private int animcnt=0;
    public Animationmanager(Main main){
        m_Main = main;
//        reset();
    }

    public boolean islineallaunched() {
        return islineallaunched;
    }

    public boolean isLinealresetting() {
        return isLinealresetting;
    }

    public boolean isRadierermoving() {
        return isRadierermoving;
    }

    public boolean isIncreasingPower() {
        return isIncreasingPower;
    }

    public void handle(long now){
        animBall();
        handleLineal();
        handleRadierer();
        handlePower();
        handleWindmesser();
    }

    public void startMovingRadierer(){
        isRadierermoving = true;
    }

    public void stopMovingRadierer(){
        isRadierermoving = false;
    }

    public void launchLineal(){
        islineallaunched = true;isLinealresetting = false;
    }

    public void stopLineal(){
        islineallaunched = false;
        isLinealresetting = false;
    }

    public void stopResettingLineal(){

        isLinealresetting = false;
    }

    public void startResettingLineal(){

        isLinealresetting = true;
    }

    private void animBall() {m_Main.getPlayingfield().getBall_Image().setRotate(m_Main.getPlayingfield().getBall_Image().getRotate()+3);}

    private void handleLineal() {
        double deltarot = 0;
        if(islineallaunched){
            deltarot = m_Main.getGamelogic().getLinealpower()/20;
        }
        if(isLinealresetting){
            deltarot = -1;
        }
        m_Main.getPlayingfield().getLineal().setRotate(m_Main.getPlayingfield().getLineal().getRotate()+deltarot);
        m_Main.getPlayingfield().getLineal_Image().setRotate(m_Main.getPlayingfield().getLineal_Image().getRotate()+deltarot);
        if(m_Main.getPhysics().isLinealHittingGround()){
            m_Main.getGamelogic().onLinealHitsGround();
            m_Main.getPlayingfield().getLineal().setRotate(m_Main.getPlayingfield().getLineal().getRotate()-deltarot);
            m_Main.getPlayingfield().getLineal_Image().setRotate(m_Main.getPlayingfield().getLineal_Image().getRotate()-deltarot);
        }
    }

    private void handleRadierer(){
        if(isRadierermoving && !islineallaunched && !isLinealresetting){
            m_Main.getPlayingfield().getRadierer().setStartX(m_Main.getPlayingfield().getRadierer().getStartX()+m_Main.getGamelogic().getRadiererVelocity());
            m_Main.getPlayingfield().getRadierer().setEndX(m_Main.getPlayingfield().getRadierer().getEndX()+m_Main.getGamelogic().getRadiererVelocity());
            m_Main.getPlayingfield().getLineal().getTransforms().add(new Translate(-m_Main.getGamelogic().getRadiererVelocity(),0));
            m_Main.getPlayingfield().getLineal().setLayoutX(m_Main.getPlayingfield().getLineal().getLayoutX()+m_Main.getGamelogic().getRadiererVelocity());
            m_Main.getPlayingfield().getLineal_Image().getTransforms().add(new Translate(-m_Main.getGamelogic().getRadiererVelocity(),0));
            m_Main.getPlayingfield().getLineal_Image().setLayoutX(m_Main.getPlayingfield().getLineal_Image().getLayoutX()+m_Main.getGamelogic().getRadiererVelocity());
            //m_Main.getPlayingfield().getLineal().getTransforms().add(new Rotate(angle, pivotX, pivotY, pivotZ, Rotate.Z_AXIS));
        }
        if(isSizingRadierer && !islineallaunched && !isLinealresetting){
//            m_Main.getPlayingfield().getRadierer().setStartY(m_Main.getPlayingfield().getRadierer().getStartY()+m_Main.getGamelogic().getRadierersizingspeed());
            m_Main.getPlayingfield().getLineal().setStartY(m_Main.getPlayingfield().getLineal().getStartY()+m_Main.getGamelogic().getRadierersizingspeed());
            m_Main.getPlayingfield().getLineal().setEndY(m_Main.getPlayingfield().getLineal().getEndY()+m_Main.getGamelogic().getRadierersizingspeed());
            m_Main.getPlayingfield().getLineal_Image().setY(m_Main.getPlayingfield().getLineal_Image().getY()+m_Main.getGamelogic().getRadierersizingspeed());
            m_Main.getPlayingfield().getLineal_Image().setY(m_Main.getPlayingfield().getLineal_Image().getY()+m_Main.getGamelogic().getRadierersizingspeed());
        }
    }

    private void handleWindmesser(){
        m_Main.getPlayingfield().getWindmesser().setRotate(270+Math.toDegrees(Math.cos(Math.toRadians(++animcnt)))); //nur schön zum anschauen :D
    }

    private void handlePower(){
        if(isIncreasingPower && m_Main.getGamelogic().getLinealpower()<200/3){
            m_Main.getPlayingfield().getPowerbar().setHeight(m_Main.getGamelogic().getLinealpower()*3);
            m_Main.getPlayingfield().getPowerbar().setY(-m_Main.getGamelogic().getLinealpower()*3);
            m_Main.getGamelogic().setLinealpower(m_Main.getGamelogic().getLinealpower()+1);
        }
    }

    public void resetPowerbar(){
        m_Main.getPlayingfield().getPowerbar().setHeight(1);
        m_Main.getPlayingfield().getPowerbar().setY(0);
    }

    public void stopIncreasingPower() {
        isIncreasingPower = false;
    }

    public void startIncreasingPower() {isIncreasingPower = true;}

    public void reset() {
        stopLineal();
        stopMovingRadierer();
        stopIncreasingPower();
        stopSizingRadierer();
        m_Main.getPlayingfield().getLineal().setRotate(0);
        m_Main.getPlayingfield().getLineal_Image().setRotate(0);
        resetPowerbar();
    }

    public void stopSizingRadierer() {
        isSizingRadierer = false;
    }

    public void startSizingRadierer() {
        isSizingRadierer = true;
    }
}
