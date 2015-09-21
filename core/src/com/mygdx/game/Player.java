package com.mygdx.game;

public class Player {
	private boolean hasSelectedCard;
	private int columnSelected;
	
	public Player()
	{
		hasSelectedCard = false;
	}
	
	public void indicateColumn(int column)
	{
		columnSelected = column;
	}
	
	public void pickCard()
	{
		hasSelectedCard = true;
	}
	public boolean getHasSelectedCard()
	{
		return hasSelectedCard;
	}
	public int getColumnSelected()
	{
		return columnSelected;
	}
}
