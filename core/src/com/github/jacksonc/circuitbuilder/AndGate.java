package com.github.jacksonc.circuitbuilder;

public class AndGate extends LogicGate {

	public AndGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "AND";
		this.inputs.add(null);
		this.inputs.add(null);
	}

	@Override
	public void think() {
		buffer = true;
		for(Gate g : inputs) {
			if(!Util.gateOutput(g)) {
				buffer = false;
				return;
			}
		}
	}
}
