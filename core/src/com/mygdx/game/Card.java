package com.mygdx.game;

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

    protected Suit mySuit;
    protected Face myFace;
    
    //Constructor
    public Card(Suit theSuit, Face theFace){
        mySuit = theSuit;
        myRank = theRank;
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

    public String returnCard(){
        return (myRank.toString() + " of " + mySuit.toString());
    }

}//End of Card
