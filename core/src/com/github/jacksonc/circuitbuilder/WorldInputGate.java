package com.github.jacksonc.circuitbuilder;

public class WorldInputGate extends LogicGate {
	
	public void think() {
		//doesn't do anything in think.
	}
	
	public void setInitialState(boolean initialState) {
		output = initialState;
	}
	

}
