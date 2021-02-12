package com.ben.engine.ecs;

import java.awt.Rectangle;

import com.ben.engine.util.BitMask;
import com.ben.engine.util.Vector2f;

/**
 * A component class representing a "skinned" box bounds (left, right, top, bottom).
 * Fair warning: this does not work with rotation.
 */
public class BoxBounds extends Component {

	private Transform transform;
	private BitMask collisionMask;
	
	public BoxBounds(GameObject parent) {
		super(parent);
		
		this.transform = getComponent(Transform.class);
		this.collisionMask = null;
	}
	
	public BoxBounds(GameObject parent, BitMask collisionMask) {
		super(parent);
		
		this.transform = getComponent(Transform.class);
		this.collisionMask = collisionMask;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)transform.getPosition().x - (int)transform.getScale().x / 2, 
				   			 (int)transform.getPosition().y - (int)transform.getScale().y / 2, 
				   			 (int)transform.getScale().x, 
				   			 (int)transform.getScale().y);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)transform.getPosition().x - (int)transform.getScale().x / 2,
							 (int)transform.getPosition().y - (int)transform.getScale().y / 4,
							 (int)transform.getScale().x / 4,
							 (int)transform.getScale().y / 2);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int)transform.getPosition().x + (int)transform.getScale().x / 4,
							 (int)transform.getPosition().y - (int)transform.getScale().y / 4,
							 (int)transform.getScale().x / 4,
							 (int)transform.getScale().y / 2);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)transform.getPosition().x - (int)transform.getScale().x / 4,
							 (int)transform.getPosition().y - (int)transform.getScale().y / 2,
							 (int)transform.getScale().x / 2,
							 (int)transform.getScale().y / 2);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int)transform.getPosition().x - (int)transform.getScale().x / 4,
							 (int)transform.getPosition().y,
							 (int)transform.getScale().x / 2,
							 (int)transform.getScale().y / 2);
	}
	
	public Rectangle getProjectedBounds(Vector2f velocity) {
		return new Rectangle((int)transform.getPosition().x + (int)velocity.x - (int)transform.getScale().x / 2, 
				   			 (int)transform.getPosition().y + (int)velocity.y - (int)transform.getScale().y / 2, 
				   			 (int)transform.getScale().x, 
				   			 (int)transform.getScale().y);
	}
	
	public Rectangle getProjectedBoundsLeft(Vector2f velocity) {
		return new Rectangle((int)transform.getPosition().x + (int)velocity.x - (int)transform.getScale().x / 2,
							 (int)transform.getPosition().y + (int)velocity.y - (int)transform.getScale().y / 4,
							 (int)transform.getScale().x / 4,
							 (int)transform.getScale().y / 2);
	}
	
	public Rectangle getProjectedBoundsRight(Vector2f velocity) {
		return new Rectangle((int)transform.getPosition().x + (int)velocity.x + (int)transform.getScale().x / 4,
							 (int)transform.getPosition().y + (int)velocity.y - (int)transform.getScale().y / 4,
							 (int)transform.getScale().x / 4,
							 (int)transform.getScale().y / 2);
	}
	
	public Rectangle getProjectedBoundsTop(Vector2f velocity) {
		return new Rectangle((int)transform.getPosition().x + (int)velocity.x - (int)transform.getScale().x / 4,
							 (int)transform.getPosition().y + (int)velocity.y - (int)transform.getScale().y / 2,
							 (int)transform.getScale().x / 2,
							 (int)transform.getScale().y / 2);
	}
	
	public Rectangle getProjectedBoundsBottom(Vector2f velocity) {
		return new Rectangle((int)transform.getPosition().x + (int)velocity.x - (int)transform.getScale().x / 4,
							 (int)transform.getPosition().y + (int)velocity.y,
							 (int)transform.getScale().x / 2,
							 (int)transform.getScale().y / 2);
	}

	public BitMask getCollisionMask() {
		return collisionMask;
	}
	
	public boolean isOnLayer(int layer) {
		return collisionMask == null || collisionMask.containsBit(layer);
	}

	public void setCollidable(BitMask collisionMask) {
		this.collisionMask = collisionMask;
	}

	public Transform getTransform() {
		return transform;
	}

	@Override
	public String toSerializedString() {
		return "BoxBounds: " + (collisionMask == null ? "NoMask" : collisionMask.getMask());
	}

}
