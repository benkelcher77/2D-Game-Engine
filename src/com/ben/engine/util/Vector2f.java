package com.ben.engine.util;

import java.awt.geom.Point2D;
import java.io.Serializable;

public class Vector2f implements Serializable {
	
	public static final Vector2f ZERO = new Vector2f(0f, 0f);
	public static final Vector2f ONE = new Vector2f(1f);
	
	public float x;
	public float y;
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f(Vector2f other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	public Vector2f(Point2D point) {
		this.x = (float)point.getX();
		this.y = (float)point.getY();
	}
	
	public Vector2f(float scalar) {
		this.x = scalar;
		this.y = scalar;
	}
	
	public float length() {
		return Mathf.sqrt(x * x + y * y);
	}
	
	public Vector2f add(Vector2f other) {
		return new Vector2f(x + other.x, y + other.y);
	}
	
	public Vector2f subtract(Vector2f other) {
		return new Vector2f(x - other.x, y - other.y);
	}
	
	public Vector2f scale(float scalar) {
		return new Vector2f(x * scalar, y * scalar);
	}
	
	public float dot(Vector2f other) {
		return x * other.x + y * other.y;
	}
	
	public Vector2f normalized() {
		float l = length();
		return new Vector2f(x / l, y / l);
	}
	
	public Vector2f negated() {
		return new Vector2f(-x, -y);
	}
	
	public Vector2f perpindicular() {
		return new Vector2f(-y, x);
	}
	
	public static float length(Vector2f vector) {
		return Mathf.sqrt(vector.x * vector.x + vector.y * vector.y);
	}
	
	public static Vector2f add(Vector2f a, Vector2f b) {
		return new Vector2f(a.x + b.x, a.y + b.y);
	}
	
	public static Vector2f subtract(Vector2f a, Vector2f b) {
		return new Vector2f(a.x - b.x, a.y - b.y);
	}
	
	public static Vector2f scale(Vector2f vector, float scalar) {
		return new Vector2f(vector.x * scalar, vector.y * scalar);
	}
	
	public static float dot(Vector2f a, Vector2f b) {
		return a.x * b.x + a.y * b.y;
	}
	
	public static Vector2f normalized(Vector2f vector) {
		float l = vector.length();
		return new Vector2f(vector.x / l, vector.y / l);
	}
	
	public static Vector2f negated(Vector2f vector) {
		return new Vector2f(-vector.x, -vector.y);
	}
	
	public static Vector2f perpinducular(Vector2f vector) {
		return new Vector2f(-vector.y, vector.x);
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Vector2f))
			return false;
		
		return x == ((Vector2f)other).x && y == ((Vector2f)other).y;
	}

}
