package com.github.jacksonc.circuitbuilder;

public class XorGate extends LogicGate {

	@Override
	public void think() {
		buffer = Util.gateOutput(inputs.get(0)) != Util.gateOutput(inputs.get(1));
	}
}
