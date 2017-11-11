public interface HangmanView {

	/**
	 * Correct letter is guessed by the player.
	 * Adds correct letter to its correct location(s) in the word
	 */
	void correctGuessNotification(char letter);
	
	/**
	 * Incorrect letter is guessed.
	 * Hangman graphics draw the next body part and the letter is added to the incorrect bank.
	 */
	void incorrectGuessNotification(char letter);

	/**
	 * Player loses the game. Losses increase by one.
	 */
	void guesserLosesNofitication(int wins, int losses);

	/**
	 * Player wins the game. Wins increase by one.
	 */
	void guesserWinsNotification(int wins, int losses);
	
	/**
	 * A game of Hangman is started. 
	 * A word has been chosen and the corresponding number of dashes are added to the screen. 
	 */
	void startGameNotification(String word);
}