package com.github.jacksonc.circuitbuilder;

public class AndGate extends LogicGate {

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
