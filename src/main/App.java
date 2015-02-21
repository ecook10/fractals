package main;

import processing.core.*; 

public class App extends PApplet{

	private int width;
	private int height;
	private float x_max;
	private float x_min;
	private float y_max;
	private float y_min;
	private Complex c;
	private PImage img;

	public void setup() {
		x_max = (float) 2.5;
		x_min = (float) -2.5;
		y_max = (float) 1.25;
		y_min = (float) -1.25;
		width = (int) ((x_max - x_min) * 128);
		height = (int) ((y_max - y_min) * 128);
		
		size(width,height);
		noLoop();
		img = createImage(width,height,HSB);
		img.loadPixels();
		
		float dx = (x_max - x_min) / width;
		float dy = (y_max - y_min) / height;
		
		Complex c = new Complex();
		c.re = x_min;
		for (int x_pix = 0; x_pix < width; x_pix++) {
			c.im = y_min;
			for (int y_pix = 0; y_pix < height; y_pix++) {

//				float real = x_pix / (width / (x_max-x_min)) - x_max;
//				float imag = y_pix / (height / (y_max-y_min)) - y_max;
//				Complex z = new Complex(x_min,y_min);
				Complex z = new Complex(0,0);
				
				int count = 0;
				int maxCount = 100;
				while (count < maxCount && z.radius() < 2) {
					z.squared()
					z.plus(c);
					count++;
				}
				colorMode(HSB, maxCount);

				img.set(x_pix, y_pix, color(count, maxCount, maxCount-count));
				
				c.advanceImag(dy);
			}
			c.advanceReal(dx);
		}
		img.updatePixels();
	}
	
	public void draw() {
		background(255)	;
		image(img,0,0);
	}
}
