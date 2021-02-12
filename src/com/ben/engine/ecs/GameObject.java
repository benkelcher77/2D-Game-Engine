package com.ben.engine.ecs;

import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.ben.engine.Application;
import com.ben.engine.scripting.BasicScript;
import com.ben.engine.scripting.NativeScriptComponent;
import com.ben.engine.util.UID;

public class GameObject {
	
	public Application application;
	public Scene scene;
	
	private List<Component> components;
	private final int id;
	
	public GameObject(Application application) {
		this(application, null);
	}
	
	public GameObject(Application application, Scene scene) {
		this.application = application;
		this.scene = scene;
		
		components = new ArrayList<>();
		id = UID.unique();
	}
	
	public GameObject(Application application, Scene scene, int id) {
		this.application = application;
		this.scene = scene;
		this.id = id;
		UID.register(id);
		
		components = new ArrayList<>();
	}
	
	public boolean update(List<GameObject> objects) {
		for (Component component : components)
			if (component.update(objects))
				return true;
		
		return false;
	}
	
	public void onDestroy() {
		for (Component c : components)
			c.onDestroy();
	}
	
	public void render(Graphics2D g) {
		for (Component component : components)
			component.render(g);
	}

	public <T extends Component> T addComponent(T component) {
		component.onCreate();
		components.add(component);
		
		return (T)component;
	}
	
	public <T extends Component> T addComponent(Class<T> klass, Object... constructorArguments) {
		Object[] argumentsWithPlayer = new Object[constructorArguments.length + 1];
		argumentsWithPlayer[0] = this;
		for (int i = 0; i < constructorArguments.length; i++)
			argumentsWithPlayer[i + 1] = constructorArguments[i];
		
		Class<?>[] argumentTypes = new Class<?>[argumentsWithPlayer.length];
		for (int i = 0; i < argumentTypes.length; i++) {
			argumentTypes[i] = argumentsWithPlayer[i].getClass();
			
			if (argumentTypes[i].equals(Byte.class))
				argumentTypes[i] = byte.class;
			if (argumentTypes[i].equals(Short.class))
				argumentTypes[i] = short.class;
			if (argumentTypes[i].equals(Character.class))
				argumentTypes[i] = char.class;
			if (argumentTypes[i].equals(Integer.class))
				argumentTypes[i] = int.class;
			if (argumentTypes[i].equals(Long.class))
				argumentTypes[i] = long.class;
			if (argumentTypes[i].equals(Float.class))
				argumentTypes[i] = float.class;
			if (argumentTypes[i].equals(Double.class))
				argumentTypes[i] = double.class;
			if (argumentTypes[i].equals(Boolean.class))
				argumentTypes[i] = boolean.class;
		}
		
		try {
			T comp = klass.getDeclaredConstructor(argumentTypes).newInstance(argumentsWithPlayer);
			comp.onCreate();
			components.add(comp);
			
			return comp;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public <T extends BasicScript> NativeScriptComponent addNativeScript(Class<T> klass) {
		return addComponent(new NativeScriptComponent(this).bind(klass));
	}
	
	public void removeComponent(Component component) {
		component.onDestroy();
		components.remove(component);
	}
	
	public <T extends Component> T getComponent(Class<T> klass) {
		for (Component component : components) {
			if (klass.isAssignableFrom(component.getClass()))
				return klass.cast(component);
		}
		
		return null;
	}
	
	public <T extends BasicScript> T getScript(Class<T> klass) {
		for (Component component : components) {
			if (component instanceof NativeScriptComponent) {
				NativeScriptComponent script = (NativeScriptComponent)component;
				if (klass.isAssignableFrom(script.instance.getClass()))
					return klass.cast(script.instance);
			}
		}
		
		return null;
	}
	
	public List<Component> getComponents() {
		return components;
	}
	
	public final int getID() {
		return id;
	}

}
