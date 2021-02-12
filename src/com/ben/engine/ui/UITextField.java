package com.ben.engine.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.ben.engine.Keyboard;
import com.ben.engine.Mouse;
import com.ben.engine.util.Vector2f;

public class UITextField extends UIComponent {
	
	private Font font = new Font("Arial", Font.PLAIN, 14);
	private Color textColor = Color.BLACK;
	private Color unfocusedTextColor = Color.LIGHT_GRAY;
	private String text = "";
	private String defaultText = "Default";
	private String displayText = text;
	private int paddingX = 4;
	
	private boolean outline = false;
	private Color outlineColor = Color.BLACK;
	
	private boolean background = false;
	private Color backgroundColor = Color.LIGHT_GRAY;
	
	private boolean clicked = false;
	private boolean focused = false;
	
	private int frameTimer = 0;
	private boolean showCursor = false;
	
	private Keyboard.KeyTypedListener ktl = (e) -> keyTyped(e);
	
	public UITextField(Vector2f position, Vector2f scale) {
		super(position, scale);
	}

	@Override
	public void onCreate() {
		Keyboard.instance.registerKeyTypedListener(ktl);
	}

	@Override
	public void onDestroy() {
		Keyboard.instance.unregisterKeyTypedListener(ktl);
	}

	@Override
	public boolean update() {
		Rectangle rect = new Rectangle((int)position.x - (int)scale.x / 2, 
				   (int)position.y - (int)scale.y / 2, 
				   (int)scale.x, 
				   (int)scale.y);
		int mx = Mouse.instance.getX();
		int my = Mouse.instance.getY();
		
		displayText = "";
		
		if (!clicked && Mouse.instance.isButtonPressed(MouseEvent.BUTTON1)) {
			clicked = true;
			
			if (rect.contains(mx, my))
				focused = true;
			else
				focused = false;
			
		} else if (clicked && !Mouse.instance.isButtonPressed(MouseEvent.BUTTON1)) {
			clicked = false;
		}
		
		frameTimer++;
		if (frameTimer >= 60) {
			frameTimer = 0;
		}
		
		if (frameTimer % 30 == 0)
			showCursor = !showCursor;
		
		if (focused || !text.isEmpty())
			displayText = text + (showCursor && focused ? "|" : "");
		else
			displayText = defaultText;
				
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
		
		g.setColor(focused ? textColor : unfocusedTextColor);
		g.setFont(font);
		
		FontMetrics metrics = g.getFontMetrics(font);
		int x = rect.x;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.drawString(displayText, x + paddingX, y); // TODO: Make the text shift to the left as it gets too big for the box
	}
	
	private void keyTyped(KeyEvent e) {
		if (focused) {
			if (e.getKeyChar() == '\b') {
				if (!text.isEmpty())
					text = text.substring(0, text.length() - 1);
			} else
				text = text + e.getKeyChar();
		}
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

	public Color getUnfocusedTextColor() {
		return unfocusedTextColor;
	}

	public void setUnfocusedTextColor(Color unfocusedTextColor) {
		this.unfocusedTextColor = unfocusedTextColor;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getDefaultText() {
		return defaultText;
	}
	
	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;
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

	public int getPaddingX() {
		return paddingX;
	}

	public void setPaddingX(int paddingX) {
		this.paddingX = paddingX;
	}

}
