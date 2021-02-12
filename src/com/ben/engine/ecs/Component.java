package com.ben.engine.ecs;

import java.awt.Graphics2D;
import java.util.List;

public abstract class Component {
	
	protected GameObject parent;
	
	public Component(GameObject parent) {
		this.parent = parent;
	}
	
	public abstract String toSerializedString();
	
	public void onCreate() {
		
	}
	
	public void onDestroy() {
		
	}
	
	public boolean update(List<GameObject> objects) {
		return false;
	}
	
	public void render(Graphics2D g) {
		return;
	}
	
	protected <T extends Component> T getComponent(Class<T> klass) {
		return parent.getComponent(klass);
	}

}
