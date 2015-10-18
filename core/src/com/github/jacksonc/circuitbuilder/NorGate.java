package com.github.jacksonc.circuitbuilder;

public class NorGate extends LogicGate {

	public NorGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "NOR";
		this.inputs.add(null);
		this.inputs.add(null);
	}

	public void think() {
		buffer = true;
		for (Gate g : inputs) {
			if (Util.gateOutput(g)) {
				buffer = false;
				return;
			}
		}
	}
}
