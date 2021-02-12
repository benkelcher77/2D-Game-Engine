package com.ben.engine.util;

public class Pair<A, B> {

	public A a;
	public B b;
	
	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pair<?, ?>) {
			Pair<?, ?> other = (Pair<?, ?>)o;
			return a.equals(other.a) && b.equals(other.b);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Pair: " + a.toString() + ", " + b.toString();
	}

}
