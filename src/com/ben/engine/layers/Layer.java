package com.ben.engine.layers;

import java.awt.Graphics2D;

import com.ben.engine.Application;

public abstract class Layer {
	
	protected Application application;
	
	public Layer(Application application) {
		this.application = application;
	}
	
	public abstract void onAttach();
	public abstract void onDetach();
	public abstract void update();
	public abstract void render(Graphics2D g);

}
