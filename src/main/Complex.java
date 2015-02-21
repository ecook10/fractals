package main;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Complex {
	public double re;
	public double im;
	
	public Complex() {
		this.re = 0;
		this.im = 0;
	}
	
	public Complex(double real, double imag) {
		this.re = real;
		this.im = imag;
	}
	
	public void advanceReal(double inc) {
		re += inc;
	}
	
	public void advanceImag(double inc) {
		im += inc;
	}
	
	public void print() {
		DecimalFormat df = new DecimalFormat("##.#######");
		df.setRoundingMode(RoundingMode.DOWN);
		System.out.println(df.format(re) + " " + df.format(im) + "i");
	}
	
	public void squared() {
		double oldReal = re;
		re = (re*re) - (im*im);
		im = 2 * oldReal * im;
	}
	
	public void plus(Complex add) {
		re = re + add.re;
		im = im + add.im;
	}
	
	public double radius() {
		return (double) Math.sqrt(re*re + im*im);
	}
	
}
