package com.github.jacksonc.circuitbuilder;

public class XnorGate extends LogicGate {

	public void think() {
		buffer = Util.gateOutput(inputs.get(0)) == Util.gateOutput(inputs.get(0));
	}
}
