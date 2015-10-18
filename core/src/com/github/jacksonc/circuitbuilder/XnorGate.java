package com.github.jacksonc.circuitbuilder;

public class XnorGate extends LogicGate {

	public XnorGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "XNOR";
		this.inputs.add(null);
		this.inputs.add(null);
	}

	public void think() {
		buffer = Util.gateOutput(inputs.get(0)) == Util.gateOutput(inputs.get(1));
	}
	
	public int getMaxInputs() {
		return 2;
	}
}
