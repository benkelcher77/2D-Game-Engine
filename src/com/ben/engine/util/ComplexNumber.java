package com.ben.engine.util;

import java.io.Serializable;

public class ComplexNumber implements Serializable {
	
	public float re;
	public float im;
	
	public ComplexNumber(float re, float im) {
		this.re = re;
		this.im = im;
	}
	
	public ComplexNumber add(ComplexNumber other) {
		return new ComplexNumber(re + other.re, im + other.im);
	}
	
	public ComplexNumber subtract(ComplexNumber other) {
		return new ComplexNumber(re - other.re, im - other.im);
	}
	
	public ComplexNumber multiply(ComplexNumber other) {
		return new ComplexNumber(re * other.re - im * other.im, re * other.im + im * other.re);
	}
	
	public ComplexNumber divide(ComplexNumber other) {
		return this.multiply(other.reciprocal());
	}
	
	public ComplexNumber reciprocal() {
		return new ComplexNumber(re / (re * re + im * im), -im / (re * re + im * im));
	}
	
	public ComplexNumber conjugate() {
		return new ComplexNumber(re, -im);
	}
	
	public float modulus() {
		return Mathf.sqrt(re * re + im * im);
	}
	
	/**
	 * @return The value <i>e<sup>z</sup></i>, where <i>z</i> is the complex number represented by this object.
	 */
	public ComplexNumber exp() {
		float exponent = Mathf.exp(re);
		return new ComplexNumber(exponent * Mathf.cos(im), exponent * Mathf.sin(im));
	}
	
	@Override
	public String toString() {
		return re + " + " + im + "i";
	}

}
