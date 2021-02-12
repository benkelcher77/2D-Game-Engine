package com.ben.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.ben.engine.audio.AudioManager;
import com.ben.engine.layers.LayerStack;

public abstract class Application extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final double UPS = 60.0;
	public static final double DELTA_TIME = 1.0 / UPS;
	
	public Rectangle windowBounds;
	
	protected JFrame frame;
	protected String title;
	protected int width;
	protected int height;
	
	protected Thread thread;
	protected boolean running = false;
	
	protected LayerStack stack;
	
	private Color clearColor = Color.WHITE;
	
	public Application(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		
		addKeyListener(Keyboard.instance);
		addMouseListener(Mouse.instance);
		addMouseMotionListener(Mouse.instance);
		addMouseWheelListener(Mouse.instance);
		
		AudioManager.init();
		
		frame = new JFrame(title);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		stack = new LayerStack();
	}
	
	public synchronized void start() {
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long now = 0L;
		
		final double NS = 1000000000.0 / UPS;
		double delta = 0.0;
		
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		
		requestFocus();
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / NS;
			lastTime = now;
			
			while (delta >= 0.0) {
				delta--;
				updates++;
				updateInternal();
			}
			
			frames++;
			renderInternal();
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				
				frame.setTitle(title + " | " + frames + " fps, " + updates + " ups");
				frames = 0;
				updates = 0;
			}
		}
	}
	
	protected abstract void update();
	
	private void updateInternal() {
		stack.update();
		update();
	}
	
	private void renderInternal() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			
			windowBounds = new Rectangle(0, 0, getWidth(), getHeight());
			
			return;
		}
		
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g.setColor(clearColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		stack.render(g);
		
		g.dispose();
		bs.show();
	}
	
	protected void setClearColor(Color color) {
		this.clearColor = color;
	}

}
