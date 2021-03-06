package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MagicTrick extends ApplicationAdapter {
	public enum GamePhase{
        TITLE_SCREEN, TRANSITION1, PICK_CARD,
        PICK_CARD_SELECT_COLUMN_TRANSITION, SELECT_COLUMN,
        REVEAL_CARD
    }
	
	//game play related objects
	private Player player;
	private Dealer dealer;
	private Board board; 
	private Deck deck;
	private GamePhase phase = GamePhase.TITLE_SCREEN;
	private float timer = 0f;
	private int previousRan = 0;
	
	//display related objects
	private BitmapFont font;
	private String text;
	private float messageCenter;
	
	private SpriteBatch batch;  //sprites get added to a batch for faster drawing
	private OrthographicCamera camera; //camera for rendering
	
	private Texture background; //background texture
	
	//sound related objects
	private Sound bgMusic;
	private Sound abra;			//Zach's creepy voice
	private Sound abraSong;		//Steve Miller's Band
	private Sound magicSong;	//B.O.B's hit
	private Sound clickSound;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		bgMusic = Gdx.audio.newSound(Gdx.files.internal("music.wav"));
		bgMusic.loop(0.3f);
		abra = Gdx.audio.newSound(Gdx.files.internal("abra.mp3"));
		abraSong = Gdx.audio.newSound(Gdx.files.internal("abraSong.mp3"));
		magicSong = Gdx.audio.newSound(Gdx.files.internal("magic.mp3"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
		
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
		
		if(Gdx.input.justTouched()){
			clickSound.play(.2f);
		}
		
		//switch to the transition phase when the mouse is clicked if on the title screen
		if(phase == GamePhase.TITLE_SCREEN && Gdx.input.justTouched()){
			phase = GamePhase.TRANSITION1;
			updateMessage("PREPARE TO BE AMAZED!");
			startGame(); //same code from start game button
		} 
				
		//wait 3 seconds before transition to Pick Card phase
		if (phase == GamePhase.TRANSITION1 && timer >= 3){
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
			if(dealer.dealNumber() > 3){
				phase = GamePhase.REVEAL_CARD;
				successSound();
			}
			
			handleInput(Gdx.input.justTouched()); 
			if(player.getColumnSelected() != -1){
				dealer.pickupCards();
				dealer.deal();
				player.setColumnSelected(-1);
			}
			
		}
		
		if(phase == GamePhase.REVEAL_CARD){
			updateMessage("ARE YOU AMAZED NOW?");
			if(Gdx.input.justTouched()){
				resetGame();
			}
		}
	}
	@Override
	public void dispose(){					//properly ends the sounds
		clickSound.dispose();
		abra.dispose();
		abraSong.dispose();
		magicSong.dispose();
		bgMusic.dispose();
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
	
	private void resetGame(){
		phase = GamePhase.TITLE_SCREEN;
		updateMessage("CLICK ANYWHERE TO BEGIN");
		board = new Board();
		player = new Player();
		Texture cardSpriteSheet = new Texture("cards.png");
		deck = new Deck(cardSpriteSheet);
		dealer = new Dealer(board, deck, player);
	}
	
	private void successSound(){
		Random r = new Random();
		int ranVar = r.nextInt(3);
		if(ranVar == previousRan){		//will prevent the same success song from playing two turns in a row
			ranVar = (ranVar+1)%3;
		}
		previousRan = ranVar;
		System.out.println(ranVar);
		if(ranVar == 0) {				
			abra.play();				//creepy voice
		} 
		else if(ranVar == 1) {
			abraSong.play();			//Steve Miller's Band
		}
		else {
			magicSong.play(); 			//BOB and Rivers 
		}
	}
	
}
