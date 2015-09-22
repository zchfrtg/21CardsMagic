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
        TITLE_SCREEN, TRANSITION1, PICK_CARD,
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
		
		
		font = new BitmapFont(Gdx.files.internal("magic.fnt"));
		updateMessage("CLICK ANYWHERE TO BEGIN");
		
		batch = new SpriteBatch();
		background = new Texture("magicBackground.jpg");
		
		//create the Texture and pass it to the deck class where it will be
		//split and added to each card
		Texture cardSpriteSheet = new Texture("cards.png");
		deck = new Deck(cardSpriteSheet);
		dealer = new Dealer(board, deck, player);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(background, 0, 0);
		//Draw the cards whenever the phase is not the title screen or transition
		if(phase != GamePhase.TITLE_SCREEN && phase != GamePhase.REVEAL_CARD)
			board.draw(batch);
		else if (phase == GamePhase.REVEAL_CARD)
			board.drawReveal(batch);
		font.draw(batch, text, messageCenter, 80);
		

		batch.end();
		
		//switch to the transition phase when the mouse is clicked if on the title screen
		if(phase == GamePhase.TITLE_SCREEN && Gdx.input.justTouched()){
			phase = GamePhase.TRANSITION1;
			updateMessage("PREPARE TO BE AMAZED!");
			startGame(); //same code from start game button
		} 
				
		//wait 5 seconds before transitioning to the pick card phase
		if (phase == GamePhase.TRANSITION1 && timer >= 4){
			phase = GamePhase.PICK_CARD;
			player.pickCard();
			updateMessage("PICK A CARD AND CLICK");
			timer = 0f;
		} else if (phase == GamePhase.TRANSITION1){
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
			if(dealer.dealNumber() > 3)
				phase = GamePhase.REVEAL_CARD;
			
			handleInput(Gdx.input.justTouched()); 
			if(player.getColumnSelected() != -1){
				if(dealer.dealNumber() > 3)
					phase = GamePhase.REVEAL_CARD;
				else {
					dealer.pickupCards();
					dealer.deal();
					player.setColumnSelected(-1);
				}
			}
			
		}
		
		if(phase == GamePhase.REVEAL_CARD){
			updateMessage("ARE YOU AMAZED?");
		}
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
	
	private void startGame() {
		dealer.deal();
		System.out.println("Magic has begun");
	}
	
	private void handleInput(Boolean clicked){
		int x1 = Gdx.input.getX();
		int y1 = Gdx.input.getY();
		Vector3 input = new Vector3(x1, y1, 0);
		camera.unproject(input);
		if(phase == GamePhase.SELECT_COLUMN)
			board.columnClicked(input.x, input.y, clicked, player);
	}
	
	private void updateMessage(String m){
		text = m;
		GlyphLayout glyphLayout = new GlyphLayout();
		glyphLayout.setText(font, text);
		float w = glyphLayout.width;
		messageCenter = (Gdx.graphics.getWidth()/2) - (w/2);
	}

}
