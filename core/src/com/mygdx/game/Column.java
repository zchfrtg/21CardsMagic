package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Column {

	private int id;
	List<Card> cards = new ArrayList<Card>();
	
	public Column(int id)
	{
		this.id = id;
	}
	
	public void addCard(Card card)
	{
		cards.add(card);
	}

	public void draw(SpriteBatch batch) {
		for(Card c : cards)
			c.draw(batch);
	}

	public void checkClicks(float x, float y) {
		//Need to figure out how to return the column clicked on
		//This would be a click between the sprites location at index 6
		//and the sprites location at index 0 + height/width.
		if(x >= cards.get(6).getX()
				&& y >= cards.get(6).getY()
				&& x < cards.get(0).getX() + Card.CARD_WIDTH
				&& y < cards.get(0).getY() + Card.CARD_HEIGHT){
			//Determine the card clicked on.  Ignoring overlaps.  Current draw is half the card high per stack.
			//X should be the same as above.
			float bottomLeftY = cards.get(6).getY();
			int cardClicked = (int) (y - bottomLeftY) / (Card.CARD_HEIGHT/2);
			//last card is 1 or 0
			if(cardClicked == 0)
				cardClicked = 1;
			//reverse the numbers
			cardClicked = 8 - cardClicked;
			System.out.println(cards.get(cardClicked - 1).toString() + " in COLUMN " + id);
		}
	}

	public boolean exists() {
		//check to see if the cards array has cards in it
		if(cards.isEmpty())
			return false;
		else 
			return true;
	}
}
