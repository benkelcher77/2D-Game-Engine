package com.ben.engine.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.ben.engine.Mouse;
import com.ben.engine.util.Vector2f;

public class UICheckBox extends UIComponent {
	
	private boolean outline = true;
	private Color outlineColor = Color.BLACK;
	
	private boolean background = false;
	private Color backgroundColor = Color.LIGHT_GRAY;
	
	private Color hoverBackgroundColor = Color.LIGHT_GRAY;
	private Color selectedBackgroundColor = Color.DARK_GRAY;
	
	private boolean hovering = false;
	private boolean clicked = false;
	private boolean selected = false;
	
	public UICheckBox(Vector2f position, Vector2f scale) {
		super(position, scale);
	}

	@Override
	public void onCreate() {
		
	}
	
	@Override
	public void onDestroy() {
		
	}
	
	@Override
	public boolean update() {
		Rectangle rect = new Rectangle((int)position.x - (int)scale.x / 2, 
				   (int)position.y - (int)scale.y / 2, 
				   (int)scale.x, 
				   (int)scale.y);
		int mx = Mouse.instance.getX();
		int my = Mouse.instance.getY();
		
		if (!hovering && rect.contains(mx, my)) {
			hovering = true;
		} else if (hovering && !rect.contains(mx, my)) {
			hovering = false;
		}
		
		if (!clicked && Mouse.instance.isButtonPressed(MouseEvent.BUTTON1)) {
			clicked = true;
			if (hovering) {
				selected = !selected;
			}
		} else if (clicked && !Mouse.instance.isButtonPressed(MouseEvent.BUTTON1)) {
			clicked = false;
		}
		
		return false;
	}

	@Override
	public void render(Graphics2D g) {
		Rectangle rect = new Rectangle((int)position.x - (int)scale.x / 2, 
									   (int)position.y - (int)scale.y / 2, 
									   (int)scale.x, 
									   (int)scale.y);
		
		if (background) {
			g.setColor(backgroundColor);
			g.fill(rect);
		}
		
		if (hovering) {
			g.setColor(hoverBackgroundColor);
			g.fill(rect);
		}
		
		if (selected) {
			g.setColor(selectedBackgroundColor);
			g.fill(rect);
		}
		
		if (outline) {
			g.setColor(outlineColor);
			g.draw(rect);
		}
	}

	public boolean isOutline() {
		return outline;
	}

	public void setOutline(boolean outline) {
		this.outline = outline;
	}

	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public boolean isBackground() {
		return background;
	}

	public void setBackground(boolean background) {
		this.background = background;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getHoverBackgroundColor() {
		return hoverBackgroundColor;
	}

	public void setHoverBackgroundColor(Color hoverBackgroundColor) {
		this.hoverBackgroundColor = hoverBackgroundColor;
	}

	public Color getSelectedBackgroundColor() {
		return selectedBackgroundColor;
	}

	public void setSelectedBackgroundColor(Color selectedBackgroundColor) {
		this.selectedBackgroundColor = selectedBackgroundColor;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
