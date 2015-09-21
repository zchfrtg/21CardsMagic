package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Created by Kenneth Adair in Group 3 Assignment 2
public class Card {

    //Enumerated Suit data type
    public  enum Suit{
        HEARTS, SPADES, CLUBS, DIAMONDS
    }

    //Enumerated Face data type
    public enum Face{
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    public static final int CARD_WIDTH = 71;
    public static final int CARD_HEIGHT = 95;
    
    protected boolean taken;
    protected Suit mySuit;
    protected Face myFace;
    private Sprite cardBack;
    private Sprite cardFront;
    
    private float targetX;
    private float targetY;
    
    //Constructor
    public Card(Suit theSuit, Face theFace, Sprite cardSprite){
        taken = false;
        mySuit = theSuit;
        myFace = theFace;
        this.cardBack = new Sprite(new Texture(Gdx.files.internal("cardBack.png")));
        this.cardFront = cardSprite;
    }
    
    //This method returns the suit of the card as a string.  
    //So if the card is HEARTS when you use this method it will return "HEARTS"
    public String returnSuit(){
        return this.mySuit.toString();
    }
    
    //This method returns the "rank" (number or face value) of the card as a string
    //So if the card is TWO then the method will return a String that says "TWO"
    //If the card is a King it will return a string that says "KING"
    public String returnFace(){
        return this.myFace.toString();
    }

    //This method returns the Face and the Suit of the card as a single string instead of two seperate strings
    public String toString(){
        return (myFace.toString() + " of " + mySuit.toString());
    }

    //This method updates the card and say it is taken now
    public void cardIsTaken(){
        this.taken = true;
    }
    
    //This method checks whether or not the card has been taken
    public boolean returnTaken(){
        return this.taken;
    }

	public void draw(SpriteBatch batch) {
		// Add sprite drawing here, temp for testing
		this.slide(targetX, targetY);
		cardFront.draw(batch);
		
	}
	
	//This method is for positioning cards on the gameboard.
	public void setPos(float x, float y){
		cardFront.setPosition(x, y);
	}

	public void checkClicks(float x, float y) {
		if(cardFront.getBoundingRectangle().contains(x, y))
			System.out.println("" + mySuit + myFace);
		
	}

	public void printXY() {
		System.out.println("Width " + cardFront.getWidth());
		System.out.println("Height " + cardFront.getHeight());
		
	}

	public float getX() {
		return cardFront.getX();
	}
	
	public float getY(){
		return cardFront.getY();
	}
	
	public void slide(float targetXPos, float targetYPos){
		float currentX = cardFront.getX();
		float currentY = cardFront.getY();
		float deltaTime = Gdx.graphics.getDeltaTime();
		float speed = deltaTime * 150;
		float movementX;
		float movementY;
		
		//x movement
		if(currentX < targetXPos){
			movementX = (currentX + speed);
		}
		else {
			movementX = targetXPos;
		}
		
		//y movement
		if(currentY > targetYPos){
			movementY = (currentY - speed);
		}
		else {
			movementY = targetYPos;
		}
		
		
		
		//float movementY = (targetYPos - cardFront.getY());
		
		cardFront.setPosition(movementX, movementY);
	
		
		
	}


	public void setTargets(float targetX, float targetY) {
		this.targetX = targetX;
		this.targetY = targetY;
	}

}//End of Card
