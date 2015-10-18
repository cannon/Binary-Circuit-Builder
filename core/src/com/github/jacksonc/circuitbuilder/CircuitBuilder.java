package com.github.jacksonc.circuitbuilder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

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
	private Table table;
	

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shape = new ShapeRenderer();
		sprite = new SpriteBatch();
		manager = new Manager();
		
		inputHandler = new InputHandler(this,manager);
	    stage = new Stage(new ScreenViewport());
	    InputMultiplexer inputMultiplexer = new InputMultiplexer();
	    inputMultiplexer.addProcessor(stage);
	    inputMultiplexer.addProcessor(inputHandler);
	    Gdx.input.setInputProcessor(inputMultiplexer);
	    
	    table = new UiTable();
	    table.setFillParent(true);
	    stage.addActor(table);
	   // table.setDebug(true);
	    
		renderer = new GridRenderer(sprite);

		
		font = new BitmapFont();
		font.setColor(Color.BLACK);
	}

	@Override
	public void render () {
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
			shape.setColor(Color.GRAY);
			
			for (int i = 0; i < g.getInputs().size(); i++) {
				shape.box((info.x + i) * 32 + 10, (info.y + info.height - 1) * 32 - 12, 0, 12, 12, 0);
			}
			shape.box(info.x * 32 + 10, (info.y + info.height) * 32,	
					0, 12, 12, 0);
		}
		for (Gate g : manager.getGates()) {
			DrawInfo info = g.getDrawInfo();
			for (int i = 0; i < g.getInputs().size(); i++) {
				Gate input = g.getInputs().get(i);
				
				if(input!=null){
					DrawInfo inputsInfo = input.getDrawInfo();
					
					shape.setColor(Color.WHITE);
					if (input.getOutput()) {
						shape.setColor(Color.BLUE);
					}
					
					//draws line between input and current gate
					shape.rectLine(inputsInfo.x * 32 + 16, (inputsInfo.y + inputsInfo.height) * 32 + 6,
							(info.x + i) * 32 + 16, info.y * 32 -6 , 4);
					
				}

			}
		}
		for (Gate g : manager.getGates()) {
			shape.setColor(Color.WHITE);
			DrawInfo info = g.getDrawInfo();
			shape.box(info.x*32, info.y*32, 0, info.width*32, info.height*32, 0);
		}
		shape.end();
		
		sprite.begin();
		for (Gate g : manager.getGates()) {
			DrawInfo info = g.getDrawInfo();
			font.draw(sprite, info.name, info.x*32 + info.width*16 - 12, info.y*32 + 21);
		}
		sprite.end();
		
		//stage.act(delta);
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
