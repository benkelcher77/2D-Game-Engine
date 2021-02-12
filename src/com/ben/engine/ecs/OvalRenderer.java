package com.ben.engine.ecs;

import java.awt.Color;
import java.awt.Graphics2D;

public class OvalRenderer extends Component {
	
	private Color color;
	private Transform transform;
	
	public OvalRenderer(GameObject parent, Color color) {
		super(parent);
		
		this.color = color;
		this.transform = getComponent(Transform.class);
	}
	
	public void render(Graphics2D g) {
		g.rotate(Math.toRadians(transform.getRotation()), transform.getPosition().x, transform.getPosition().y);
		g.setColor(color);
		g.fillOval((int)transform.getPosition().x - (int)transform.getScale().x / 2, 
				   (int)transform.getPosition().y - (int)transform.getScale().y / 2, 
				   (int)transform.getScale().x, 
				   (int)transform.getScale().y);
		g.rotate(-Math.toRadians(transform.getRotation()), transform.getPosition().x, transform.getPosition().y);
	}

	@Override
	public String toSerializedString() {
		return "OvalRenderer: " + color.getRGB();
	}

}
