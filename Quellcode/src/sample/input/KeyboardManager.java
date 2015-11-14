package sample.input;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import sample.Main;
import sample.engine.*;
import sample.model.Keyboard;
import sample.sounds.Soundmanager;

import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import java.util.Vector;

public class KeyboardManager {
	private Main m_Main;
	private Scene m_CurrentScene;
	private Pane m_PauseMenuPane;

	public KeyboardManager(Main main){
		m_Main = main;
	}

	public void applyControlsToCurrentScene(){
		unsetIngameListener();
		unsetPauseListener();
		m_CurrentScene = m_Main.getScene(m_Main.getGamelogic().getCurrentSceneName());
		m_PauseMenuPane =  (Pane)(m_CurrentScene.lookup("#pauseMenuPane"));
		setIngameListener();
		setPauseListener();
	}

	public void unsetIngameListener() {
		if(m_CurrentScene!=null){
			m_CurrentScene.setOnKeyPressed(event -> {});
			m_CurrentScene.setOnKeyReleased(event -> {});
		}
	}

	public void unsetPauseListener() {
		if(m_CurrentScene!=null){
			Button mainMenuButton = (Button) m_CurrentScene.lookup("#mainMenuButton");
			Button settingsButton = (Button) m_CurrentScene.lookup("#settingsButton");
			Button resumeButton = (Button) m_CurrentScene.lookup("#resumeButton");
			mainMenuButton.setOnAction(event -> {});
			settingsButton.setOnAction(event -> {});
			resumeButton.setOnAction(event -> {});
		}
	}

	public void setIngameListener() {
		m_CurrentScene.setOnKeyPressed(event -> {
			if (!m_PauseMenuPane.isVisible()) {
				if (event.getCode() == Keyboard.getLaunchKey()) {
					m_Main.getGamelogic().startIncreasingPower();
				}
				else if (event.getCode() == Keyboard.getMoveLeftKey()) {
					m_Main.getGamelogic().startMovingRadiererToLeft();
				}
				else if (event.getCode() == Keyboard.getMoveRightKey()) {
					m_Main.getGamelogic().startMovingRadiererToRight();
				}
				else if (event.getCode() == Keyboard.getMoveUpKey()) {
					m_Main.getGamelogic().startGrowingRadierer();
				}
				else if (event.getCode() == Keyboard.getMoveDownKey()) {
					m_Main.getGamelogic().startShrinkingRadierer();
				}
			}
		});

		m_CurrentScene.setOnKeyReleased(event -> {
			if (event.getCode() == Keyboard.getPausemenuKey()) {
				handleEscape();
			}
			if (!m_PauseMenuPane.isVisible()) {
				if (event.getCode() == Keyboard.getLaunchKey()) {
					m_Main.getGamelogic().stopIncreasingPower();
					m_Main.getGamelogic().launchLineal();
				}
				else if (event.getCode() == Keyboard.getMoveLeftKey()) {
					m_Main.getGamelogic().stopMovingRadiererToLeft();
				}
				else if (event.getCode() == Keyboard.getMoveRightKey()) {
					m_Main.getGamelogic().stopMovingRadiererToRight();
				}
				else if (event.getCode() == Keyboard.getMoveUpKey()) {
					m_Main.getGamelogic().stopGrowingRadierer();
				}
				else if (event.getCode() == Keyboard.getMoveDownKey()) {
					m_Main.getGamelogic().stopShrinkingRadierer();
				}
				else if (event.getCode() == Keyboard.getStartRoundKey()) {
					m_Main.getGamelogic().startRound();
				}
				else if (event.getCode() == Keyboard.getNewRoundKey()) {
					m_Main.getGamelogic().newRound();
				}
			}
		});
	}

	public void handleEscape() {
		if(!m_PauseMenuPane.isVisible()) {
			m_PauseMenuPane.setVisible(true);
			m_Main.getGamelogic().Pause();
		}else{
			m_PauseMenuPane.setVisible(false);
			m_Main.getGamelogic().UnPause();
		}
	}

	public void unlockNextLevelButton(){
		(m_CurrentScene.lookup("#nextlevelButton")).setDisable(false);
	}

	public void lockNextLevelButton(){
		(m_CurrentScene.lookup("#nextlevelButton")).setDisable(true);
	}

	public void setPauseListener() {
		Button mainMenuButton = (Button) m_CurrentScene.lookup("#mainMenuButton");
		Button settingsButton = (Button) m_CurrentScene.lookup("#settingsButton");
		Button resumeButton = (Button) m_CurrentScene.lookup("#resumeButton");
		Button nextlevelButton = (Button) m_CurrentScene.lookup("#nextlevelButton");
		Button tryagainButton = (Button) m_CurrentScene.lookup("#tryagainButton");
		tryagainButton.setOnAction(event -> {m_Main.getGamelogic().newRound();handleEscape();});
		nextlevelButton.setOnAction(event -> {m_Main.getGamelogic().nextLevel();handleEscape();});
		mainMenuButton.setOnAction(event -> m_Main.getGamelogic().GotoMainMenu());
		settingsButton.setOnAction(event -> m_Main.setScene("SettingsPause"));
		resumeButton.setOnAction(event -> {
//			m_Main.getSoundmanager().playSound(Soundmanager.CLICK_SOUND);
			m_PauseMenuPane.setVisible(false);
			m_Main.getGamelogic().UnPause();
		});
	}

//	private int m_activeControlSet;
//	private Vector<ControlSet> m_Controls;
//	private Engine m_engine;
//
//	public KeyboardManager(Engine engine){
//		m_engine = engine;
//		m_Controls = new Vector<ControlSet>();
//		m_activeControlSet = -1;
//		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
//            @Override
//            public boolean dispatchKeyEvent(KeyEvent keyevent) {
//                switch (keyevent.getID()) {
//	                case KeyEvent.KEY_PRESSED:
//	            		if(m_activeControlSet != -1){
//	            			int id = m_Controls.get(m_activeControlSet).onKeyPressed(keyevent.getKeyCode());
//	            			if(id != -1){
//	            				evalKeyboardAction(id);
//	            			}
//	            		}
//	                    break;
//	                case KeyEvent.KEY_RELEASED:
//	            		if(m_activeControlSet != -1){
//	            			int id = m_Controls.get(m_activeControlSet).onKeyReleased(keyevent.getKeyCode());
//	            			if(id != -1){
//	            				evalKeyboardAction(id);
//	            			}
//	            		}
//	                    break;
//                }
//                return false;
//            }
//        });
//	}
//
//	public void evalKeyboardAction(int id)
//	{
//		switch(id){
//			case KeyboardActions.ESCAPE:
//				m_engine.shutdown();
//		}
//	}
//
//	public int addControlSet(ControlSet controlset){
//		m_Controls.add(controlset);
//		int id = m_Controls.size()-1;
//		if(m_activeControlSet==-1){
//			m_activeControlSet = id;
//		}
//		return id;
//	}
//
//	public void setActiveControlSet(int i){
//		if(i<m_Controls.size()){
//			m_activeControlSet = i;
//		}
//	}
}
