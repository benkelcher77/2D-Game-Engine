package com.ben.engine.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.ben.engine.util.Vector2f;

public class UILabel extends UIComponent {

	private Font font = new Font("Arial", Font.PLAIN, 14);
	private Color textColor = Color.BLACK;
	private String text = "Label";
	
	private boolean outline = false;
	private Color outlineColor = Color.BLACK;
	
	private boolean background = false;
	private Color backgroundColor = Color.LIGHT_GRAY;
	
	public UILabel(Vector2f position, Vector2f scale, String text) {
		super(position, scale);
		
		this.text = text;
	}

	@Override
	public void onCreate() {
		
	}
	
	@Override
	public void onDestroy() {
		
	}

	@Override
	public boolean update() {
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
		
		if (outline) {
			g.setColor(outlineColor);
			g.draw(rect);
		}
		
		g.setColor(textColor);
		g.setFont(font);
		
		FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.drawString(text, x, y);
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

}
