package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

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
//	public Dealer()
//	{
//		
//	}
	
	public void deal(){
		float xPos = 180;
		float yPos = 400;
		
		for(int i = 0; i < selected21.size(); i++)
		{
			selected21.get(i).setPos(xPos * ((i%3) + 1), yPos);
			board.addToColumn(i % 3, selected21.get(i));
			if(i%3 == 2)
				yPos -= Card.CARD_HEIGHT/2;
		}
	}
	
	public Card revealCard(){
		return selected21.get(10);
	}
	
	public void pickupCards()
	{
		int columnNumber = player.getColumnSelected();
		selected21.clear();
		Column column;
		switch (columnNumber) {
		case 1:	column = board.getColumn(2);
				for(int i = 0; i < 7; i++)
					selected21.add(column.cards.get(i));
				column = board.getColumn(1);
				for(int i = 7; i < 14; i++)
				{
					selected21.add(column.cards.get(i));
				}
				column = board.getColumn(3);
				for(int i = 14; i < 21; i++)
				{
					selected21.add(column.cards.get(i));
				}
				break;
		case 2:	column = board.getColumn(1);
				for(int i = 0; i < 7; i++)
					selected21.add(column.cards.get(i));
				column = board.getColumn(2);
				for(int i = 7; i < 14; i++)
				{
					selected21.add(column.cards.get(i));
				}
				column = board.getColumn(3);
				for(int i = 14; i < 21; i++)
				{
					selected21.add(column.cards.get(i));
				}
				break;
		case 3: column = board.getColumn(1);
				for(int i = 0; i < 7; i++)
					selected21.add(column.cards.get(i));
				column = board.getColumn(3);
				for(int i = 7; i < 14; i++)
				{
					selected21.add(column.cards.get(i));
				}
				column = board.getColumn(2);
				for(int i = 14; i < 21; i++)
				{
					selected21.add(column.cards.get(i));
				}
				break;
		}
		
	}
	
	public static void main(String args[])
	{
	}
}
