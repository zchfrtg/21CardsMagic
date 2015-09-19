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
}
