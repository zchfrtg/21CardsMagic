package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board {

	List<Column> columns = new ArrayList<Column>();
	
	public Board(){
		for(int i = 1; i < 4; i++)
			columns.add(new Column(i));
	}
	
	public void addToColumn(int columnId, Card card)
	{
		columns.get(columnId).addCard(card);
	}
	
	public void draw(SpriteBatch batch){
		for(Column c : columns)
			c.draw(batch);
	}

}
