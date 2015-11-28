/*used*/
package sample.gamelogic;


/**
 * @author Nils
 */
class Score {

	public static int getScore(final int timeLeft, final int ballsUsed) {
		int score = timeLeft - ballsUsed;
		return score > 0 ? score : 0;
	}
}
