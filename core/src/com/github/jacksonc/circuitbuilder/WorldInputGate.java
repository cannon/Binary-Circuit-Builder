package com.github.jacksonc.circuitbuilder;

public class WorldInputGate extends LogicGate {
	
	public WorldInputGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "World";
	}

	public void think() {
		//doesn't do anything in think.
	}
	
	public void talk() {
		
	}
	
	public void setInitialState(boolean initialState) {
		output = initialState;
	}
	
	public int getMaxInputs() {
		return 0;
	}
	

}
