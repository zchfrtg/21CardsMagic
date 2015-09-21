package com.mygdx.game;

import java.util.Stack;

public class Dealer {

	private int dealNumber = 0;
	private Stack<Card> firstColumn;
	private Stack<Card> secondColumn;
	private Stack<Card> thirdColumn;
	
	public Dealer()
	{
		firstColumn = new Stack<Card>();
		secondColumn = new Stack<Card>();
		thirdColumn = new Stack<Card>();
	}
	
	public void deal(Stack<Card> deck)
	{
		for (int i = deck.size(); i > 0; i=i-3)
		{
			firstColumn.add(deck.pop());
			secondColumn.add(deck.pop());
			thirdColumn.add(deck.pop());
		}
		dealNumber++;
	}
	
	public void revealCard()
	{
		
	}
	
	public Stack<Card> pickupCards(int deckSelected)
	{
		Stack<Card> newDeck = new Stack<Card>();
		if(deckSelected == 1)
		{
			for(int i = secondColumn.size(); i > 0; i--)
			{
				newDeck.add(secondColumn.pop());
			}
			for(int i = firstColumn.size(); i > 0; i--)
			{
				newDeck.add(firstColumn.pop());
			}
			for(int i = thirdColumn.size(); i > 0; i--)
			{
				newDeck.add(thirdColumn.pop());
			}
		}
		else if(deckSelected == 2)
		{
			for(int i = firstColumn.size(); i > 0; i--)
			{
				newDeck.add(firstColumn.pop());
			}
			for(int i = secondColumn.size(); i > 0; i--)
			{
				newDeck.add(secondColumn.pop());
			}
			for(int i = thirdColumn.size(); i > 0; i--)
			{
				newDeck.add(thirdColumn.pop());
			}
		}
		else if(deckSelected == 3)
		{
			for(int i = firstColumn.size(); i > 0; i--)
			{
				newDeck.add(firstColumn.pop());
			}
			for(int i = thirdColumn.size(); i > 0; i--)
			{
				newDeck.add(thirdColumn.pop());
			}
			for(int i = secondColumn.size(); i > 0; i--)
			{
				newDeck.add(secondColumn.pop());
			}
		}
		firstColumn.clear();
		secondColumn.clear();
		thirdColumn.clear();
		return newDeck;
	}

	public int getDealNumber()
	{
		return dealNumber;
	}
}
