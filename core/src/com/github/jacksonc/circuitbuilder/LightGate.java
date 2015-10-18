package com.github.jacksonc.circuitbuilder;

import com.badlogic.gdx.graphics.Color;

public class LightGate extends LogicGate {

	public LightGate(int x, int y) {
		super(x, y);
		this.drawInfo.name = "";
		this.inputs.add(null);
		drawInfo.hasOutput = false;
		drawInfo.width = 1;
		drawInfo.color = Color.RED;
	}
	
	@Override
	public void talk() {
		drawInfo.color = buffer ? Color.GREEN : Color.RED;
	}

	@Override
	public void think() {
		buffer = false;
		for(Gate g : inputs) {
			if(Util.gateOutput(g)) {
				buffer = true;
				return;
			}
		}
	}
}
