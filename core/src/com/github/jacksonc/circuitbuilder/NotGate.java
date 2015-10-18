package com.github.jacksonc.circuitbuilder;

public class NotGate extends LogicGate {

	public NotGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "NOT";
	}

	public void think() {
		buffer = !Util.gateOutput(inputs.get(0));
	}
}
