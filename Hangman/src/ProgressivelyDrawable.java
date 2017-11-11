/**
 * The graphical interface required by a HangmanView.
 * @author Mark Jones
 * 
 *
 */
public interface ProgressivelyDrawable {
	void drawNextPart();   // draw the next part of the hangman
	void reset();          // reset the hangman (no parts drawn)
	int getMaxParts();     // return the maximum number of parts supported by the graphics
}