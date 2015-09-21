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
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MagicTrick extends ApplicationAdapter {
	public enum GamePhase{
        TITLE_SCREEN, TITLE_PICK_CARD_TRANSITION, PICK_CARD,
        PICK_CARD_SELECT_COLUMN_TRANSITION, SELECT_COLUMN,
        REVEAL_CARD
    }
	
	private Player player;
	private Dealer dealer;
	private Board board; 
	private Deck deck;
	private GamePhase phase = GamePhase.TITLE_SCREEN;
	private float timer = 0f;
	
	//temp will remove this
	private Boolean columnPhase = true;
	
	private BitmapFont font;
	private String text;
	private float messageCenter;
	
	private SpriteBatch batch;  //sprites get added to a batch for faster drawing
	private OrthographicCamera camera; //camera for rendering
	
	private Texture background; //background texture
	
	private Stage stage; //Buttons get added to the stage
	private Skin skin;
	
	private HashMap<String, Runnable> buttonMap = new HashMap<String, Runnable>();
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage();
		
		board = new Board();
		player = new Player();
		dealer = new Dealer();
		
		font = new BitmapFont(Gdx.files.internal("magic.fnt"));
		updateMessage("CLICK ANYWHERE TO BEGIN");
		
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
		//Draw the cards whenever the phase is not the title screen or transition
		if(phase != GamePhase.TITLE_SCREEN && phase != GamePhase.TITLE_PICK_CARD_TRANSITION)
			board.draw(batch);
		font.draw(batch, text, messageCenter, 80);
		

		batch.end();
		
		//switch to the transition phase when the mouse is clicked if on the title screen
		if(phase == GamePhase.TITLE_SCREEN && Gdx.input.justTouched()){
			phase = GamePhase.TITLE_PICK_CARD_TRANSITION;
			updateMessage("PREPARE TO BE AMAZED!");
		} 
				
		//wait 5 seconds before transitioning to the pick card phase
		if (phase == GamePhase.TITLE_PICK_CARD_TRANSITION && timer >= 2){
			phase = GamePhase.PICK_CARD;
			player.pickCard();
			updateMessage("PICK A CARD AND CLICK");
			testButtonPress(); //same code from start game button
			timer = 0f;
		} else if (phase == GamePhase.TITLE_PICK_CARD_TRANSITION){
			timer += Gdx.graphics.getDeltaTime();
		}
		
		if(phase == GamePhase.PICK_CARD && Gdx.input.justTouched() && player.getHasSelectedCard()) //Placeholder for player.getHasSelected to move to the select column phase
		{
			phase = GamePhase.SELECT_COLUMN;
			updateMessage("SELECT A COLUMN");
		} //else nothing, wait for player to click to say he is ready
		
		if(phase == GamePhase.SELECT_COLUMN){
			//Will refactor to remove the justTouched pass.  
			//This was for card selection which doesn't need to happen
			//Also this code will become player.SelectColumn
			handleInput(Gdx.input.justTouched());  
		}
		
		if(phase == GamePhase.REVEAL_CARD){
			//placeholder for whatever happens when the card is revealed.
		}
		
		//disabling to show that buttons are not needed
		//Gdx.input.setInputProcessor(stage);
		//stage.draw();
		
		//handleInput(Gdx.input.justTouched());
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
		board = new Board();
		deck = new Deck(cardSpriteSheet);
		deck.shuffle();
		Card[] drawnCards;
		drawnCards = deck.random21();
		float xPos = 180;
		float yPos = 400;
		for(int i = 0; i < drawnCards.length; i++){
			drawnCards[i].setPos(xPos * ((i%3) + 1), yPos);
			board.addToColumn(i % 3, drawnCards[i]);
			if(i%3 == 2)
				yPos -= Card.CARD_HEIGHT/2;
		}
		
		System.out.println("Test Button Pressed");
	}
	
	private void handleInput(Boolean clicked){
		int x1 = Gdx.input.getX();
		int y1 = Gdx.input.getY();
		Vector3 input = new Vector3(x1, y1, 0);
		camera.unproject(input);
		if(columnPhase)
			board.columnClicked(input.x, input.y, clicked);
	}
	
	private void updateMessage(String m){
		text = m;
		GlyphLayout glyphLayout = new GlyphLayout();
		glyphLayout.setText(font, text);
		float w = glyphLayout.width;
		messageCenter = (Gdx.graphics.getWidth()/2) - (w/2);
	}

}
