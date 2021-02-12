package com.ben.engine.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

public class FileWriter {

	public interface FileWriteEvent { void onFinished(); }
	public interface ImageWriteEvent { void onFinished(); }
	public interface ObjectWriteEvent { void onFinished(); }
	
	private FileWriter() { }
	
	public static void writeToFile(String filepath, byte[] data) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(filepath));
		fos.write(data);
		fos.flush();
		fos.close();
	}
	
	public static void writeImageToFile(String filepath, BufferedImage img) throws IOException {
		ImageIO.write(img, "PNG", new File(filepath));
	}
	
	public static void writeObjectToFile(String filepath, Object object) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(filepath));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(object);
		fos.flush();
		fos.close();
	}
	
	public static void writeToFile(String filepath, byte[] data, FileWriteEvent onFinished) {
		new Thread(() -> {
			try {
				FileOutputStream fos = new FileOutputStream(new File(filepath));
				fos.write(data);
				fos.flush();
				fos.close();
				
				onFinished.onFinished();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, "Read File from " + filepath).run();
	}
	
	public static void writeImageToFile(String filepath, BufferedImage img, ImageWriteEvent onFinished) {
		new Thread(() -> {
			try {
				ImageIO.write(img, "PNG", new File(filepath));
				onFinished.onFinished();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, "Read Image from " + filepath).run();
	}
	
	public static void writeObjectToFile(String filepath, Object object, ObjectWriteEvent onFinished) {
		new Thread(() -> {
			try {
				FileOutputStream fos = new FileOutputStream(new File(filepath));
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(object);
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, "Read Object from " + filepath).run();
	}

}
