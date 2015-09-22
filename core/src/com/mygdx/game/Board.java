package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board {

	List<Column> columns;
	
	public Board(){
		columns = new ArrayList<Column>();
		for(int i = 1; i < 4; i++){
			columns.add(new Column(i));
		}
	}
	
	public void addToColumn(int columnId, Card card)
	{
		columns.get(columnId).addCard(card);
	}
	
	public void draw(SpriteBatch batch){
		for(Column c : columns){
			c.draw(batch);
		}
	}
	
	public void drawReveal(SpriteBatch batch){
		columns.get(1).drawReveal(batch);
	}

	public void columnClicked(float x, float y, Boolean clicked, Player player) {
		//check to see if columns have been dealt
		if(columns.get(0).exists())
		for(Column c : columns){
			c.checkClicks(x, y, clicked, player);
		}	
	}
		
	public Column getColumn(int i)
	{
		Column c = columns.get(i - 1);
		columns.remove(i - 1);
		buildColumns();
		return c;		
	}
	
	public Column getNextColumn(){
		Column c = columns.get(0);
		columns.remove(0);
		return c;
	}

	public void buildColumns() {
		for(int i = 1; i < 4; i++){
			columns.add(new Column(i));
		}		
	}

}
