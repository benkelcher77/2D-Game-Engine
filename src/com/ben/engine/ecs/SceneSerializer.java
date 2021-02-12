package com.ben.engine.ecs;

public class SceneSerializer {
	
	private SceneSerializer() { }
	
	public static String serialize(Scene scene) {
		StringBuilder sb = new StringBuilder("Scene: Untitled\n"); // TODO: Add names for scenes
		for (GameObject go : scene.getObjects()) {
			sb.append("GameObject: ").append(go.getID()).append("\n");
			for (Component component : go.getComponents()) 
				sb.append("\t").append(component.toSerializedString()).append("\n");
		}
		
		return sb.toString();
	}

}
