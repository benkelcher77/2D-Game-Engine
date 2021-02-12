package com.ben.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Keyboard extends KeyAdapter {
	
	public interface KeyTypedListener extends Serializable { void onKeyTyped(KeyEvent e); }
	
	public static Keyboard instance = new Keyboard();

	private boolean[] keys = new boolean[1024];
	private List<KeyTypedListener> registered;
	
	public Keyboard() {
		for (int i = 0; i < keys.length; i++)
			keys[i] = false;
		
		registered = new ArrayList<>();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		for (KeyTypedListener ktl : registered)
			ktl.onKeyTyped(e);
	}
	
	public void registerKeyTypedListener(KeyTypedListener ktl) {
		registered.add(ktl);
	}
	
	public void unregisterKeyTypedListener(KeyTypedListener ktl) {
		registered.remove(ktl);
	}
	
	public boolean isKeyDown(int keycode) {
		return keys[keycode];
	}

}
