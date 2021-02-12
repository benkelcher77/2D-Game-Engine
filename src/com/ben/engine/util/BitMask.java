package com.ben.engine.util;

public class BitMask {
	
	public int mask;
	
	public BitMask(int... layers) {
		setMask(layers);
	}
	
	public int getMask() {
		return mask;
	}
	
	public void setMask(int... layers) {
		mask = 0;
		for (int i : layers)
			addToMask(i);
	}
	
	public void addToMask(int bit) {
		if (containsBit(bit)) {
			System.out.println(bit + " is already in this mask!");
			return;
		}
		
		mask |= Mathf.bitshift(bit);
	}
	
	public void removeFromMask(int bit) {
		if (!containsBit(bit)) {
			System.out.println(bit + " is not in this mask!");
			return;
		}
		
		mask ^= Mathf.bitshift(bit);
	}
	
	public boolean containsBit(int bit) {
		return (mask & Mathf.bitshift(bit)) != 0;
	}
	
	@Override
	public String toString() {
		return Integer.toBinaryString(mask);
	}

}
