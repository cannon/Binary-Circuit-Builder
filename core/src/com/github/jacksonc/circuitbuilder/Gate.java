package com.github.jacksonc.circuitbuilder;

import java.util.List;

public interface Gate {
	public boolean getOutput();
	public void think();
	public void talk();
	public List<Gate> getInputs();
	public DrawInfo getDrawInfo();
	public int getMaxInputs();
}