package com.github.jacksonc.circuitbuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class LogicGate implements Gate {
	public static enum GateTypes {
		NOT,
		AND,
		OR,
		LIGHT,
		NAND,
		NOR,
		ON,
		XNOR,
		XOR,
	}
	
	protected boolean buffer;
	protected boolean output;
	protected List<Gate> inputs;
	protected DrawInfo drawInfo;
	
	public LogicGate(int x,int y) {
		drawInfo = new DrawInfo(x,y,2,1);
		inputs = new ArrayList<Gate>();
	}

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

	public DrawInfo getDrawInfo() {
		return drawInfo;
	}
	
	public int getMaxInputs() {
		return -1;
	}
}
