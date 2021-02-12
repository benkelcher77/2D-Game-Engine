package com.ben.engine.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.ben.engine.ecs.GameObject;
import com.ben.engine.util.Pair;

public class FSMGraph {

	public enum TransitionType { NONE, POP, PUSH, FULL_TRANSITION }
	
	public interface Action { public void onEnable(GameObject parent, List<GameObject> objects); public void act(GameObject parent, List<GameObject> objects); }
	public interface TransitionPredicate { public TransitionType transition(GameObject parent, List<GameObject> objects); }
	
	private Map<Action, List<Pair<TransitionPredicate, Action>>> machine;
	private Stack<Action> actionStack;
	
	public FSMGraph() {
		this.machine = new HashMap<>();
		this.actionStack = new Stack<>();
	}
	
	public void addUnconnectedAction(Action action) {
		machine.put(action, new ArrayList<>());
		
		if (actionStack.isEmpty())
			actionStack.push(action);
	}
	
	public void addTransition(Action source, Action destination, TransitionPredicate transition) {
		if (machine.keySet().contains(source) && machine.keySet().contains(destination)) {
			machine.get(source).add(new Pair<>(transition, destination));
		} else {
			throw new RuntimeException("Attemted to add FSM transition from non-existent source or to non-existent destination!");
		}
	}
	
	public void runFSM(GameObject parent, List<GameObject> objects) {
		if (actionStack.isEmpty())
			return;
		
		// Determine if a transition is needed and if so perform it
		List<Pair<TransitionPredicate, Action>> possibleTransitions = machine.get(actionStack.peek());
		
		TransitionLoop:
		for (Pair<TransitionPredicate, Action> pair : possibleTransitions) {
			TransitionType type = pair.a.transition(parent, objects);
			
			TypeSwitch:
			switch (type) {
				case NONE:
					break TypeSwitch;
				case POP:
					popAction();
					break TransitionLoop;
				case PUSH:
					pair.b.onEnable(parent, objects);
					pushAction(pair.b);
					break TransitionLoop;
				case FULL_TRANSITION:
					pair.b.onEnable(parent, objects);
					replaceCurrentAction(pair.b);
					break TransitionLoop;
			}
		}
		
		// After the possible transition, perform the (possibly new) action
		actionStack.peek().act(parent, objects);
	}
	
	public void pushAction(Action action) {
		if (machine.containsKey(action))
			actionStack.push(action);
		else
			throw new RuntimeException("Attempted to push non-existent action!");
	}
	
	public void popAction() {
		actionStack.pop();
	}
	
	public void replaceCurrentAction(Action action) {
		if (machine.containsKey(action)) {
			actionStack.pop();
			actionStack.push(action);
		} else
			throw new RuntimeException("Attempted to replace current action with non-existent action!");
	}

}
