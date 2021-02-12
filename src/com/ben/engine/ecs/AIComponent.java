package com.ben.engine.ecs;

import java.util.List;

import com.ben.engine.ai.FSMGraph;

public class AIComponent extends Component {

	private FSMGraph brain;
	
	public AIComponent(GameObject parent, FSMGraph brain) {
		super(parent);
		
		this.brain = brain;
	}
	
	@Override
	public boolean update(List<GameObject> objects) {
		brain.runFSM(parent, objects);
		
		return false;
	}

	@Override
	public String toSerializedString() {
		return "AIComponent - TODO: Serialization of FSM";
	}

}
