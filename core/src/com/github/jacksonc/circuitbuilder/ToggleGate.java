package com.github.jacksonc.circuitbuilder;

import com.badlogic.gdx.graphics.Color;

public class ToggleGate extends InteractableGate {
	private boolean state;
	
	public ToggleGate(int x, int y) {
		super(x, y);
		state = true;
		this.drawInfo.name = "on";
		this.drawInfo.width = 1;
		this.drawInfo.height = 1;
		this.drawInfo.color = Color.YELLOW;
		output = state;
	}
	
	@Override
	public void interact() {
		state = !state;
		this.drawInfo.name = state ? "on" : "off";
		output = state;
	}
	
	@Override
	public void talk() {}

	@Override
	public void think() {}

}
