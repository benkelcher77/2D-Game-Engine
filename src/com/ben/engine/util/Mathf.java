package com.ben.engine.util;

public class Mathf {
	
	public static final float PI = (float)Math.PI;
	public static final float E = (float)Math.E;
	
	private Mathf() { }
	
	public static float sin(float rad) {
		return (float)Math.sin(rad);
	}
	
	public static float cos(float rad) {
		return (float)Math.cos(rad);
	}
	
	public static float tan(float rad) {
		return (float)Math.tan(rad);
	}
	
	public static float asin(float x) {
		return (float)Math.asin(x);
	}
	
	public static float acos(float x) {
		return (float)Math.acos(x);
	}
	
	public static float atan(float x) {
		return (float)Math.atan(x);
	}
	
	public static float atan2(float y, float x) {
		return (float)Math.atan2(y, x);
	}
	
	public static float sinh(float rad) {
		return (float)Math.sinh(rad);
	}
	
	public static float cosh(float rad) {
		return (float)Math.cosh(rad);
	}
	
	public static float tanh(float rad) {
		return (float)Math.tanh(rad);
	}
	
	/**
	 * @param x The exponent to raise <i>e</i> to.
	 * @return The value <i>e<sup>x</sup></i>,
     *          where <i>e</i> is the base of the natural logarithms.
	 */
	public static float exp(float x) {
		return (float)Math.exp(x);
	}
	
	/**
	 * @param x The value to take the logarithm of.
	 * @return The value <i>log<sub>10</sub>(x)</i>.
	 */
	public static float log10(float x) {
		return (float)Math.log10(x);
	}
	
	/**
	 * @param x The value to take the natural logarithm of.
	 * @return The value <i>ln(x)</i> or <i>log<sub>e</sub>(x)</i>, 
	 * 			where <i>e</i> is the base of natural logarithms.
	 */
	public static float ln(float x) {
		return (float)Math.log(x);
	}
	
	/**
	 * @param x The value to take the logarithm of.
	 * @return The value <i>log<sub>2</sub>(x)</i>.
	 */
	public static float log2(float x) {
		return ln(x) / ln(2);
	}
	
	/**
	 * @param n The base of the logarithm to be computed.
	 * @param x The value to take the logarithm of.
	 * @return The value <i>log<sub>n</sub>(x)</i>.
	 */
	public static float logn(float n, float x) {
		return ln(x) / ln(n);
	}
	
	/**
	 * @param x The value to take the positive square root of.
	 * @return The positive square root of <i>x</i>.
	 */
	public static float sqrt(float x) {
		return (float)Math.sqrt(x);
	}
	
	/**
	 * @param x The value to take the cube root of.
	 * @return The cube root of <i>x</i>.
	 */
	public static float cbrt(float x) {
		return (float)Math.cbrt(x);
	}
	
	/**
	 * @param base The base of the power.
	 * @param exponent The exponent of the power.
	 * @return The value <i>base<sup>exponent</sup></i>.
	 */
	public static float pow(float base, float exponent) {
		return (float)Math.pow(base, exponent);
	}
	
	/**
	 * @param index The "n" in the n<sup>th</sup> root (ie. 2 for square root, 3 for cube root).
	 * @param radicand The value to take the n<sup>th</sup> root of.
	 * @return The value of the n<sup>th</sup> root of the radicand. Equivalent to <br><code>pow(radicand, 1f / index)</code>.
	 */
	public static float nrt(float index, float radicand) {
		return (float)Math.pow(radicand, 1f / index);
	}
	
	public static float toRadians(float deg) {
		return (deg * PI) / 180f;
	}
	
	public static float toDegrees(float rad) {
		return (rad * 180f) / PI;
	}
	
	public static int sign(float x) {
		return (x < 0f ? -1 : (x > 0f ? 1 : 0));
	}
	
	public static int round(float x) {
		return Math.round(x);
	}
	
	public static int abs(int x) {
		return (x < 0 ? -x : x);
	}
	
	public static float abs(float x) {
		return (x < 0f ? -x : x);
	}
	
	public static float ceil(float x) {
		return (float)Math.ceil(x);
	}
	
	public static float floor(float x) {
		return (float)Math.floor(x);
	}
	
	public static float random() {
		return (float)Math.random();
	}
	
	public static float map(float x, float origMin, float origMax, float newMin, float newMax) {
		if (origMin == origMax || newMin == newMax)
			throw new IllegalArgumentException("Range of size 0");
		
		return (x - origMin) / (origMax - origMin) * (newMax - newMin) + newMin;
	}
	
	public static float min(float a, float b) {
		return (a < b ? a : b);
	}
	
	public static float max(float a, float b) {
		return (a < b ? b : a);
	}
	
	public static int bitshift(int bit) {
		return 1 << bit;
	}
	
	public static float clamp(float x, float min, float max) {
		return (x < min ? min : (x > max ? max : x));
	}
	
	public static float hypot(float x, float y) {
		return (float)Mathf.hypot(x, y);
	}

}
