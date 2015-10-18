package com.github.jacksonc.circuitbuilder;

import java.util.HashSet;
import java.util.Set;
import com.github.jacksonc.circuitbuilder.LogicGate.GateTypes;
import com.github.jacksonc.circuitbuilder.InputHandler;
import com.github.jacksonc.circuitbuilder.InputHandler.Actions;

// Controls the updating of the gates. Contains methods for connecting gates and changing
// how much time occurs between tick/tock calls.

public class Manager {
	
	private float timePerFrame = 1f; //Seconds between each tick/tock (timePerFrame * 2 is time between a single tick)
	private float timeSinceLastFrame = 0f;
	private boolean tick = false;
	
	//for getting gate data at a grid location
	public static Gate gateAtPoint = null;
	public static int gateAtPointType = 0; //1=gate 2=input 3=output
	public static int gateAtPointInput = 0; //input index
	
	private Set<Gate> gates = new HashSet<Gate>();
			
	// Updates the game world to reflect change in time (use parameter deltaTime)
	public void update(float deltaTime) {
		timeSinceLastFrame += deltaTime;
		if (timeSinceLastFrame >= timePerFrame) {
			timeSinceLastFrame = timeSinceLastFrame - timePerFrame;
			tick = !tick;
			if (tick) {
				tick();
			} else {
				tock();
			}
		}
	}

	// Adds a source gate to a destination gate's list of inputs
	public void connectGates(Gate source, Gate destination) {
		if (destination.getMaxInputs() == -1 || destination.getInputs().size() + 1 <= destination.getMaxInputs()) {
			destination.getInputs().add(source);
		}
	}
	
	// Overloaded if you want to set an input at a certain point on the list.
	public void connectGates(Gate source, Gate destination, int index) {
		if (destination.getMaxInputs() == -1 || destination.getInputs().size() + 1 <= destination.getMaxInputs()) {
			destination.getInputs().set(index, source);
		}
	}
	
	// Tells all gates to think.
	private void tick() {
		for (Gate g : gates) {
			g.think();
		}	
	}
	
	// Tells all game to talk.
	private void tock() {
		for (Gate g : gates) {
			g.talk();
		}
	}
	
	// Change how long each frame (which is a tick or tock) lasts.
	public void setTimePerFrame(float timePerFrame) {
		this.timePerFrame = timePerFrame;
	}
	
	//Returns reference to list of all gates
	public Set<Gate> getGates() {
		return gates;
	}
	
	public void addGate(Gate gate) {
		gates.add(gate);
	}
	
	public void makeGate(GateTypes gateType,int x,int y){
		switch(gateType){
		case AND:
			gates.add(new AndGate(x,y));
			break;
		case OR:
			gates.add(new OrGate(x,y));
			break;
		case NOT:
			gates.add(new NotGate(x,y));
			break;
		}
	}
	
	public boolean findGate(int x,int y) {
		for (Gate g : gates) {
			DrawInfo info = g.getDrawInfo();
			if(x>=info.x && x<info.x+info.width && y>=info.y && y<info.y+info.height) {
				gateAtPoint = g;
				gateAtPointType = 1;
				return true;
			}
			if(x==info.x && y==info.y+info.height) {
				gateAtPoint = g;
				gateAtPointType = 3;
				return true;
			}
			if(x>=info.x && x<info.x+g.getInputs().size() && y==info.y-1) {
				gateAtPoint = g;
				gateAtPointType = 2;
				gateAtPointInput = x-info.x;
				return true;
			}
		}
		gateAtPoint = null;
		gateAtPointType = 0;
		gateAtPointInput = 0;
		return false;
	}
}


