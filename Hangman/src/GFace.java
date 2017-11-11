/** GFace.java:  This file contains the graphics for building a face.
 * @author Dr. Mark A. Jones
 */

import java.awt.Color;
import acm.graphics.*;

@SuppressWarnings("serial")
public class GFace extends GCompound {
	
	/** Construct a new GFace object with the specified dimensions.
	 * @param wid  the width of the allocated area for the face
	 * @param ht   the height of the allocated area for the face
	 */
	public GFace(double wid, double ht) {
		width = wid;
		height = ht;
		addHead();
		addLeftEye();
		addRightEye();
		addNose();
		addMouth();
	}
	
	/** Sets the face fill color
	 * @param col   the color of the face
	 */
	public void setFillColor(Color col) {
		color = col;
		head.setFillColor(color);		
	}
	
	/** Sets the filled flag */
	public void setFilled(boolean val) {
		head.setFilled(val);
	}

	/** Adds the head */
	private void addHead() {
		head = new GOval(width, height);
		setFillColor(Color.WHITE);
		setFilled(true);
		add(head, -width / 2, -height / 2);
	}

	/** Adds the left eye */
	private void addLeftEye() {
		leftEye = new GOval(EYE_WIDTH * width, EYE_HEIGHT * height);
		add(leftEye, -0.25 * width - EYE_WIDTH * width / 2, 
				 -0.25 * height - EYE_HEIGHT * height / 2);
		leftEye.setVisible(false);
	}

	/** Adds the right eye */
	private void addRightEye() {
		rightEye = new GOval(EYE_WIDTH * width, EYE_HEIGHT * height);
		add(rightEye,  0.25 * width - EYE_WIDTH * width / 2, 
			 	  -0.25 * height - EYE_HEIGHT * height / 2);
		rightEye.setVisible(false);
	}

	/** Adds the nose */
	private void addNose() {
		nose = createNose(NOSE_WIDTH * width, NOSE_HEIGHT * height);
		add(nose, 0, 0);
		nose.setVisible(false);
	}

	/** Creates a rectangle for the mouth */
	private void addMouth() {
		mouth = new GRect(MOUTH_WIDTH * width, MOUTH_HEIGHT * height);
		add(mouth, -MOUTH_WIDTH * width / 2,
		    0.25 * height - MOUTH_HEIGHT * height / 2);
		mouth.setVisible(false);
	}

	/** Creates a triangle for the nose */
	private GPolygon createNose(double wid, double ht) {
		GPolygon poly = new GPolygon();
		poly.addVertex(0, -ht / 2);
		poly.addVertex(wid / 2, ht / 2);
		poly.addVertex(-wid / 2, ht / 2);
		return poly;
	}

/* Constants specifying facial feature size as a fraction of the head size */
	private static final double EYE_WIDTH    = 0.15;
	private static final double EYE_HEIGHT   = 0.15;
	private static final double NOSE_WIDTH   = 0.15;
	private static final double NOSE_HEIGHT  = 0.10;
	private static final double MOUTH_WIDTH  = 0.50;
	private static final double MOUTH_HEIGHT = 0.03;

/* Private instance variables */
	public GOval head, leftEye, rightEye;
	public GRect mouth;
	public GPolygon nose;
	private double width, height;
	private Color color;
}