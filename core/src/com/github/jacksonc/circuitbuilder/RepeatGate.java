package com.github.jacksonc.circuitbuilder;

public class RepeatGate extends LogicGate {
	
	public RepeatGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "REP";
		this.inputs.add(null);
	}

	@Override
	public void think() {
		buffer = Util.gateOutput(inputs.get(0));
	}
	

}
