package com.github.jacksonc.circuitbuilder;

public abstract class InteractableGate extends LogicGate {
	public InteractableGate(int x, int y) {
		super(x, y);
	}

	public abstract void interact();

}
