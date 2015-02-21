package main;

import processing.core.*;

public class App2 extends PApplet {
	
	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	private double dx;
	private double dy;
	private int maxCount;
	
	private PImage img;
	
	public void setup() {
		size(640, 360);
		background(255);
		
		img = createImage(width, height, RGB);
		img.loadPixels();
	
		// Establish a range of values on the complex plane
		// A different range will allow us to "zoom" in or out on the fractal
		xmin = (double) -2.50;
		xmax = (double) 2.50;
		ymin = (double) -1.25;
		ymax = (double) 1.25;
		
		// Maximum number of iterations for each point on the complex plane
		maxCount = 5000;
	
		// Calculate amount we increment x,y for each pixel
		dx = (xmax - xmin) / (width);
		dy = (ymax - ymin) / (height);
	
		// Start y
		double y = ymin;
		for (int j = 0; j < height; j++) {
		  // Start x
			double x = xmin;
			for (int i = 0; i < width; i++) {
			    // Now we test, as we iterate z = z^2 + cm does z tend towards infinity?
				Complex c = new Complex(x,y);
				Complex z = new Complex(0,0);
				int count = 0;
				while (count < maxCount && z.radius() < 2) {
					z.squared();
					z.plus(c);
					count ++;
				}
			
			    colorMode(HSB, maxCount);
				img.set(i, j, color(count, maxCount, maxCount-count));

			    x += dx;
			}
		y += dy;
		}
	img.updatePixels();
	}
	
	public void draw() {
		image(img,0,0);
		println("x: " + (dx*mouseX + xmin));
		println("y: " + (dy*mouseY + ymin));
	}
	
	public void mouseClicked() {
		img.loadPixels();
		
		double xToAdd = (xmax - xmin) / 8;
		double yToAdd = (ymax - ymin) / 8;
		double xSpot = dx*mouseX + xmin;
		double ySpot = dy*mouseY + ymin;
		xmin = xSpot - xToAdd;
		xmax = xSpot + xToAdd;
		ymin = ySpot - yToAdd;
		ymax = ySpot + yToAdd;

		// Calculate amount we increment x,y for each pixel
		dx = dx/4;
		dy = dy/4;
		
		// Start y
		double y = ymin;
		for (int j = 0; j < height; j++) {
		  // Start x
			double x = xmin;
			for (int i = 0; i < width; i++) {
			    // Now we test, as we iterate z = z^2 + cm does z tend towards infinity?
				Complex c = new Complex(x,y);
				Complex z = new Complex(0,0);
				int count = 0;
				while (count < maxCount && z.radius() < 2) {
					z.squared();
					z.plus(c);
					count ++;
				}
			
			    colorMode(HSB, maxCount);
				img.set(i, j, color(count, maxCount, maxCount-count));

			    x += dx;
			}
		y += dy;
		}
		img.updatePixels();
	}
}
