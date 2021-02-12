package com.ben.engine.scripting;

import java.awt.Graphics2D;
import java.util.List;

import com.ben.engine.ecs.Component;
import com.ben.engine.ecs.ComponentNotFoundException;
import com.ben.engine.ecs.GameObject;

public abstract class BasicScript {

	protected GameObject parent;
	
	public BasicScript() { }
	
	protected <T extends Component> T getComponent(Class<T> klass) {
		T comp = parent.getComponent(klass);
		if (comp != null)
			return comp;
		
		throw new ComponentNotFoundException("Component of type " + klass.getCanonicalName() + " not found on GameObject " + parent);
	}
	
	public void initialize(GameObject parent) {
		this.parent = parent;
	}
	
	public void onCreate() {
		
	}
	
	public void onDestroy() {
		
	}
	
	public boolean update(List<GameObject> objects) {
		return false;
	}
	
	public void render(Graphics2D g) {
		
	}

}
