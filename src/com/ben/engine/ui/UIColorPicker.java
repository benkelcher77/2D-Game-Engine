package com.ben.engine.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.ben.engine.Mouse;
import com.ben.engine.util.Mathf;
import com.ben.engine.util.Vector2f;

public class UIColorPicker extends UIComponent {

	private float selectedHue = 0f;
	private float selectedSaturation = 0f;
	private float selectedValue = 0f;
	private Color color = Color.BLACK;
	private transient BufferedImage colorBlock;
	private UISlider hueSlider;
	
	public UIColorPicker(Vector2f position, Vector2f scale) {
		super(position, scale);
		
		this.hueSlider = new UISlider(position.add(new Vector2f(0f, scale.y / 2f + 16)), new Vector2f(scale.x, 16), 0f, 1f);
		this.hueSlider.setCurrentValue(0f);
		this.hueSlider.setSliderColor(Color.getHSBColor(0f, 0f, 0f));
		this.hueSlider.setLeftTrackColor(Color.LIGHT_GRAY);
		this.hueSlider.setRightTrackColor(Color.LIGHT_GRAY);
		this.hueSlider.setSliderOutline(true);
		
		this.colorBlock = new BufferedImage((int)scale.x, (int)scale.y, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < (int)scale.y; y++) {
			for (int x = 0; x < (int)scale.x; x++) {
				colorBlock.setRGB(x, y, Color.HSBtoRGB(hueSlider.getCurrentValue(), Mathf.map(x, 0f, scale.x, 0f, 1f), 1f - Mathf.map(y, 0f, scale.y, 0f, 1f)));
			}
		}
	}

	@Override
	public void onCreate() {
		hueSlider.onCreate();
	}

	@Override
	public void onDestroy() {
		hueSlider.onDestroy();
	}

	@Override
	public boolean update() {
		hueSlider.update();
		
		if (selectedHue != hueSlider.getCurrentValue()) {
			selectedHue = hueSlider.getCurrentValue();
			color = Color.getHSBColor(selectedHue, selectedSaturation, selectedValue);
			
			hueSlider.setSliderColor(color);
			for (int y = 0; y < (int)scale.y; y++) {
				for (int x = 0; x < (int)scale.x; x++) {
					colorBlock.setRGB(x, y, Color.HSBtoRGB(selectedHue, Mathf.map(x, 0f, scale.x, 0f, 1f), 1f - Mathf.map(y, 0f, scale.y, 0f, 1f)));
				}
			}
		}
		
		Rectangle colorBlockRect = new Rectangle((int)(position.x - scale.x / 2f), (int)(position.y - scale.y / 2f), (int)scale.x, (int)scale.y);
		int mx = Mouse.instance.getX();
		int my = Mouse.instance.getY();
		
		if (Mouse.instance.isButtonPressed(MouseEvent.BUTTON1) && colorBlockRect.contains(mx, my)) {
			selectedSaturation = Mathf.map(mx, position.x - scale.x / 2f, position.x + scale.x / 2f, 0f, 1f);
			selectedValue = 1f - Mathf.map(my, position.y - scale.y / 2f, position.y + scale.y / 2f, 0f, 1f);
			color = Color.getHSBColor(selectedHue, selectedSaturation, selectedValue);
			hueSlider.setSliderColor(color);
		}
		
		return false;
	}

	@Override
	public void render(Graphics2D g) {
		hueSlider.render(g);
		
		g.drawImage(colorBlock, (int)(position.x - scale.x / 2f), (int)(position.y - scale.y / 2f), (int)scale.x, (int)scale.y, null);
		g.setColor(Color.BLACK);
		g.drawRect((int)(position.x - scale.x / 2f), (int)(position.y - scale.y / 2f), (int)scale.x, (int)scale.y);
		
		g.setColor(Color.WHITE);
		int selectorX = (int)Mathf.map(selectedSaturation, 0f, 1f, position.x - scale.x / 2f, position.x + scale.x / 2f);
		int selectorY = (int)Mathf.map(1f - selectedValue, 0f, 1f, position.y - scale.y / 2f, position.y + scale.y / 2f);
		g.drawOval(selectorX - 4, selectorY - 4, 8, 8);
	}
	
	public Color getColor() {
		return color;
	}

}
