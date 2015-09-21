package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Column {

	private int id;
	List<Card> cards = new ArrayList<Card>();
	Sprite highlight;
	Boolean drawHighlight = false;
	
	public Column(int id)
	{
		this.id = id;
		highlight = new Sprite(new Texture(Gdx.files.internal("ColumnHighlight.png")));
		highlight.setPosition((180*id) - 4, 110);
	}
	
	public void addCard(Card card)
	{
		cards.add(card);
	}

	public void draw(SpriteBatch batch) {
		for(Card c : cards)
			c.draw(batch);
		if(drawHighlight)
			highlight.draw(batch);
	}

	public void checkClicks(float x, float y, Boolean clicked) {
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
			if(clicked)
				System.out.println(cards.get(cardClicked - 1).toString() + " in COLUMN " + id);
			
			drawHighlight = true;
		} else
			drawHighlight = false;
	}

	public boolean exists() {
		//check to see if the cards array has cards in it
//is this backwards?
		return cards.isEmpty() ? false:true;
//		if(cards.isEmpty())
//			return false;
//		else 
//			return true;
	}
}
