package com.github.jacksonc.circuitbuilder;

import java.util.List;

public abstract class LogicGate implements Gate {
	
	protected boolean buffer;
	protected boolean output;
	protected List<Gate> inputs;

	@Override
	public boolean getOutput() {
		return output;
	}

	@Override
	public abstract void think();

	@Override
	public void talk() {
		output = buffer;
	}
	
	public List<Gate> getInputs() {
		return inputs;
	}
}
