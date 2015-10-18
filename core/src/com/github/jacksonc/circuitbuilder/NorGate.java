package com.github.jacksonc.circuitbuilder;

public class NorGate extends LogicGate {

	public NorGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "NOR";
	}

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
