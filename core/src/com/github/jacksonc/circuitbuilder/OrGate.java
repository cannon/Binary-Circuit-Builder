package com.github.jacksonc.circuitbuilder;

public class OrGate extends LogicGate {

	@Override
	public void think() {
		buffer = false;
		for(Gate g : inputs) {
			if(g.getOutput()) {
				buffer = true;
				return;
			}
		}
	}
}
