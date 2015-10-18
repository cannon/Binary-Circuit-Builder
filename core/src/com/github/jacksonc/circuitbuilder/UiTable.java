package com.github.jacksonc.circuitbuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.github.jacksonc.circuitbuilder.LogicGate.GateTypes;
import com.github.jacksonc.circuitbuilder.InputHandler;
import com.github.jacksonc.circuitbuilder.InputHandler.Actions;

public class UiTable extends Table {
	public Skin uiSkin;
	public Table leftTable;
	public UiTable() {
		super();
		Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		
		leftTable = new Table();
		//leftTable.setDebug(true);
		
		this.right().top();
		
		this.add(leftTable.pad(20));
		
		for(GateTypes g : GateTypes.values()) {
			TextButton button = new TextButton(g.toString(), uiSkin);
	
			button.addListener(new ChangeListener() {
		        @Override
		        public void changed (ChangeEvent event, Actor actor) {
		            GateTypes gateType = GateTypes.valueOf(((TextButton)actor).getLabel().getText().toString());
		            InputHandler.action = Actions.MAKE;
		            InputHandler.gateType = gateType;
		            
		            highlight(actor);
		        }
		    });
			
			leftTable.add(button).space(20);
			leftTable.row();
		}
		
		TextButton button = new TextButton("Wire", uiSkin);
		
		button.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            InputHandler.action = Actions.WIRE;
	            
	            highlight(actor);
	        }
	    });
		
		leftTable.add(button).space(20);
		leftTable.row();
		
		TextButton button2 = new TextButton("Delete", uiSkin);
		
		button2.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            InputHandler.action = Actions.DELETE;   
	            
	            highlight(actor);
	        }
	    });
		
		leftTable.add(button2).space(20);
		leftTable.row();
		
	}
	
	private void highlight(Actor actor) {
		for (Actor children : actor.getParent().getChildren()) {
        	((TextButton) children).setColor(Color.WHITE);
        }
        
        ((TextButton) actor).setColor(Color.YELLOW);
	}
}
