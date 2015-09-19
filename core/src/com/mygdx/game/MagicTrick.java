package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MagicTrick extends ApplicationAdapter {
	private Board board; 
	private Deck deck;
	
	private SpriteBatch batch;  //sprites get added to a batch for faster drawing
	private OrthographicCamera camera; //camera for rendering
	
	private Texture background; //background texture
	
	private Stage stage; //Buttons get added to the stage
	private Skin skin;
	
	private HashMap<String, Runnable> buttonMap = new HashMap<String, Runnable>();
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,400);
		stage = new Stage();
		
		board = new Board();
		
		batch = new SpriteBatch();
		background = new Texture("magicBackground.jpg");
		
		//create the Texure and pass it to the deck class where it will be
		//split and added to each card
		Texture cardSpriteSheet = new Texture("cards.png");
		deck = new Deck(cardSpriteSheet);
		
		//This has something to do with button setup.  copyright someone in group 4 Assignment 1
		skin = new Skin();
		Pixmap pixmap = new Pixmap(100, 50, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		BitmapFont bitmapfont = new BitmapFont();
		skin.add("default", bitmapfont);
		skin.add("buttonDown", new Texture(Gdx.files.internal("btnDown.png")));
		skin.add("buttonUp", new Texture(Gdx.files.internal("btnUp.png")));
		skin.add("textFieldSkin", new Texture(pixmap));
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("buttonUp"); 
		textButtonStyle.down = skin.newDrawable("buttonDown"); 
		textButtonStyle.font = skin.getFont("default");
		//end button setup
		
		//This make each individual button
		buttonMap.put("testButton", new Runnable() {
			public void run() {
				testButtonPress();
			}
		});
		final TextButton testButton = getButton("Test Button", 20, 20, "testButton", textButtonStyle);
		stage.addActor(testButton);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		board.draw(batch);
		batch.end();
		
		Gdx.input.setInputProcessor(stage);
		stage.draw();
	}
	
	private TextButton getButton(String buttonText, int xPosition,
			int yPosition, final String id, TextButtonStyle textButtonStyle) {
		TextButton button = new TextButton(buttonText, textButtonStyle);
		button.setPosition(xPosition, yPosition);

		button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				buttonMap.get(id).run();
			}
		});
		return button;
	}
	
	private void testButtonPress() {
		// This is just for testing
		Texture cardSpriteSheet = new Texture("cards.png");
		deck = new Deck(cardSpriteSheet);
		deck.shuffle();
		Card[] drawnCards;
		drawnCards = deck.random21();
		float xPos = 180;
		float yPos = 120;
		for(int i = 0; i < drawnCards.length; i++){
			drawnCards[i].setPos(xPos * ((i%3) + 1), yPos);
			board.addToColumn(i % 3, drawnCards[i]);
			if(i%3 == 2)
				yPos += 50;
		}
		System.out.println("Test Button Pressed");
	}
}
