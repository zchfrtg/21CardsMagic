package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board {

	List<Column> columns;
	
	public Board(){
		columns = new ArrayList<Column>();
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

	public void columnClicked(float x, float y, Boolean clicked) {
		//check to see if columns have been delt
		if(columns.get(0).exists())
		for(Column c : columns)
			c.checkClicks(x, y, clicked);
		
	}
	
	public Column getColumn(int i)
	{
		return columns.get(i);
	}

}
