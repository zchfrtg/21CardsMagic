package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Dealer {
	private int dealNumber = 0;
	private Board board;
	private Deck selected21;
	private Player player;
	
	public Dealer(Board board, Deck deck, Player player){
		this.board = board;
		this.selected21 = deck;
		deck.shuffle();
		this.player = player;
		
	}
	
	public void deal(){
		float xPos = 180;
		float yPos = 400;

		for(int i = 0; i < selected21.size(); i++){			
			selected21.get(i).setPos(180, 400);
			selected21.get(i).setTargets(xPos * ((i%3) + 1), yPos);
			
			//this will place the cards without the animation
			//selected21.get(i).setPos(xPos * ((i%3) + 1), yPos);
			board.addToColumn(i % 3, selected21.get(i));
			if(i%3 == 2)
				yPos -= Card.CARD_HEIGHT/2;
		}
		dealNumber++;
	}
	
	public Card revealCard(){
		return selected21.get(10);
	}
	
	public int dealNumber(){
		return dealNumber;
	}
	
	public void pickupCards()
	{
		int columnNumber = player.getColumnSelected();
		selected21.clear();
		Column selected = board.getColumn(columnNumber);
		Column one = board.getNextColumn();
		Column two = board.getNextColumn();
		for(int i = 0; i < 7; i++)
			selected21.add(one.getNextCard());
		for(int i = 0; i < 7; i++)
			selected21.add(selected.getNextCard());
		for(int i = 0; i < 7; i++)
			selected21.add(two.getNextCard());
	
	}
	
	public void resetDealNumber(){
		dealNumber = 0;
	}
}
