package com.github.jacksonc.circuitbuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class LogicGate implements Gate {
	public static enum GateTypes {
		NOT,
		AND,
		OR,
		XOR,	
		NAND,
		NOR,
		XNOR,
		LIGHT,
		TOGGLE,
		MEMORY
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
	
	@Override
	public List<Gate> getInputs() {
		return inputs;
	}

	@Override
	public DrawInfo getDrawInfo() {
		return drawInfo;
	}
	
	@Override
	public int getMaxInputs() {
		return -1;
	}
	
	@Override
	public void delete(Manager manager) {
		for (Gate g : manager.getGates()) {
			while (g.getInputs().contains(this)) {
				int index = g.getInputs().indexOf(this);
				g.getInputs().set(index, null);
			}
		}
		
		manager.getGates().remove(this);
	}
}
