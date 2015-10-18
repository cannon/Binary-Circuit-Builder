package com.github.jacksonc.circuitbuilder;

public class OnGate extends LogicGate {

	public OnGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "ON";
	}

	@Override
	public void think() {
		buffer = true;
	}
}
