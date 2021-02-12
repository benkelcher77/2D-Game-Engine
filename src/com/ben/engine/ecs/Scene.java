package com.ben.engine.ecs;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ben.engine.util.UID;
import com.ben.engine.util.Vector2f;

public class Scene {
	
	private volatile List<GameObject> objects;
	private volatile List<GameObject> queue;
	
	public Scene() {
		this.objects = new ArrayList<>();
		this.queue = new ArrayList<>();
	}
	
	public void update() {
		Iterator<GameObject> it = objects.iterator();
		while (it.hasNext()) {
			GameObject go = it.next();
			if (go.update(objects)) {
				go.onDestroy();
				it.remove();
				UID.unregister(go.getID());
			}
		}
		
		for (GameObject go : queue)
			objects.add(go);
		
		queue.clear();
	}
	
	public void render(Graphics2D g, Vector2f cameraOffset) {
		for (GameObject go : objects) {
			Transform t = go.getComponent(Transform.class);
			if (t != null) {
				// TODO: Optimize this; maybe store Transforms seperately for easy access
				if (go.getComponent(Transform.class).isOnScreen(cameraOffset)) 
					go.render(g);
			}
		}
	}
	
	public void render(Graphics2D g) {
		render(g, Vector2f.ZERO);
	}
	
	public void addObjects(GameObject... objects) {
		for (GameObject go : objects)
			queue.add(go);
	}
	
	public void clear() {
		for (GameObject go : objects)
			go.onDestroy();
		
		objects.clear();
	}
	
	public List<GameObject> getObjects() {
		return objects;
	}
	
	public GameObject getByID(int id) {
		return objects.stream().filter(go -> go.getID() == id).findFirst().orElse(null);
	}

}
