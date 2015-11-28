/*used*/
package sample.input;

import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import sample.Difficulty;
import sample.Main;
import sample.Gamelogic.Countdown;
import sample.model.data.Preferences;

/**
 * JENDRIK
 *
 * @author Nils
 */
public class KeyboardManager {
	private Scene m_CurrentScene;
	private Pane  m_PauseMenuPane;
	private Pane  m_endScreenPane;

	private Countdown countdown;

	private boolean physicsWasActive;

	public void applyControlsToCurrentScene() {
		unsetIngameListener();
		unsetPauseListener();
		unsetEndScreenListener();
		m_CurrentScene = Main.getScene(Main.getGamelogic().getCurrentSceneName());
		m_PauseMenuPane = (Pane) (m_CurrentScene.lookup("#pauseMenuPane"));
		m_endScreenPane = (Pane) (m_CurrentScene.lookup("#endScreenPane"));
		setIngameListener();
		setPauseListener();
		setEndScreenListener();

		bindLabels();
		setCountdown();
	}

	private void setCountdown() {
		if (countdown != null) {
			countdown.getTimeline().stop();
		}
		countdown = new Countdown((int) (20 + 160 * ((float) (Difficulty.toInteger(Difficulty.EASY)) / (float) (Difficulty.toInteger(Main.getGamelogic().getDifficulty())))));
		setTimelineFinishedListener();
		Label timeField = (Label) m_CurrentScene.lookup("#timeField");
		timeField.textProperty().bind(Bindings.format("%3d", countdown.timeLeftProperty()));
		countdown.start();
	}

	private void setTimelineFinishedListener() {
		countdown.getTimeline().setOnFinished(event -> openPauseAfterFail());
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

	private void unsetIngameListener() {
		if (m_CurrentScene != null) {
			m_CurrentScene.setOnKeyPressed(event -> {
			});
			m_CurrentScene.setOnKeyReleased(event -> {
			});
		}
	}

	private void unsetPauseListener() {
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

	private void unsetEndScreenListener() {
		if (m_CurrentScene != null) {
			Button highscoreButton = (Button) m_CurrentScene.lookup("#highscoreButton");
			Button nextButton = (Button) m_CurrentScene.lookup("#nextButton");
			highscoreButton.setOnAction(event -> {
			});
			nextButton.setOnAction(event -> {
			});
		}
	}

	private void setIngameListener() {
		m_CurrentScene.setOnKeyPressed(event -> {
			if (!m_PauseMenuPane.isVisible()) {
				if (event.getCode() == Preferences.getLaunchKey()) {
					Main.getGamelogic().startIncreasingPower();
				}
				else if (event.getCode() == Preferences.getMoveLeftKey()) {
					Main.getGamelogic().startMovingRadiererToLeft();
				}
				else if (event.getCode() == Preferences.getMoveRightKey()) {
					Main.getGamelogic().startMovingRadiererToRight();
				}
				else if (event.getCode() == Preferences.getMoveUpKey()) {
//					Main.getGamelogic().startGrowingRadierer();
				}
				else if (event.getCode() == Preferences.getMoveDownKey()) {
//					Main.getGamelogic().startShrinkingRadierer();
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
					Main.getGamelogic().stopIncreasingPower();
					Main.getGamelogic().launchLineal();
				}
				else if (event.getCode() == Preferences.getMoveLeftKey()) {
					Main.getGamelogic().stopMovingRadiererToLeft();
				}
				else if (event.getCode() == Preferences.getMoveRightKey()) {
					Main.getGamelogic().stopMovingRadiererToRight();
				}
				else if (event.getCode() == Preferences.getMoveUpKey()) {
//					Main.getGamelogic().stopGrowingRadierer();
				}
				else if (event.getCode() == Preferences.getMoveDownKey()) {
//					Main.getGamelogic().stopShrinkingRadierer();
				}
				else if (event.getCode() == Preferences.getStartKey()) {
					Main.getGamelogic().startRound();
				}
				else if (event.getCode() == Preferences.getRetryKey()) {
					Main.getGamelogic().retry();
				}
			}

		});
	}

	public void closePauseMenu() {
		if (countdown != null) {
			if (countdown.getTimeLeft() > 0) {
				m_PauseMenuPane.setVisible(false);
				countdown.resume();
				if (physicsWasActive) {
					Main.getGamelogic().unpause();
				}
			}
		}
	}

	private void openPauseMenu() {
		physicsWasActive = Main.getPhysics().isActive;
		Main.getGamelogic().pause();
		countdown.pause();
		m_PauseMenuPane.setVisible(true);
	}

	private void setPauseListener() {
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

	private void setEndScreenListener() {
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
			Main.getGamelogic().nextLevel();
			closePauseMenu();
		});
	}

	public void openEndScreen() {
		countdown.pause();
		m_endScreenPane.setVisible(true);
	}

	private void openPauseAfterFail() {
		disableResume();
		openPauseMenu();
	}

	private void disableResume() {
		Button resumeButton = (Button) m_CurrentScene.lookup("#resumeButton");
		resumeButton.setDisable(true);
	}
}
