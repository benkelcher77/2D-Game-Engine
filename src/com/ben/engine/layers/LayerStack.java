package com.ben.engine.layers;

import java.awt.Graphics2D;
import java.util.Stack;

public class LayerStack {
	
	private Stack<Layer> stack;
	
	public LayerStack() {
		this.stack = new Stack<>();
	}
	
	public void push(Layer layer) {
		layer.onAttach();
		stack.add(layer);
	}
	
	public Layer pop() {
		Layer popped = stack.pop();
		popped.onDetach();
		
		return popped;
	}
	
	public void remove(Layer layer) {
		layer.onDetach();
		stack.remove(layer);
	}
	
	public void clear() {
		for (Layer layer : stack)
			layer.onDetach();
		
		stack.clear();
	}
	
	public void update() {
		for (Layer layer : stack)
			layer.update();
	}
	
	public void render(Graphics2D g) {
		for (Layer layer : stack)
			layer.render(g);
	}
	
}
