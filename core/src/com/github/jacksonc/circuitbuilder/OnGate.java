package com.github.jacksonc.circuitbuilder;

public class OnGate extends LogicGate {

	public OnGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "ON";
		this.getDrawInfo().width = 1;
	}

	@Override
	public void think() {
		buffer = true;
	}
	
	public int getMaxInputs() {
		return 0;
	}
}
