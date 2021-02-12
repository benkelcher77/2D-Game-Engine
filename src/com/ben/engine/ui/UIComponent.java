package com.ben.engine.ui;

import java.awt.Graphics2D;
import java.io.Serializable;

import com.ben.engine.util.Vector2f;

public abstract class UIComponent implements Serializable {
	
	protected Vector2f position;
	protected Vector2f scale;
	
	public UIComponent(Vector2f position, Vector2f scale) {
		this.position = position;
		this.scale = scale;
	}
	
	public abstract void onCreate();
	public abstract void onDestroy();
	public abstract boolean update();
	public abstract void render(Graphics2D g);

}
