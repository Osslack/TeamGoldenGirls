/*used*/
package sample.gamelogic;


import javafx.animation.*;
import javafx.beans.property.*;
import javafx.util.Duration;

/**
 * @author Nils
 * This class is used to count the time from value x till 0 when the game is running
 */
public class Countdown {
	private final ReadOnlyIntegerWrapper timeLeft;
	private final ReadOnlyDoubleWrapper  timeLeftDouble;
	private final Timeline               timeline;

	public Timeline getTimeline() {
		return timeline;
	}

	public ReadOnlyIntegerProperty timeLeftProperty() {
		return timeLeft.getReadOnlyProperty();
	}

	public int getTimeLeft() {
		return timeLeft.get();
	}
	//Creates a countdown from time to 0
	public Countdown(final int time) {
		timeLeft = new ReadOnlyIntegerWrapper(time);
		timeLeftDouble = new ReadOnlyDoubleWrapper(time);

		//Does the counting to 0
		timeline = new Timeline(
				//start frame
				new KeyFrame(
						Duration.ZERO,
						new KeyValue(timeLeftDouble, time)
				),
				//end frame
				new KeyFrame(
						Duration.seconds(time),
						new KeyValue(timeLeftDouble, 0)
				)
		);

		timeLeftDouble.addListener(o -> {
			timeLeft.set((int) Math.ceil(timeLeftDouble.get()));
		});
	}

	public void start() {
		timeline.playFromStart();
	}

	public void pause() {
		timeline.pause();
	}

	public void resume() {
		timeline.play();
	}
}
