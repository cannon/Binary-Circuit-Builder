package com.github.jacksonc.circuitbuilder;

public class LightGate extends LogicGate {

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
