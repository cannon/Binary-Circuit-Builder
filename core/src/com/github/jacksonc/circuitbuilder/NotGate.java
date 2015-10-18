package com.github.jacksonc.circuitbuilder;

public class NotGate extends LogicGate {

	public void think() {
		buffer = !Util.gateOutput(inputs.get(0));
	}
}
