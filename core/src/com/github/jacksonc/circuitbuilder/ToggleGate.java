package com.github.jacksonc.circuitbuilder;

public class ToggleGate extends InteractableGate {
	private boolean state;
	
	public ToggleGate(int x, int y) {
		super(x, y);
		state = false;
		this.drawInfo.name = "Off";
		this.drawInfo.width = 1;
		this.drawInfo.height = 1;
		output = state;
	}
	
	@Override
	public void interact() {
		state = !state;
		this.drawInfo.name = state ? "On" : "Off";
		output = state;
	}
	
	@Override
	public void talk() {}

	@Override
	public void think() {}

}
