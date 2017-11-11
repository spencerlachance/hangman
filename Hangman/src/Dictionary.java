import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

/**
 * The Dictionary class models getting a random word from a list of words.
 * @author Mark Jones
 */
public class Dictionary {

	/** Create a dictionary.  Default constructor uses a default (internal) dictionary. */
	public Dictionary() {
		dictionary = new String[] {
				"algorithm",
				"application",
				"bus",
				"byte",
				"computer",
				"database",
				"hard disk",
				"keyboard",
				"memory",
				"modem",
				"monitor",
				"mouse",
				"network",
				"operating system",
				"program",
				"software",
				"hardware"
		};
		numWords = dictionary.length;
	}

	/**
	 * Create a dictionary from a file containing a list of words.
	 * @param filename   filename of the file containing the dictionary
	 */
	public Dictionary(String filename) {
		readDictionary(filename);
	}

	/**
	 * Reads a dictionary from filename.
	 * @param filename   the dictionary filename containing a list of hangman words or phrases
	 */
	public void readDictionary(String filename) {
		try {
			File file = new File(filename);
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String word = sc.nextLine();
				addWord(word);
			}			
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found:" + e);
		}
	}

	/**
	 * Gets the current number of words in the dictionary.
	 * @return    the number of words in the dictionary
	 */
	public int size() {
		return numWords;
	}

	/** Prints the entire dictionary. */
	public void printDictionary() {
		printDictionary(size());
	}

	/**
	 * Print the first or last entries in the dictionary.
	 *   if numEntries >= 0 then print the first numEntries
	 *   if numEntries < 0  then print the last numEntries
	 * @param numEntries    number of entries to print
	 */
	public void printDictionary(int numEntries) {
		int begin, end;
		if (numEntries >= 0) {
			begin = 0;
			end = Math.min(size(), numEntries);
		} else {  // numEntries < 0
			begin = Math.max(0, numEntries + size());
			end = size();
		}
		for (int i=begin; i<end; i++) {
			System.out.println(i + ".\t" + dictionary[i]);
		}
	}

	/**
	 * Prints front words from the front of the dictionary and
	 *        back words from the back of the dictionary.
	 * @param front
	 * @param back
	 */
	public void printDictionary(int front, int back) {
		front = Math.min(front,size());
		back = Math.min(back, size()-front);
		printDictionary(front);
		System.out.println(". . .");
		printDictionary(-back);
	}

	/**
	 * Appends a word to the end of the dictionary.
	 * @param word
	 */
	public void addWord(String word) {
		if (numWords < MAX_WORDS) {
			dictionary[numWords] = word;
			numWords++;
		}
	}

	/**
	 * Returns the ith word in the dictionary.
	 * @param i
	 * @return the ith word
	 */
	public String getWord(int i) {
		if (dictionary[i].length() < 11)
			return dictionary[i];
		else {
			return getWord(i+1);
		}
	}

	/**
	 * Returns a random word in the dictionary.
	 * @return a random word
	 */
	public String getRandomWord() { 
		return getWord(randomNumber.nextInt(numWords));
	}

	/* class constants */
	private static final int MAX_WORDS = 263533;
	private static Random randomNumber = new Random();

	/* instance variables */
	private String[] dictionary = new String[MAX_WORDS];
	private int numWords = 0;

}