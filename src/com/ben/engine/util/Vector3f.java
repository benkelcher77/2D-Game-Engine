package com.ben.engine.util;

import java.io.Serializable;

public class Vector3f implements Serializable {
	
	public static final Vector3f ZERO = new Vector3f(0f);
	public static final Vector3f ONE = new Vector3f(1f);
	
	public float x;
	public float y;
	public float z;
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f(Vector3f other) {
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	
	public Vector3f(float scalar) {
		this.x = scalar;
		this.y = scalar;
		this.z = scalar;
	}
	
	public float length() {
		return Mathf.sqrt(x * x + y * y + z * z);
	}
	
	public Vector3f add(Vector3f other) {
		return new Vector3f(x + other.x, y + other.y, z + other.z);
	}
	
	public Vector3f subtract(Vector3f other) {
		return new Vector3f(x - other.x, y - other.y, z - other.z);
	}
	
	public Vector3f multiply(Vector3f other) {
		return new Vector3f(x * other.x, y * other.y, z * other.z);
	}
	
	public Vector3f scale(float scalar) {
		return new Vector3f(x * scalar, y * scalar, z * scalar);
	}
	
	public float dot(Vector3f other) {
		return x * other.x + y * other.y + z * other.z;
	}
	
	public Vector3f normalized() {
		float l = length();
		return new Vector3f(x / l, y / l, z / l);
	}
	
	public Vector3f negated() {
		return new Vector3f(-x, -y, -z);
	}
	
	public static float length(Vector3f vector) {
		return Mathf.sqrt(vector.x * vector.x + vector.y * vector.y + vector.z * vector.z);
	}
	
	public static Vector3f add(Vector3f a, Vector3f b) {
		return new Vector3f(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	public static Vector3f subtract(Vector3f a, Vector3f b) {
		return new Vector3f(a.x - b.x, a.y - b.y, a.z - b.z);
	}
	
	public static Vector3f scale(Vector3f vector, float scalar) {
		return new Vector3f(vector.x * scalar, vector.y * scalar, vector.z * scalar);
	}
	
	public static float dot(Vector3f a, Vector3f b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}
	
	public static Vector3f normalized(Vector3f vector) {
		float l = vector.length();
		return new Vector3f(vector.x / l, vector.y / l, vector.z / l);
	}
	
	public static Vector3f negated(Vector3f vector) {
		return new Vector3f(-vector.x, -vector.y, -vector.z);
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Vector3f))
			return false;
		
		return x == ((Vector3f)other).x && y == ((Vector3f)other).y && z == ((Vector3f)other).z;
	}

}
