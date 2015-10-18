package com.github.jacksonc.circuitbuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.github.jacksonc.circuitbuilder.LogicGate.GateTypes;
import com.github.jacksonc.circuitbuilder.Manager;

public class InputHandler implements InputProcessor {
	
	public static enum Actions {
		MAKE,
		WIRE,
		DELETE,
		INTERACT
	}
	
	public static Actions action = Actions.MAKE;
	public static GateTypes gateType = GateTypes.AND;
	public static Gate firstDragGate = null;
	public static int firstDragInput = -1; //-1 means the output
	
	private CircuitBuilder circuitBuilder;
	private Manager manager;
	private int lastMouseX = 0;
	private int lastMouseY = 0;
	private boolean dragging = false;
	private float camX = 0f;
	private float camY = 0f;
	private float camZoom = 1f;

	public InputHandler(CircuitBuilder circuitBuilder,Manager manager) {
		this.circuitBuilder = circuitBuilder;
		this.manager = manager;
	}

	public void update() {
		// TODO Auto-generated method stub
		if(this.dragging){
			camX-=camZoom*(Gdx.input.getX()-lastMouseX);
			camY+=camZoom*(Gdx.input.getY()-lastMouseY);
		}
		lastMouseX = Gdx.input.getX();
		lastMouseY = Gdx.input.getY();
		circuitBuilder.setCamera(camX, camY, camZoom);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if(button==1){ this.dragging=true; }
		if(button==0){
			switch(action){
			case MAKE:
				int makeX = gridUnderScreenX(Gdx.input.getX());
				int makeY = gridUnderScreenY(Gdx.input.getY());
				LogicGate g = (LogicGate)manager.makeGate(gateType,makeX,makeY);
				DrawInfo info = g.getDrawInfo();
				int inputSize = g.getInputs().size();
				manager.getGates().remove(g);
				boolean make = true;
				for(int x=info.x;x<info.x+info.width;x++){
					for(int y=info.y;y<info.y+info.height;y++){
						if(manager.findGate(x, y)) { make=false; }
					}
				}
				for(int x=info.x;x<info.x+inputSize;x++){
					if(manager.findGate(x, info.y-1)) { make=false; }
				}
				
				if(manager.findGate(info.x, info.y+info.height)) { make=false; }
				
				if(make) { manager.makeGate(gateType,makeX,makeY); } 

				break;
			case WIRE:
				firstDragGate = null;
				firstDragInput = -1;
				if(manager.findGate(gridUnderScreenX(Gdx.input.getX()),gridUnderScreenY(Gdx.input.getY()))){
					if(manager.gateAtPointType==2){
						firstDragGate = manager.gateAtPoint;
						firstDragInput = manager.gateAtPointInput;
					}
					if(manager.gateAtPointType==3){
						firstDragGate = manager.gateAtPoint;
					}
				}
				break;
			case DELETE:
				if(manager.findGate(gridUnderScreenX(Gdx.input.getX()),gridUnderScreenY(Gdx.input.getY()))){
					manager.gateAtPoint.delete(manager);
				}
				break;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if(button==1){ this.dragging=false; }
		if(button==0 && action == Actions.WIRE && firstDragGate!=null ){
			if(manager.findGate(gridUnderScreenX(Gdx.input.getX()),gridUnderScreenY(Gdx.input.getY()))){
				if(manager.gateAtPointType==2 && firstDragInput==-1){
					manager.connectGates(firstDragGate, manager.gateAtPoint, manager.gateAtPointInput);
				}
				if(manager.gateAtPointType==3 && firstDragInput>=0){
					manager.connectGates(manager.gateAtPoint, firstDragGate, firstDragInput);
				}
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		camZoom += amount * camZoom * 0.2f;
		camZoom = Math.max(0.1f, Math.min(camZoom, 10f));
		return false;
	}
	
	public int gridUnderScreenX(int sx) {
		return ((int)Math.floor(((((float)(sx-(Gdx.graphics.getWidth()/2)))*camZoom)+camX)/32f));
	}

	public int gridUnderScreenY(int sy) {
		return ((int)Math.floor(((((float)((Gdx.graphics.getHeight()/2)-sy))*camZoom)+camY)/32f));
	}
}
