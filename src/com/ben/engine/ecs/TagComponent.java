package com.ben.engine.ecs;

public class TagComponent extends Component {

	private final String TAG;
	
	public TagComponent(GameObject parent, String tag) {
		super(parent);
		
		this.TAG = tag;
	}
	
	public final String getTag() {
		return TAG;
	}

	@Override
	public String toSerializedString() {
		return "Tag: " + TAG;
	}

}
