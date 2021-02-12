package com.ben.engine.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class FileReader {

	public interface FileLoadEvent { void onFinished(ByteBuffer data); }
	public interface ImageLoadEvent { void onFinished(BufferedImage img); }
	public interface ObjectLoadEvent { void onFinished(Object obj); }
	
	private FileReader() { }
	
	public static ByteBuffer readFile(String filepath) throws IOException {
		FileInputStream fis = new FileInputStream(new File(filepath));
		ByteBuffer buf = ByteBuffer.wrap(fis.readAllBytes());
		fis.close();
		
		return buf;
	}
	
	public static BufferedImage readImageFromFile(String filepath) {
		try {
			return ImageIO.read(new File(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Object readObjectFromFile(String filepath) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(new File(filepath));
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();
		fis.close();
		
		return obj;
	}
	
	public static void readFile(String filepath, FileLoadEvent onFinished) {
		new Thread(() -> {
			try {
				FileInputStream fis = new FileInputStream(new File(filepath));
				ByteBuffer buf = ByteBuffer.wrap(fis.readAllBytes());
				onFinished.onFinished(buf);
				
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, "Read File from: " + filepath).run();
	}
	
	public static void readImageFromFile(String filepath, ImageLoadEvent onFinished) {
		new Thread(() -> {
			try {
				BufferedImage img = ImageIO.read(new File(filepath));
				
				onFinished.onFinished(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, "Read Image from: " + filepath).run();
	}
	
	public static void readObjectFromFile(String filepath, ObjectLoadEvent onFinished) {
		new Thread(() -> {
			try {
				FileInputStream fis = new FileInputStream(new File(filepath));
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				fis.close();
				
				onFinished.onFinished(obj);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}, "Read Object from: " + filepath).run();
	}

}
