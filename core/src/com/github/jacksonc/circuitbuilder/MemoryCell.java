package com.github.jacksonc.circuitbuilder;

public class MemoryCell extends LogicGate{
	
	public MemoryCell(int x, int y) {
		super(x, y);
		this.drawInfo.name = "MEM";
	}

	private boolean state = false;
	
	//If input at index 0 is true, this sets to false. If input at index 1 is
	//true, this sets to true. If both are true or false, nothing changes.
	public void think() {
		if (Util.gateOutput(inputs.get(0)) != Util.gateOutput(inputs.get(1))) {
			state = Util.gateOutput(inputs.get(1));
		}
		buffer = state;
	}
	
	public int getMaxInputs() {
		return 2;
	}
}
