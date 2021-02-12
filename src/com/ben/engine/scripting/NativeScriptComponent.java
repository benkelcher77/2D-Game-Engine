package com.ben.engine.scripting;

import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ben.engine.ecs.Component;
import com.ben.engine.ecs.GameObject;

public class NativeScriptComponent extends Component {

	public interface NativeScriptOnCreate { void onCreate(); }
	public interface NativeScriptOnDestroy { void onDestroy(); }
	public interface NativeScriptUpdate { boolean update(List<GameObject> objects); }
	public interface NativeScriptRender { void render(Graphics2D g); }
	
	public BasicScript instance;
	
	public NativeScriptOnCreate onCreate;
	public NativeScriptOnDestroy onDestroy;
	public NativeScriptUpdate update;
	public NativeScriptRender render;
	
	public NativeScriptComponent(GameObject parent) {
		super(parent);
	}
	
	@Override
	public void onCreate() {
		onCreate.onCreate();
	}
	
	@Override
	public void onDestroy() {
		onDestroy.onDestroy();
	}
	
	@Override
	public boolean update(List<GameObject> objects) {
		return update.update(objects);
	}
	
	@Override
	public void render(Graphics2D g) {
		render.render(g);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasicScript> NativeScriptComponent bind(Class<T> type) {
		try {
			instance = type.getDeclaredConstructor().newInstance();
			instance.initialize(parent);
			
			onCreate = () -> ((T)instance).onCreate();
			onDestroy = () -> ((T)instance).onDestroy();
			update = (objects) -> ((T)instance).update(objects);
			render = (g) -> ((T)instance).render(g);
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
		
		return this;
	}

	@Override
	public String toSerializedString() {
		return "NativeScript: " + instance.getClass().getName();
	}

}
