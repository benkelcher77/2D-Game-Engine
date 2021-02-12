package com.ben.engine.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UID {
	
	private static List<Integer> uids;
	private static Random random;
	
	private UID() { }
	
	public static int unique() {
		if (uids == null)
			uids = new ArrayList<>();
		
		if (random == null)
			random = new Random();
		
		int generated = -1;
		do {
			generated = random.nextInt();
		} while (generated == -1 || uids.contains(generated));
		
		uids.add(generated);
		return generated;
	}
	
	public static void register(int uid) {
		if (uids == null)
			uids = new ArrayList<>();
		
		uids.add(uid);
	}
	
	public static void unregister(int uid) {
		if (uids == null)
			uids = new ArrayList<>();
		
		// Needs to be cast to an Integer (object) to avoid confusion as to whether it's meant to be an index or an entry
		if (uids.contains(uid)) {
			uids.remove((Integer)uid);
		} else
			System.out.println("UID " + uid + " was never registered!");
	}	

}
