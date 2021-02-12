package com.ben.engine.util;

public class Timer {

	public enum Format { SECONDS, MILLISECONDS, MICROSECONDS, NANOSECONDS }
	
	private String name;
	private long start;
	
	public Timer(String name) {
		this.name = name;
		this.start = System.nanoTime();
	}
	
	public void end(Format format) {
		long elapsed = System.nanoTime() - start;
		
		switch (format) {
		case SECONDS:
			System.out.println("Timer \"" + name + "\": " + elapsed / 1e9 + "s");
			break;
		case MILLISECONDS:
			System.out.println("Timer \"" + name + "\": " + elapsed / 1e6 + "ms");
			break;
		case MICROSECONDS:
			System.out.println("Timer \"" + name + "\": " + elapsed / 1e3 + "μs");
			break;
		case NANOSECONDS:
			System.out.println("Timer \"" + name + "\": " + elapsed + "ns");
			break;
		}
	}
	
	public void end() {
		end(Format.SECONDS);
	}

}
