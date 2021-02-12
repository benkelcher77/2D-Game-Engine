package com.ben.engine.ui;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UIHandler implements Serializable {
	
	private volatile List<UIComponent> components;
	private volatile List<UIComponent> queue;
	
	public UIHandler() {
		this.components = new ArrayList<>();
		this.queue = new ArrayList<>();
	}
	
	public void update() {
		Iterator<UIComponent> it = components.iterator();
		while (it.hasNext()) {
			UIComponent component = it.next();
			if (component.update()) {
				component.onDestroy();
				it.remove();
			}
		}
		
		for (UIComponent go : queue)
			components.add(go);
		
		queue.clear();
	}
	
	public void render(Graphics2D g) {
		for (UIComponent component : components)
			component.render(g);
	}
	
	public void addComponents(UIComponent... components) {
		for (UIComponent component : components) {
			component.onCreate();
			queue.add(component);
		}
	}

}
