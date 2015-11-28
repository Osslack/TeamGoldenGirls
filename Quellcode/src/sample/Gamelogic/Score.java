/*used*/
package sample.Gamelogic;

/**
 * @author Nils
 */
public class Score {

	public static int getScore(final int timeLeft, final int ballsUsed) {
		int score = timeLeft - ballsUsed;
		return score > 0 ? score : 0;
	}
}
