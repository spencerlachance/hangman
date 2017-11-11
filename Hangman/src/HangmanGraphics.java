/** HangmanGraphics.java:  This file contains the graphics for building a hangman.
 * @author Dr. Mark Jones
 */

import java.awt.Color;
import acm.graphics.*;

@SuppressWarnings("serial")
public class HangmanGraphics extends GCompound implements ProgressivelyDrawable {

	/** Make the graphics for a hangman.
	 * @param wid  the width of the allocated area for the hangman graphics
	 * @param ht   the height of the allocated area for the hangman graphics
	 */
	public HangmanGraphics(double wid, double ht) {
		super();
		width = wid;
		height = ht;
		if (width / height < WIDTH_TO_HEIGHT_RATIO)
			height = width / WIDTH_TO_HEIGHT_RATIO;
		else if (width / height > WIDTH_TO_HEIGHT_RATIO)
			width = height * WIDTH_TO_HEIGHT_RATIO;
	}
	
	public void drawNextPart() {
		drawPart(part++);
	}
	
	/**
	 * Draw a particular part of a hangman object.
	 * @param part  the part number
	 */
	private void drawPart(int part) {
		switch (part) {
			case 0: addGallows(); break;
			case 1: addFace(); break;
			case 2: addBody(); break;
			case 3: addLeftArm(); break;
			case 4: addRightArm(); break;
			case 5: addLeftLeg(); break;	
			case 6: addRightLeg(); break;		
			case 7: face.leftEye.setVisible(true); break;		
			case 8: face.rightEye.setVisible(true); break;		
			case 9: face.nose.setVisible(true); break;		
			case 10: face.mouth.setVisible(true); break;		
		}
	}
	
	/**
	 * Resets the hangman object by removing all of the parts.
	 */
	public void reset() {
		removeAll();
		part = 0;
		drawNextPart();
	}
	
	/**
	 * Returns the maximum number of hangman parts that can be drawn.
	 */
	public int getMaxParts() {
		return MAX_PARTS;
	}
	
	// The following methods add the hangman parts to the GCompound.
	
	private void addGallows(){
		GPolygon gallows = createGallows();
		add(gallows, -width/2, -height/2);
	}
	
	private void addFace(){
		GFace face = createFace();
		add(face, -width/2 + 0.65*width, -height/2 + 0.25*height);
	}
	
	private void addBody(){
		GRect body = createBody();
		add(body, -width/2 + 0.55*width, -height/2 + 0.35*height);
	}

	private void addLeftArm(){
		GPolygon leftArm = createLeftArm();
		add(leftArm, -width/2 + 0.55*width, -height/2 + 0.35*height);
	}
	
	private void addRightArm(){
		GPolygon rightArm = createRightArm();
		add(rightArm, -width/2 + (0.55 + BODY_WIDTH)*width, -height/2 + 0.35*height);
	}
	
	private void addLeftLeg(){
		GPolygon leg = createLeftLeg();
		add(leg, -width/2 + 0.65*width, -height/2 + (0.35 + BODY_HEIGHT)*height);
	}
	
	private void addRightLeg(){
		GPolygon leg = createRightLeg();
		add(leg, -width/2 + 0.65*width, -height/2 + (0.35 + BODY_HEIGHT)*height);
	}

	// The following methods create the hangman parts.
	
	/** Defines the GFace with respect to the upper left corner. */
	private GFace createFace(){
		face = new GFace(1.0, 1.0);
		face.scale(FACE_WIDTH*width, FACE_HEIGHT*height);
		face.setFilled(true);
		face.setFillColor(HANGMAN_COLOR);
		return face;
	}	
	
	/** Defines the GRect with respect to the upper left corner. */
	private GRect createBody(){
		GRect body = new GRect(1.0, 1.0);
		body.scale(BODY_WIDTH*width, BODY_HEIGHT*height);
		body.setFilled(true);
		body.setFillColor(HANGMAN_COLOR);
		return body;
	}
	
	/** Defines the GPolygon with respect to the upper right corner. */
	private GPolygon createLeftArm(){
		GPolygon arm = new GPolygon();
		arm.addVertex( 0.0, 0.0 );
		arm.addVertex(-0.4, 0.8 );
		arm.addVertex(-0.4, 1.0 );
		arm.addVertex(-0.2, 1.0 );
		arm.addVertex(-0.2, 0.8 );
		arm.addVertex( 0.0, 0.4 );
		arm.scale(ARM_WIDTH*width, ARM_HEIGHT*height);
		arm.setFilled(true);
		arm.setFillColor(HANGMAN_COLOR);
		return arm;
	}
	
	/** Defines the GPolygon with respect to the upper left corner. */	
	private GPolygon createRightArm(){
		GPolygon arm = new GPolygon();
		arm.addVertex( 0.0, 0.0 );
		arm.addVertex( 0.4, 0.8 );
		arm.addVertex( 0.4, 1.0 );
		arm.addVertex( 0.2, 1.0 );
		arm.addVertex( 0.2, 0.8 );
		arm.addVertex( 0.0, 0.4 );
		arm.scale(ARM_WIDTH*width, ARM_HEIGHT*height);
		arm.setFilled(true);
		arm.setFillColor(HANGMAN_COLOR);
		return arm;
	}
	
	/** Defines the GPolygon with respect to the upper right corner. */
	private GPolygon createLeftLeg(){
		GPolygon leg = new GPolygon();
		leg.addVertex( 0.0, 0.0 );
		leg.addVertex(-0.5, 0.0 );
		leg.addVertex(-0.5, 1.0 );
		leg.addVertex(-0.2, 1.0 );
		leg.addVertex( 0.0, 0.2 );
		leg.scale(LEG_WIDTH*width, LEG_HEIGHT*height);
		leg.setFilled(true);
		leg.setFillColor(HANGMAN_COLOR);	
		return leg;
	}
	
	/** Defines the GPolygon with respect to the upper left corner. */	
	private GPolygon createRightLeg(){
		GPolygon leg = new GPolygon();
		leg.addVertex( 0.0, 0.0 );
		leg.addVertex( 0.5, 0.0 );
		leg.addVertex( 0.5, 1.0 );
		leg.addVertex( 0.2, 1.0 );
		leg.addVertex( 0.0, 0.2 );
		leg.scale(LEG_WIDTH*width, LEG_HEIGHT*height);
		leg.setFilled(true);
		leg.setFillColor(HANGMAN_COLOR);	
		return leg;
	}

	/** Defines the GPolygon with respect to the upper left corner. */
	public GPolygon createGallows() {
		GPolygon gallows = new GPolygon();
		gallows.addVertex( 0.00, 1.00 ); // lower left bottom of base
		gallows.addVertex( 0.00, 0.90 ); // lower left top of base
		gallows.addVertex( 0.10, 0.90 ); // lower left of upright
		gallows.addVertex( 0.10, 0.00 ); // upper left of upright
		gallows.addVertex( 0.70, 0.00 ); // upper right of upright
		gallows.addVertex( 0.70, 0.10 ); // lower right bottom of top
		gallows.addVertex( 0.66, 0.10 ); // upper right of rope
		gallows.addVertex( 0.66, 0.25 ); // lower right of rope
		gallows.addVertex( 0.64, 0.25 ); // lower left of rope
		gallows.addVertex( 0.64, 0.10 ); // upper left of rope
		gallows.addVertex( 0.20, 0.10 ); // lower left bottom of top
		gallows.addVertex( 0.20, 0.90 ); // lower right bottom of upright
		gallows.addVertex( 1.00, 0.90 ); // upper right of base
		gallows.addVertex( 1.00, 1.00 ); // lower right of base
		gallows.scale(GALLOWS_WIDTH*width, GALLOWS_HEIGHT*height);
		gallows.setFilled(true);
		gallows.setFillColor(GALLOWS_COLOR);
		return gallows;
	}
	
	private static final int MAX_PARTS = 6;
	private static final Color  GALLOWS_COLOR = Color.BLACK;
	private static final Color  HANGMAN_COLOR = Color.YELLOW;

	/* Constants specifying feature percentages as fractions of other features */
	private static final double WIDTH_TO_HEIGHT_RATIO = 2./3.;
	private static final double GALLOWS_WIDTH  = 1.00;             // as a % of object width
	private static final double GALLOWS_HEIGHT = 1.00;             // as a % of object height
	private static final double FACE_WIDTH  = 0.15*GALLOWS_WIDTH;  // as a % of gallows width
	private static final double FACE_HEIGHT = 0.20*GALLOWS_HEIGHT; // as a % of gallows height
	private static final double BODY_WIDTH  = 1.33*FACE_WIDTH;     // as a % of face width
	private static final double BODY_HEIGHT = 1.00*FACE_HEIGHT;    // as a % of face height
	private static final double ARM_WIDTH  = 0.80*BODY_WIDTH;      // as a % of body width
	private static final double ARM_HEIGHT = 0.80*BODY_HEIGHT;     // as a % of body height
	private static final double LEG_WIDTH  = 1.0*BODY_WIDTH;       // as a % of body width
	private static final double LEG_HEIGHT = 1.0*LEG_WIDTH;        // as a % of leg width

/* Private instance variables */
	private double width, height;
	private int part;
	private GFace face;
}