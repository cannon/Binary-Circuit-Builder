package com.github.jacksonc.circuitbuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {
	
	private CircuitBuilder circuitBuilder;
	private int lastMouseX = 0;
	private int lastMouseY = 0;
	private boolean dragging = false;
	private float camX = 0f;
	private float camY = 0f;
	private float camZoom = 1f;

	public InputHandler(CircuitBuilder circuitBuilder) {
		this.circuitBuilder = circuitBuilder;
	}

	public void update() {
		// TODO Auto-generated method stub
		if(this.dragging){
			System.out.println("draggin");
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
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if(button==1){ this.dragging=false; }
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
		return false;
	}

}
