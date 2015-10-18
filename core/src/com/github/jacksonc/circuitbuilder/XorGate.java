package com.github.jacksonc.circuitbuilder;

public class XorGate extends LogicGate {

	public XorGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "XOR";
		this.inputs.add(null);
		this.inputs.add(null);
	}

	@Override
	public void think() {
		buffer = Util.gateOutput(inputs.get(0)) != Util.gateOutput(inputs.get(1));
	}
	
	public int getMaxInputs() {
		return 2;
	}
}
