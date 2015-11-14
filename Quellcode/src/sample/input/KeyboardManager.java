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

	private boolean physicsWasActive;

	public KeyboardManager(Main main) {
		m_Main = main;
	}

	public void applyControlsToCurrentScene() {
		unsetIngameListener();
		unsetPauseListener();
		m_CurrentScene = m_Main.getScene(m_Main.getGamelogic().getCurrentSceneName());
		m_PauseMenuPane = (Pane) (m_CurrentScene.lookup("#pauseMenuPane"));
		setIngameListener();
		setPauseListener();
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
		m_PauseMenuPane.setVisible(true);
		physicsWasActive = Main.getPhysics().isActive;
		m_Main.getGamelogic().Pause();
	}

	public void unlockNextLevelButton() {
		(m_CurrentScene.lookup("#nextlevelButton")).setDisable(false);
	}

	public void lockNextLevelButton() {
		(m_CurrentScene.lookup("#nextlevelButton")).setDisable(true);
	}

	public void setPauseListener() {
		Button mainMenuButton = (Button) m_CurrentScene.lookup("#mainMenuButton");
		Button settingsButton = (Button) m_CurrentScene.lookup("#settingsButton");
		Button resumeButton = (Button) m_CurrentScene.lookup("#resumeButton");
		Button nextlevelButton = (Button) m_CurrentScene.lookup("#nextlevelButton");
		Button tryagainButton = (Button) m_CurrentScene.lookup("#tryagainButton");
		tryagainButton.setOnAction(event -> {
			m_Main.getGamelogic().newRound();
			closePauseMenu();
		});
		nextlevelButton.setOnAction(event -> {
			m_Main.getGamelogic().nextLevel();
			closePauseMenu();
		});
		mainMenuButton.setOnAction(event -> m_Main.getGamelogic().GotoMainMenu());
		settingsButton.setOnAction(event -> m_Main.setScene("SettingsPause"));
		resumeButton.setOnAction(event -> closePauseMenu());
	}
}
