package com.ben.engine.util;

import com.ben.engine.Application;
import com.ben.engine.ecs.Transform;

public class Camera {

	private Application application;
	private Vector2f offset = Vector2f.ZERO;
	
	public Camera(Application application) {
		this.application = application;
	}
	
	public void update(Transform target) {
		offset = target.getPosition().negated().add(new Vector2f(application.windowBounds.width / 2f, application.windowBounds.height / 2f));
	}
	
	public Vector2f getOffset() {
		return offset;
	}

}
