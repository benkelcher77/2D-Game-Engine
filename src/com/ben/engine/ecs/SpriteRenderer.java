package com.ben.engine.ecs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.ben.engine.io.FileReader;
import com.ben.engine.util.Mathf;

public class SpriteRenderer extends Component {
	
	private BufferedImage img;
	private Transform transform;
	
	public SpriteRenderer(GameObject parent, Color color) {
		super(parent);
		
		this.img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		this.img.setRGB(0, 0, color.getRGB());
		this.transform = getComponent(Transform.class);
	}
	
	public SpriteRenderer(GameObject parent, BufferedImage img) {
		super(parent);
		
		this.img = img;
		this.transform = getComponent(Transform.class);
	}
	
	public SpriteRenderer(GameObject parent, String path) {
		this(parent, FileReader.readImageFromFile(path));
	}
	
	public void render(Graphics2D g) {
		g.rotate(Math.toRadians(transform.getRotation()), transform.getPosition().x, transform.getPosition().y);
		g.drawImage(img, (int)transform.getPosition().x - (int)transform.getScale().x / 2, 
					     (int)transform.getPosition().y - (int)transform.getScale().y / 2, 
					     (int)transform.getScale().x, 
					     (int)transform.getScale().y, null);
		g.rotate(2f * Mathf.PI - Math.toRadians(transform.getRotation()), transform.getPosition().x, transform.getPosition().y);
	}
	
	public void setColor(Color color) {
		img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		img.setRGB(0, 0, color.getRGB());
	}

	@Override
	public String toSerializedString() {
		return "QuadRenderer: " + "TODO: BufferedImage Serialization";
	}

}
