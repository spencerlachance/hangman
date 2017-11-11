import java.io.FileNotFoundException;

/**
 * Abstractly models a Hangman game.
 * @author Harrison Farrugia and Spencer LaChance
 *
 */
public class HangmanModel {

	// instance variables
	HangmanView hv;
	int wins = 0, losses = 0, guesses;
	Dictionary dict;
	String word, initialWord;

	/**
	 * Create a HangmanModel (given a HangmanView for notifications).
	 * @param HangmanView
	 */
	public HangmanModel(HangmanView HangmanView) {
		hv = HangmanView;
	}
	
	/**
	 * Starts a Hangman Game
	 * @throws FileNotFoundException 
	 */
	public void startGame() throws FileNotFoundException {
		dict = new Dictionary("yawl.txt");
		initialWord = dict.getRandomWord();
		word = initialWord;
		guesses = 10;
		hv.startGameNotification(word);
	}
	
	/**
	 * Player guesses a letter.
	 * Checks if letter is correct and if the game is over.
	 * Gives the corresponding notification(s).
	 * @param chosen
	 */
	public void guess(String chosen) {
		while (word.contains(chosen)) { //letter is in the word
			word = word.replaceFirst(chosen, "");
			hv.correctGuessNotification(chosen.charAt(0));
			if (word.length() == 0) { //checks to see if player has won
				wins++;
				hv.guesserWinsNotification(wins, losses);
			}
		}
		if (!initialWord.contains(chosen)) { //letter is not in the word
			hv.incorrectGuessNotification(chosen.charAt(0));
			guesses--;
			if (guesses == 0) { //checks to see if the player has lost
				losses++;
				hv.guesserLosesNofitication(wins, losses);
			}
		}
	}
}