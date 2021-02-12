package com.ben.engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Mouse extends MouseAdapter {

	public static interface MouseClickedListener {
		
		public boolean onMouseClicked(MouseEvent e);
		
	}
	
	public static Mouse instance = new Mouse();
	
	private int x;
	private int y;
	private boolean[] buttons = new boolean[10];
	
	private List<MouseClickedListener> mouseClickSubscribers = new ArrayList<MouseClickedListener>();
	
	public Mouse() {
		this.x = 0;
		this.y = 0;
		for (int i = 0; i < buttons.length; i++)
			buttons[i] = false;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for (MouseClickedListener mcl : mouseClickSubscribers)
			if (mcl.onMouseClicked(e)) 
				break;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isButtonPressed(int button) {
		return buttons[button];
	}
	
	public void mouseClickSubscribe(MouseClickedListener mcl) {
		mouseClickSubscribers.add(mcl);
	}
	
}

