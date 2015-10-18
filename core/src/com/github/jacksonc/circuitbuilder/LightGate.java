package com.github.jacksonc.circuitbuilder;

public class LightGate extends LogicGate {

	public LightGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "L";
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
