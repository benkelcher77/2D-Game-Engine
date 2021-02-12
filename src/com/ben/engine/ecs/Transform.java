package com.ben.engine.ecs;

import java.awt.Rectangle;

import com.ben.engine.util.Vector2f;

public class Transform extends Component {
	
	private Vector2f position; // Representing the MIDDLE of the rectangle
	private float rotation;
	private Vector2f scale;
	
	public Transform(GameObject parent, Vector2f position, float rotation, Vector2f scale) {
		super(parent);
		
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public Transform(GameObject parent, float x, float y, float rotation, float width, float height) {
		this(parent, new Vector2f(x, y), rotation, new Vector2f(width, height));
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
	
	/**
	 * @return Whether or not the rectangle represented by this transform is on screen. Does not currently support rotation because my brain is not 
	 * big enough for that.
	 */
	public boolean isOnScreen(Vector2f cameraOffset) {
		return parent.application.windowBounds.intersects(new Rectangle((int)getPosition().x - (int)getScale().x / 2 + (int)cameraOffset.x, 
														   			    (int)getPosition().y - (int)getScale().y / 2 + (int)cameraOffset.y, 
														   			    (int)getScale().x, 
														   			    (int)getScale().y));
	}

	@Override
	public String toSerializedString() {
		return "Transform: " + position.toString() + ", " + rotation + ", " + scale.toString();
	}

}
