package com.github.jacksonc.circuitbuilder;

public class OnGate extends LogicGate {

	@Override
	public void think() {
		buffer = true;
	}
}
