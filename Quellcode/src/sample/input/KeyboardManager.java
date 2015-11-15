package sample.input;

import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import sample.Gamelogic.Countdown;
import sample.Main;
import sample.model.data.Preferences;

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

	private Countdown countdown;

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

		bindLabels();
		setCountdown();
	}

	public void setCountdown() {
		countdown = new Countdown(60);
		setTimelineFinishedListener();
		Label timeField = (Label) m_CurrentScene.lookup("#timeField");
		timeField.textProperty().bind(Bindings.format("%3d", countdown.timeLeftProperty()));
		countdown.start();
	}

	private void setTimelineFinishedListener() {
		countdown.getTimeline().setOnFinished(event -> {
			openPauseAfterFail();
		});
	}

	private void bindLabels() {
		Label levelField = (Label) m_CurrentScene.lookup("#levelField");
		levelField.textProperty().bind(Bindings.format("%3d", Main.getGamelogic().getLevel()));

		Label scoreField = (Label) m_CurrentScene.lookup("#scoreField");
		scoreField.textProperty().bind(Bindings.format("%3d", Main.getGamelogic().getScore()));
	}

	public int getTimeLeft() {
		return countdown.getTimeLeft();
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
				if (event.getCode() == Preferences.getLaunchKey()) {
					m_Main.getGamelogic().startIncreasingPower();
				}
				else if (event.getCode() == Preferences.getMoveLeftKey()) {
					m_Main.getGamelogic().startMovingRadiererToLeft();
				}
				else if (event.getCode() == Preferences.getMoveRightKey()) {
					m_Main.getGamelogic().startMovingRadiererToRight();
				}
				else if (event.getCode() == Preferences.getMoveUpKey()) {
					m_Main.getGamelogic().startGrowingRadierer();
				}
				else if (event.getCode() == Preferences.getMoveDownKey()) {
					m_Main.getGamelogic().startShrinkingRadierer();
				}
			}
		});

		m_CurrentScene.setOnKeyReleased(event -> {
			if (m_PauseMenuPane.isVisible()) {
				if (event.getCode() == Preferences.getPauseMenuKey()) {
					closePauseMenu();
				}
			}
			else {
				if (event.getCode() == Preferences.getPauseMenuKey()) {
					openPauseMenu();
				}
				else if (event.getCode() == Preferences.getLaunchKey()) {
					m_Main.getGamelogic().stopIncreasingPower();
					m_Main.getGamelogic().launchLineal();
				}
				else if (event.getCode() == Preferences.getMoveLeftKey()) {
					m_Main.getGamelogic().stopMovingRadiererToLeft();
				}
				else if (event.getCode() == Preferences.getMoveRightKey()) {
					m_Main.getGamelogic().stopMovingRadiererToRight();
				}
				else if (event.getCode() == Preferences.getMoveUpKey()) {
					m_Main.getGamelogic().stopGrowingRadierer();
				}
				else if (event.getCode() == Preferences.getMoveDownKey()) {
					m_Main.getGamelogic().stopShrinkingRadierer();
				}
				else if (event.getCode() == Preferences.getStartKey()) {
					m_Main.getGamelogic().startRound();
				}
				else if (event.getCode() == Preferences.getRetryKey()) {
					m_Main.getGamelogic().retry();
				}
			}

		});
	}

	public void closePauseMenu() {
		if (countdown.getTimeLeft() > 0) {
			m_PauseMenuPane.setVisible(false);
			countdown.resume();
			if (physicsWasActive) {
				m_Main.getGamelogic().unpause();
			}
		}
	}

	public void openPauseMenu() {
		physicsWasActive = Main.getPhysics().isActive;
		m_Main.getGamelogic().pause();
		countdown.pause();
		m_PauseMenuPane.setVisible(true);
	}

	public void setPauseListener() {
		Button restartButton = (Button) m_CurrentScene.lookup("#restartButton");
		Button mainMenuButton = (Button) m_CurrentScene.lookup("#mainMenuButton");
		Button settingsButton = (Button) m_CurrentScene.lookup("#settingsButton");
		Button resumeButton = (Button) m_CurrentScene.lookup("#resumeButton");
		restartButton.setOnAction(event -> {
			m_PauseMenuPane.setVisible(false);
			Main.getGamelogic().restart();
			setCountdown();
			resumeButton.setDisable(false);
		});
		mainMenuButton.setOnAction(event -> {
			Main.getGamelogic().goToMainMenu();
			resumeButton.setDisable(false);
		});
		settingsButton.setOnAction(event -> {
			Main.setScene("SettingsPause");
			resumeButton.setDisable(false);
		});
		resumeButton.setOnAction(event -> closePauseMenu());
	}

	public void setEndScreenListener() {
		Button highscoreButton = (Button) m_CurrentScene.lookup("#highscoreButton");
		Button nextButton = (Button) m_CurrentScene.lookup("#nextButton");

		highscoreButton.setOnAction(event -> {
			m_endScreenPane.setVisible(false);
			Main.getSavegames().finalizeSavegame();
			Scene high = Main.getScene("HighscoreScreen");
			TabPane tabPane = (TabPane) high.lookup("#tabPane");
			switch (Main.getGamelogic().getDifficulty()) {
				case EASY:
					//Tab tab = (Tab) high.lookup("#easyTab");
					tabPane.getSelectionModel().select(0);
					break;
				case MEDIUM:
					tabPane.getSelectionModel().select(1);
					break;
				case HARD:
					tabPane.getSelectionModel().select(2);
					break;
				case EXTREME:
					tabPane.getSelectionModel().select(3);
					break;
			}
			Main.setScene(high);
		});
		nextButton.setOnAction(event -> {
			m_endScreenPane.setVisible(false);
			m_Main.getGamelogic().nextLevel();
			closePauseMenu();
		});
	}

	public void openEndScreen() {
		countdown.pause();
		m_endScreenPane.setVisible(true);
	}

	public void openPauseAfterFail() {
		disableResume();
		openPauseMenu();
	}

	public void disableResume() {
		Button resumeButton = (Button) m_CurrentScene.lookup("#resumeButton");
		resumeButton.setDisable(true);
	}
}
