package com.github.jacksonc.circuitbuilder;

public class OrGate extends LogicGate {

	public OrGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "OR";
		this.inputs.add(null);
		this.inputs.add(null);
	}

	@Override
	public void think() {
		buffer = false;
		for(Gate g : inputs) {
			if(Util.gateOutput(g)) {
				buffer = true;
				return;
			}
		}
	}
}
