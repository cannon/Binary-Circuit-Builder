package com.github.jacksonc.circuitbuilder;

public class NandGate extends LogicGate {

	public NandGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "NAND";
		this.inputs.add(null);
		this.inputs.add(null);
	}

	@Override
	public void think() {
		buffer = false;
		for(Gate g : inputs) {
			if(!Util.gateOutput(g)) {
				buffer = true;
				return;
			}
		}
	}
}
