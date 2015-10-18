package com.github.jacksonc.circuitbuilder;

import com.badlogic.gdx.graphics.Color;

public class DrawInfo {
	public int x;
	public int y;
	public int width;
	public int height;
	public String name;
	public Color color;
	public boolean hasOutput;
	
	public DrawInfo(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = "";
		
		color = Color.WHITE;
		hasOutput = true;
	}

}
