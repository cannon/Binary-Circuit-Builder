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
	public UiTable(final Manager manager) {
		super();
		Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		
		leftTable = new Table();
		//leftTable.setDebug(true);
		
		this.right().top();
		
		this.add(leftTable.pad(20));
		
		int spacing = 14;
		
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
			
			leftTable.add(button).space(spacing);
			leftTable.row();
		}
		
		for(int i = 1; i < Actions.values().length; ++i) {
			final Actions action = Actions.values()[i];
			TextButton button = new TextButton(action.toString(), uiSkin);
			
			button.addListener(new ChangeListener() {
		        @Override
		        public void changed (ChangeEvent event, Actor actor) {
		            InputHandler.action = action;
		            
		            highlight(actor);
		        }
		    });
			
			leftTable.add(button).space(spacing);
			leftTable.row();
		}
		
		TextButton speedButton = new TextButton("Speed: Medium", uiSkin);
		speedButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				float time = manager.getTimePerFrame();
				if (time == 0.1f) {
					((TextButton) actor).setText("Speed: Fast");
					manager.setTimePerFrame(0.02f);
				} else if (time == 0.02f) {
					manager.setTimePerFrame(0.5f);
					((TextButton) actor).setText("Speed: Slow");
				} else {
					manager.setTimePerFrame(0.1f);
					((TextButton) actor).setText("Speed: Medium");
				}
			}
		});
		
		leftTable.add(speedButton).space(20);
	}
	
	private void highlight(Actor actor) {
		for (Actor children : actor.getParent().getChildren()) {
        	((TextButton) children).setColor(Color.WHITE);
        }
        
        ((TextButton) actor).setColor(Color.YELLOW);
	}
}


// Select gate types from list on the left.
// Click on game world to add a gate.
// Select wire and click and drag to connect gate notes (grey squares).
// Select delete and click on a gate to delete it.
