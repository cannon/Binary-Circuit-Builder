package com.github.jacksonc.circuitbuilder;

import java.util.HashSet;
import java.util.Set;

// Controls the updating of the gates. Contains methods for connecting gates and changing
// how much time occurs between tick/tock calls.

public class Manager {
	
	private float timePerFrame = 1f; //Seconds between each tick/tock (timePerFrame * 2 is time between a single tick)
	private float timeSinceLastFrame = 0f;
	private boolean tick = false;
	
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
		destination.getInputs().add(source);
	}
	
	// Overloaded if you want to insert an input at a certain point on the list.
	public void connectGates(Gate source, Gate destination, int index) {
		destination.getInputs().add(index, source);
		//check if full
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
}


