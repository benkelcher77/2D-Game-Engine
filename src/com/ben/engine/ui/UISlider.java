package com.ben.engine.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.ben.engine.Mouse;
import com.ben.engine.util.Mathf;
import com.ben.engine.util.Vector2f;

public class UISlider extends UIComponent {

	private int sliderSize;
	private float min;
	private float max;
	private float currentValue;
	
	private Color sliderColor = Color.BLACK;
	private Color leftTrackColor = Color.DARK_GRAY;
	private Color rightTrackColor = Color.LIGHT_GRAY;
	
	private boolean sliderOutline = false;
	private Color sliderOutlineColor = Color.BLACK;
	
	private boolean trackOutline = false;
	private Color trackOutlineColor = Color.BLACK;
	
	private boolean hoveringOnSlider = false;
	
	public UISlider(Vector2f position, Vector2f scale, float min, float max) {
		super(position, scale);
		
		this.sliderSize = (int)scale.y;
		this.min = min;
		this.max = max;
		this.currentValue = (min + max) / 2f;
	}

	@Override
	public void onCreate() {
		
	}
	
	@Override
	public void onDestroy() {
		
	}

	@Override
	public boolean update() {
		int sliderX = (int)((position.x - scale.x / 2f) + scale.x * ((currentValue - min) / (max - min)) - sliderSize / 2f);
		int sliderY = (int)(position.y - scale.y / 2f);
		Rectangle slider = new Rectangle(sliderX, sliderY, sliderSize, sliderSize);
		
		Rectangle track = new Rectangle((int)(position.x - scale.x / 2f), (int)(position.y - 4f), (int)scale.x, 8);
		
		int mx = Mouse.instance.getX();
		int my = Mouse.instance.getY();
		
		if (!hoveringOnSlider && slider.contains(mx, my)) {
			hoveringOnSlider = true;
		} else if (hoveringOnSlider && !slider.contains(mx, my)) {
			hoveringOnSlider = false;
		}
		
		if (hoveringOnSlider) {
			if (Mouse.instance.isButtonPressed(MouseEvent.BUTTON1)) {
				int centreOfSlider = sliderX + sliderSize / 2;
				float dv = ((mx - centreOfSlider) / scale.x) * (max - min);
				currentValue += dv;
			}
		} else {
			if (track.contains(mx, my)) {
				if (Mouse.instance.isButtonPressed(MouseEvent.BUTTON1)) {
					currentValue = Mathf.map(mx, position.x - scale.x / 2f, position.x + scale.x / 2f, min, max);
				}
			}
		}
		
		currentValue = Mathf.clamp(currentValue, min, max);
		
		return false;
	}

	@Override
	public void render(Graphics2D g) {
		int sliderX = (int)((position.x - scale.x / 2f) + scale.x * ((currentValue - min) / (max - min)) - sliderSize / 2f);
		int sliderY = (int)(position.y - scale.y / 2f);
		
		g.setColor(leftTrackColor);
		g.fillRect((int)(position.x - scale.x / 2f), 
				   (int)(position.y - 4f), 
				   (int)(scale.x * ((currentValue - min) / (max - min))), 
				   8);
		
		g.setColor(rightTrackColor);
		g.fillRect((int)((position.x - scale.x / 2f) + scale.x * ((currentValue - min) / (max - min))), 
				   (int)(position.y - 4f), 
				   (int)(scale.x * (1f - ((currentValue - min) / (max - min)))), 
				   8);

		if (trackOutline) {
			g.setColor(trackOutlineColor);
			g.drawRect((int)(position.x - scale.x / 2f), (int)(position.y - 4f), (int)scale.x, 8);
		}
		
		g.setColor(sliderColor);
		g.fillRect(sliderX, sliderY, sliderSize, sliderSize);
		
		if (sliderOutline) {
			g.setColor(sliderOutlineColor);
			g.drawRect(sliderX, sliderY, sliderSize, sliderSize);
		}
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(float currentValue) {
		this.currentValue = currentValue;
	}

	public Color getSliderColor() {
		return sliderColor;
	}

	public void setSliderColor(Color sliderColor) {
		this.sliderColor = sliderColor;
	}

	public Color getLeftTrackColor() {
		return leftTrackColor;
	}

	public void setLeftTrackColor(Color leftTrackColor) {
		this.leftTrackColor = leftTrackColor;
	}

	public Color getRightTrackColor() {
		return rightTrackColor;
	}

	public void setRightTrackColor(Color rightTrackColor) {
		this.rightTrackColor = rightTrackColor;
	}

	public boolean isTrackOutline() {
		return trackOutline;
	}

	public void setTrackOutline(boolean trackOutline) {
		this.trackOutline = trackOutline;
	}

	public Color getTrackOutlineColor() {
		return trackOutlineColor;
	}

	public void setTrackOutlineColor(Color trackOutlineColor) {
		this.trackOutlineColor = trackOutlineColor;
	}

	public boolean isSliderOutline() {
		return sliderOutline;
	}

	public void setSliderOutline(boolean sliderOutline) {
		this.sliderOutline = sliderOutline;
	}

	public Color getSliderOutlineColor() {
		return sliderOutlineColor;
	}

	public void setSliderOutlineColor(Color sliderOutlineColor) {
		this.sliderOutlineColor = sliderOutlineColor;
	}

}
