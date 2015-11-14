package sample.input;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import sample.Main;
import sample.model.Keyboard;

/**
 * JENDRIK
 *
 * @author Nils
 */
public class KeyboardManager {
	private Main  m_Main;
	private Scene m_CurrentScene;
	private Pane  m_PauseMenuPane;
	private Pane  m_endScreenPane;

	private boolean physicsWasActive;

	public KeyboardManager(Main main) {
		m_Main = main;
	}

	public void applyControlsToCurrentScene() {
		unsetIngameListener();
		unsetPauseListener();
		unsetEndScreenListener();
		m_CurrentScene = m_Main.getScene(m_Main.getGamelogic().getCurrentSceneName());
		m_PauseMenuPane = (Pane) (m_CurrentScene.lookup("#pauseMenuPane"));
		m_endScreenPane = (Pane) (m_CurrentScene.lookup("#endScreenPane"));
		setIngameListener();
		setPauseListener();
		setEndScreenListener();
	}

	public void unsetIngameListener() {
		if (m_CurrentScene != null) {
			m_CurrentScene.setOnKeyPressed(event -> {
			});
			m_CurrentScene.setOnKeyReleased(event -> {
			});
		}
	}

	public void unsetPauseListener() {
		if (m_CurrentScene != null) {
			Button mainMenuButton = (Button) m_CurrentScene.lookup("#mainMenuButton");
			Button settingsButton = (Button) m_CurrentScene.lookup("#settingsButton");
			Button resumeButton = (Button) m_CurrentScene.lookup("#resumeButton");
			mainMenuButton.setOnAction(event -> {
			});
			settingsButton.setOnAction(event -> {
			});
			resumeButton.setOnAction(event -> {
			});
		}
	}

	public void unsetEndScreenListener() {
		if (m_CurrentScene != null) {
			Button highscoreButton = (Button) m_CurrentScene.lookup("#highscoreButton");
			Button nextButton = (Button) m_CurrentScene.lookup("#nextButton");
			highscoreButton.setOnAction(event -> {
			});
			nextButton.setOnAction(event -> {
			});
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
			if (m_PauseMenuPane.isVisible()) {
				if (event.getCode() == Keyboard.getPauseMenuKey()) {
					closePauseMenu();
				}
			}
			else {
				if (event.getCode() == Keyboard.getPauseMenuKey()) {
					openPauseMenu();
				}
				else if (event.getCode() == Keyboard.getLaunchKey()) {
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

	public void closePauseMenu() {
		m_PauseMenuPane.setVisible(false);
		if (physicsWasActive) {
			m_Main.getGamelogic().UnPause();
		}
	}

	public void openPauseMenu() {
		physicsWasActive = Main.getPhysics().isActive;
		m_Main.getGamelogic().Pause();
		m_PauseMenuPane.setVisible(true);
	}

	public void setPauseListener() {
		Button tryagainButton = (Button) m_CurrentScene.lookup("#tryagainButton");
		Button mainMenuButton = (Button) m_CurrentScene.lookup("#mainMenuButton");
		Button settingsButton = (Button) m_CurrentScene.lookup("#settingsButton");
		Button resumeButton = (Button) m_CurrentScene.lookup("#resumeButton");
		tryagainButton.setOnAction(event -> {
			m_PauseMenuPane.setVisible(false);
			m_Main.getGamelogic().newRound();
			resumeButton.setDisable(false);
		});
		mainMenuButton.setOnAction(event -> {
			m_Main.getGamelogic().GotoMainMenu();
			resumeButton.setDisable(false);
		});
		settingsButton.setOnAction(event -> {
			m_Main.setScene("SettingsPause");
			resumeButton.setDisable(false);
		});
		resumeButton.setOnAction(event -> closePauseMenu());
	}

	public void setEndScreenListener() {
		Button highscoreButton = (Button) m_CurrentScene.lookup("#highscoreButton");
		Button nextButton = (Button) m_CurrentScene.lookup("#nextButton");

		highscoreButton.setOnAction(event -> {
			m_PauseMenuPane.setVisible(false);
			m_Main.setScene("HighscoreScreen");
		});
		nextButton.setOnAction(event -> {
			m_PauseMenuPane.setVisible(false);
			m_Main.getGamelogic().nextLevel();
		});
	}

	public void openEndScreen() {
		m_endScreenPane.setVisible(true);
	}

	public void disableResume() {
		Button resumeButton = (Button) m_CurrentScene.lookup("#resumeButton");
		resumeButton.setDisable(true);
	}
}
