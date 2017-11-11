import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import acm.graphics.*;
import acm.program.GraphicsProgram;

/**
 * Hangman game.
 * @author Harrison Farrugia and Spencer LaChance
 *
 */
@SuppressWarnings("serial")
public class Hangman extends GraphicsProgram implements HangmanView {
	// class constants
	private static final int INITIAL_WIDTH = 700;
	private static final int INITIAL_HEIGHT = 500;
	//instance variables
	JLabel narration;
	String answer, letterguessed;
	GRect letterbank, scorebox;
	ArrayList<GLine> blanks = new ArrayList<GLine>();
	GLabel correctletter, score;
	private HangmanModel hm;
	private HangmanGraphics hg;
	int wordlength, wrongguessnum, correctguessnum;
	private boolean gameOver;

	/**
	 * Entry point when running Hangman as an application.
	 * @param args
	 */
	public static void main(String[] args) {
		(new Hangman()).start();
	}

	/**
	 * Constructor for Hangman when running as an application.
	 */
	public Hangman(){
	}

	/** 
	 * Create the Model (with this for callbacks).
	 * Set up the GUI.
	 */
	public void init() {
		setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		hm = new HangmanModel(this);
		narration = new JLabel("Starting new round (Guess a letter)");
		add(narration, NORTH);
		letterbank = new GRect(7 * getWidth()/12, 3 * getHeight()/5, getWidth()/3, getHeight()/4);
		add(letterbank);
		add(new GLabel("Incorrect Letters"), letterbank.getX() + letterbank.getWidth() / 3, letterbank.getY() + 
				letterbank.getHeight() / 5);
		scorebox = new GRect(5 * getWidth() / 8, getHeight() / 20, getWidth() / 4, getHeight() / 8);
		add(scorebox);
		score = new GLabel("WINS: " + hm.wins + "   LOSSES: " + hm.losses);
		add(score, scorebox.getX() + scorebox.getWidth() / 6, scorebox.getY() + scorebox.getHeight() / 2);
		hg = new HangmanGraphics(2 * getWidth() / 3, 2 * getHeight() / 3);
		hg.reset();
		add(new JButton("New Game"), SOUTH);
		try {
			hm.startGame();
		} catch (FileNotFoundException e) {
			// I don't know why Eclipse made me make these try-catches
			System.out.println("Dictionary file not found:" + e);
		}
		addKeyListeners();
		addActionListeners();
	}

	/**
	 * Run a new round of Hangman
	 */
	public void resetGame() {
		removeAll();
		letterbank = new GRect(7 * getWidth()/12, 3 * getHeight()/5, getWidth()/3, getHeight()/4);
		add(letterbank);
		add(new GLabel("Incorrect Letters"), letterbank.getX() + letterbank.getWidth() / 3, letterbank.getY() + 
				letterbank.getHeight() / 5);
		narration.setText("Starting new round (Guess a letter)");
		scorebox = new GRect(5 * getWidth() / 8, getHeight() / 20, getWidth() / 4, getHeight() / 8);
		add(scorebox);
		score = new GLabel("WINS: " + hm.wins + "   LOSSES: " + hm.losses);
		add(score, scorebox.getX() + scorebox.getWidth() / 6, scorebox.getY() + scorebox.getHeight() / 2);
		hg = new HangmanGraphics(2 * getWidth() / 3, 2 * getHeight() / 3);
		hg.reset();
		try {
			hm.startGame();
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found:" + e);
		}
		gameOver = false;
		wrongguessnum = 0;
	}

	/** 
	 * Handler for key actions.
	 */
	public void keyTyped(KeyEvent e) {
		if(!gameOver) {
			letterguessed = e.getKeyChar() + "";
			hm.guess(letterguessed);
		}
	}

	/** 
	 * Handler for button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New Game"))
			resetGame();
	}

	/*************  Notifications  ***************/

	/**
	 * Correct letter is guessed by the player.
	 * Adds correct letter to its correct location(s) in the word
	 */
	@Override
	public void correctGuessNotification(char letter) {
		narration.setText("Letter is in the word!");
		int index = answer.indexOf(letter);
		answer = answer.replaceFirst("" + letter, "" + Character.toUpperCase(letter));
		correctletter = new GLabel("" + Character.toUpperCase(letter));
		add(correctletter, blanks.get(index).getX() + correctletter.getWidth() / 2, blanks.get(index).getY() - 
				correctletter.getHeight());
	}

	/**
	 * Incorrect letter is guessed.
	 * Hangman graphics draw the next body part and the letter is added to the incorrect bank.
	 */
	@Override
	public void incorrectGuessNotification(char letter) {
		wrongguessnum++;
		narration.setText("Letter is not in the word!");
		add(new GLabel("" + letter), letterbank.getX() + wrongguessnum * letterbank.getWidth() / 12, 
				letterbank.getY() + letterbank.getHeight()/ 2);
		hg.drawNextPart();
		add(hg, getWidth() / 4, getHeight() / 2);
	}

	/**
	 * Player loses the game. Losses increase by one.
	 */
	@Override
	public void guesserLosesNofitication(int wins, int losses) {
		narration.setText("You lose! The word was " + answer.toLowerCase());
		gameOver = true;
		score.setLabel("WINS: " + wins + "   LOSSES: " + losses);
		add(score, scorebox.getX() + scorebox.getWidth() / 6, scorebox.getY() + scorebox.getHeight() / 2);
	}

	/**
	 * Player wins the game. Wins increase by one.
	 */
	@Override
	public void guesserWinsNotification(int wins, int losses) {
		narration.setText("You win, good work!");
		gameOver = true;
		score.setLabel("WINS: " + wins + "   LOSSES: " + losses);
		add(score, scorebox.getX() + scorebox.getWidth() / 6, scorebox.getY() + scorebox.getHeight() / 2);
	}

	/**
	 * A game of Hangman is started. 
	 * A word has been chosen and the corresponding number of dashes are added to the screen. 
	 */
	@Override
	public void startGameNotification(String word) {
		GLine line;
		answer = word;
		blanks = new ArrayList<GLine>();
		for(int i = 0; i < answer.length(); i++) {
			line = new GLine(getWidth() / 2 + i * getWidth() / 20, getHeight () / 2, 
					getWidth() / 2 + i * getWidth() / 20 + getWidth() / 30, getHeight() / 2);
			blanks.add(line);
			add(line);
		}
		narration.setText("Starting new game. Guess a letter!");
	}
}




