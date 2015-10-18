package com.github.jacksonc.circuitbuilder;

public class NorGate extends LogicGate {

	public void think() {
		buffer = true;
		for (Gate g : inputs) {
			if (g.getOutput()) {
				buffer = false;
				return;
			}
		}
	}
}
