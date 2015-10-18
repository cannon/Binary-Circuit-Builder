package com.github.jacksonc.circuitbuilder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CircuitBuilder extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private ShapeRenderer shape;
	private SpriteBatch sprite;
	private GridRenderer renderer;
	private InputHandler inputHandler;
	private Manager manager;
	private BitmapFont font;
	private Stage stage;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shape = new ShapeRenderer();
		sprite = new SpriteBatch();
		inputHandler = new InputHandler(this);
		Gdx.input.setInputProcessor(inputHandler);
		renderer = new GridRenderer(sprite);
		
		manager = new Manager();
		
		font = new BitmapFont();
		font.setColor(Color.BLACK);
	}

	@Override
	public void render (float delta) {
		inputHandler.update();
		manager.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sprite.setProjectionMatrix(camera.combined);
		sprite.begin();
		renderer.render(camera.position.x, camera.position.y, camera.zoom);
		sprite.end();
		
		shape.setProjectionMatrix(camera.combined);
		shape.begin(ShapeType.Filled);
		for (Gate g : manager.getGates()) {
			DrawInfo info = g.getDrawInfo();
			shape.box(info.x*32, info.y*32, 0, info.width*32, info.height*32, 0);
			for (Gate input : g.getInputs()) { 
				DrawInfo inputsInfo = input.getDrawInfo();
				int correctedX = inputsInfo.x + inputsInfo.width;
				
				if (input.getOutput()) {
					shape.setColor(Color.BLUE);
				}
				
				shape.rectLine(correctedX * 32, inputsInfo.y * 32 + 16,
						info.x * 32, info.y * 32 + 16, 4);
				
				shape.setColor(Color.WHITE);
			}
		}
		shape.end();
		
		sprite.begin();
		for (Gate g : manager.getGates()) {
			DrawInfo info = g.getDrawInfo();
			font.draw(sprite, info.name, info.x*32 + 8, info.y*32 + 21);
		}
		sprite.end();
		
		stage.act(delta);
	    stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		stage.getViewport().update(width, height, true);
	}
	
	public void setCamera(float x, float y, float scale) {
		camera.position.x = x;
		camera.position.y = y;
		camera.zoom = scale;
		camera.update();
	}
	
	public void dispose() {
	    stage.dispose();
	}

}
