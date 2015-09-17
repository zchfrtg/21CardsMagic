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

    protected boolean taken;
    protected Suit mySuit;
    protected Face myFace;
    
    //Constructor
    public Card(Suit theSuit, Face theFace){
        taken = false;
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

    //This method returns the Face and the Suit of the card as a single string instead of two seperate strings
    public String returnCard(){
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

}//End of Card
