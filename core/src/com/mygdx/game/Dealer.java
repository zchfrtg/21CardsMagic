package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Dealer {

	
	private int dealNumber = 0;
	private Board board;
	private Card[] selected21;
	private Deck deck;
	private Player player;
	
	public Dealer(Board board, Deck deck, Player player){
		this.board = board;
		this.selected21 = deck.random21();
		this.deck = deck;
		this.player = player;
	}
	public Dealer()
	{
		
	}
	
	public void deal(){
		float xPos = 180;
		float yPos = 400;
		
		for(int i = 0; i < selected21.length; i++)
		{
			selected21[i].setPos(xPos * ((i%3) + 1), yPos);
			board.addToColumn(i % 3, selected21[i]);
			if(i%3 == 2)
				yPos -= Card.CARD_HEIGHT/2;
		}
	}
	
	public void revealCard(){
		
	}
	
	public void pickupCards()
	{
		
	}
	
//	public static void main(String args[])
//	{
//		Card[] drawnCards;
//		Texture cardSpriteSheet = new Texture("cards.png");
//		Deck deck = new Deck(cardSpriteSheet);
//		drawnCards = deck.random21();
//		new Board board;
//		new Dealer(board, deck, player);
//	}
}
