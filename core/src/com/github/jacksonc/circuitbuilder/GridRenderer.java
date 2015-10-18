package com.github.jacksonc.circuitbuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

public class GridRenderer {
	private static final int SPACING = 32;
	
	private Texture texture;
	private TextureRegion textureRegion;
	private TiledDrawable tiledDrawable;
	
	private SpriteBatch renderer;
	
	public GridRenderer(SpriteBatch renderer) {
		this.renderer = renderer;
		texture = new Texture(Gdx.files.internal("grid.png"));
		textureRegion = new TextureRegion(texture);
		tiledDrawable = new TiledDrawable(textureRegion);
	}
	
	public void render(float x, float y, float zoom) {
		//Gdx.graphics.getWidth();
		tiledDrawable.draw(renderer, 32*(float)Math.floor((x-(Gdx.graphics.getWidth()*0.5f*zoom))/32.0), 32*(float)Math.floor((y-(Gdx.graphics.getHeight()*0.5f*zoom))/32.0), (Gdx.graphics.getWidth()*zoom)+64, (Gdx.graphics.getHeight()*zoom)+64);
		//renderer.draw(texture, x-(Gdx.graphics.getWidth()*0.5f*zoom), y-(Gdx.graphics.getHeight()*0.5f*zoom),x+(Gdx.graphics.getWidth()*zoom), y+(Gdx.graphics.getHeight()*zoom), -1,0,1,1);
	}
}
