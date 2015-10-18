package com.github.jacksonc.circuitbuilder;

public class NotGate extends LogicGate {

	public NotGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "NOT";
		this.drawInfo.width = 1;
		this.inputs.add(null);
	}

	public void think() {
		buffer = !Util.gateOutput(inputs.get(0));
	}
	
	public int getMaxinputs() {
		return 1;
	}
}
