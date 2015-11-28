/*used*/
package sample.Gamelogic;

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.util.Duration;

/**
 * @author Nils
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

	public Countdown(final int time) {
		timeLeft = new ReadOnlyIntegerWrapper(time);
		timeLeftDouble = new ReadOnlyDoubleWrapper(time);

		timeline = new Timeline(
				new KeyFrame(
						Duration.ZERO,
						new KeyValue(timeLeftDouble, time)
				),
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
