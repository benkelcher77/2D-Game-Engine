package com.ben.engine.scripting;

import java.awt.event.KeyEvent;
import java.util.List;

import com.ben.engine.Keyboard;
import com.ben.engine.ecs.BoxBounds;
import com.ben.engine.ecs.GameObject;
import com.ben.engine.ecs.Transform;
import com.ben.engine.util.Constants;
import com.ben.engine.util.Vector2f;

public class TopDownPlayerController extends BasicScript {

	private Transform transform;
	private BoxBounds bounds;
	
	private float speed = 5f;
	private Vector2f velocity;
	
	@Override
	public void onCreate() {
		transform = getComponent(Transform.class);
		bounds = getComponent(BoxBounds.class);
	}
	
	@Override
	public boolean update(List<GameObject> objects) {
		velocity = Vector2f.ZERO;
		
		if (Keyboard.instance.isKeyDown(KeyEvent.VK_W)) {
			velocity = velocity.add(new Vector2f(0f, -speed));
		} else if (Keyboard.instance.isKeyDown(KeyEvent.VK_S)) {
			velocity = velocity.add(new Vector2f(0f, speed));
		}
		
		if (Keyboard.instance.isKeyDown(KeyEvent.VK_A)) {
			velocity = velocity.add(new Vector2f(-speed, 0f));
		} else if (Keyboard.instance.isKeyDown(KeyEvent.VK_D)) {
			velocity = velocity.add(new Vector2f(speed, 0f));
		}
		
		collision(objects);
		
		transform.setPosition(transform.getPosition().add(velocity));
		
		return false;
	}
	
	private void collision(List<GameObject> objects) {
		for (GameObject go : objects) {
			if (go.equals(parent)) 
				continue;
			
			BoxBounds bb = go.getComponent(BoxBounds.class);
			if (bb != null) {
				if (bb.isOnLayer(Constants.COLLISION_BIT_PLAYER)) {
					if (bounds.getProjectedBoundsLeft(velocity).intersects(bb.getBounds())) {
						velocity.x = 0;
						transform.setPosition(new Vector2f(bb.getTransform().getPosition().x + bb.getTransform().getScale().x / 2f + transform.getScale().x / 2f, transform.getPosition().y));
					} else if (bounds.getProjectedBoundsRight(velocity).intersects(bb.getBounds())) {
						velocity.x = 0;
						transform.setPosition(new Vector2f(bb.getTransform().getPosition().x - bb.getTransform().getScale().x / 2f - transform.getScale().x / 2f, transform.getPosition().y));
					}
					
					if (bounds.getProjectedBoundsTop(velocity).intersects(bb.getBounds())) {
						velocity.y = 0;
						transform.setPosition(new Vector2f(transform.getPosition().x, bb.getTransform().getPosition().y + bb.getTransform().getScale().y / 2f + transform.getScale().y / 2f));
					} else if (bounds.getProjectedBoundsBottom(velocity).intersects(bb.getBounds())) {
						velocity.y = 0;
						transform.setPosition(new Vector2f(transform.getPosition().x, bb.getTransform().getPosition().y - bb.getTransform().getScale().y / 2f - transform.getScale().y / 2f));						
					}
				}
			}
		}
	}

}
