package com.ben.engine.ecs;

import com.ben.engine.audio.AudioManager;
import com.ben.engine.audio.Sound;

public class AudioComponent extends Component {

	private Sound sound;
	private String filepath;
	
	public AudioComponent(GameObject parent, String filepath) {
		super(parent);
		
		this.filepath = filepath;
		this.sound = AudioManager.loadSound(filepath);
	}
	
	public void play() {
		sound.play();
	}

	@Override
	public String toSerializedString() {
		return "AudioComponent: " + filepath;
	}

}
