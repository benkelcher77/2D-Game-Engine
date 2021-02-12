package com.ben.engine.util;

import java.io.Serializable;

public class Vector4f implements Serializable {

	public static final Vector4f ZERO = new Vector4f(0f);
	public static final Vector4f ONE = new Vector4f(1f);
	
	public float x;
	public float y;
	public float z;
	public float w;
	
	public Vector4f(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Vector4f(Vector4f other) {
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
		this.w = other.w;
	}
	
	public Vector4f(float scalar) {
		this.x = scalar;
		this.y = scalar;
		this.z = scalar;
		this.w = scalar;
	}
	
	public float length() {
		return Mathf.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public Vector4f add(Vector4f other) {
		return new Vector4f(x + other.x, y + other.y, z + other.z, w + other.w);
	}
	
	public Vector4f subtract(Vector4f other) {
		return new Vector4f(x - other.x, y - other.y, z - other.z, w - other.w);
	}
	
	public Vector4f multiply(Vector4f other) {
		return new Vector4f(x * other.x, y * other.y, z * other.z, w * other.w);
	}
	
	public Vector4f scale(float scalar) {
		return new Vector4f(x * scalar, y * scalar, z * scalar, w * scalar);
	}
	
	public float dot(Vector4f other) {
		return x * other.x + y * other.y + z * other.z + w * other.w;
	}
	
	public Vector4f normalized() {
		float l = length();
		return new Vector4f(x / l, y / l, z / l, w / l);
	}
	
	public Vector4f negated() {
		return new Vector4f(-x, -y, -z, -w);
	}
	
	public static float length(Vector4f vector) {
		return Mathf.sqrt(vector.x * vector.x + vector.y * vector.y + vector.z * vector.z + vector.w * vector.w);
	}
	
	public static Vector4f add(Vector4f a, Vector4f b) {
		return new Vector4f(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
	}
	
	public static Vector4f subtract(Vector4f a, Vector4f b) {
		return new Vector4f(a.x - b.x, a.y - b.y, a.z - b.z, a.w - b.w);
	}
	
	public static Vector4f scale(Vector4f vector, float scalar) {
		return new Vector4f(vector.x * scalar, vector.y * scalar, vector.z * scalar, vector.w * scalar);
	}
	
	public static float dot(Vector4f a, Vector4f b) {
		return a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w;
	}
	
	public static Vector4f normalized(Vector4f vector) {
		float l = vector.length();
		return new Vector4f(vector.x / l, vector.y / l, vector.z / l, vector.w / l);
	}
	
	public static Vector4f negated(Vector4f vector) {
		return new Vector4f(-vector.x, -vector.y, -vector.z, -vector.w);
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + ", " + w + "]";
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Vector4f))
			return false;
		
		return x == ((Vector3f)other).x && y == ((Vector3f)other).y && z == ((Vector3f)other).z && w == ((Vector4f)other).w;
	}

}
