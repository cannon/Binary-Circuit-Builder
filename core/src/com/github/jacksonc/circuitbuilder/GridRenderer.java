package com.github.jacksonc.circuitbuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GridRenderer {
	private ShapeRenderer renderer;
	private static final int SPACING = 15;
	public GridRenderer(ShapeRenderer renderer) {
		this.renderer = renderer;
	}
	
	public void render(float x, float y, float zoom) {
		for (int i = 0; i < Gdx.graphics.getWidth() * 2 / SPACING; i++) {
			int xCoord = (i * SPACING) - Gdx.graphics.getWidth();
			renderer.line(xCoord, - Gdx.graphics.getHeight(), xCoord, Gdx.graphics.getHeight());
		}
	}
}
