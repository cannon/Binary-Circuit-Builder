package com.github.jacksonc.circuitbuilder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class CircuitBuilder extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private ShapeRenderer shape;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shape = new ShapeRenderer();
		shape.setAutoShapeType(true);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		shape.setProjectionMatrix(camera.combined);
		shape.begin();
		shape.line(new Vector2(0.f, 0.f), new Vector2(100.f, 100.f));
		shape.end();
	}
	
	public void setCamera(float x, float y, float scale) {
		camera.position.x = x;
		camera.position.y = y;
		camera.zoom = scale;
		camera.update();
	}

}
