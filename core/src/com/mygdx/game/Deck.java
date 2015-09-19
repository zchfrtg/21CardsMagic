package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Card;
import com.mygdx.game.Card.Suit;
import com.mygdx.game.Card.Face;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> theDeck;
    private Card[] shuffledDeck;
    private Card[] twentyOneCards;
    private Random myRandom;
    
    //holds the spriteSheet for cards
    private final Sprite[] cardSprites;

    public Deck(Texture cardTexture){
    	cardSprites = new Sprite[52];
    	int next = 0;
    	TextureRegion[][] tmp = TextureRegion.split(cardTexture, cardTexture.getWidth()/13, cardTexture.getHeight()/4);
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 13; j++){
				cardSprites[next] = new Sprite(tmp[i][j], 2, 2, cardTexture.getWidth()/13-2, cardTexture.getHeight()/4-2);
				next++;
			}
		}
		hardCodeCards();
    }
    //This creates an unshuffled deck of cards.
    private void hardCodeCards(){
        //Create the deck with 52 spots
        theDeck = new ArrayList<Card>();
        //SPADES
        theDeck.add(new Card(Suit.SPADES, Face.TWO, cardSprites[27]));
        theDeck.add(new Card(Suit.SPADES, Face.THREE, cardSprites[28]));
        theDeck.add(new Card(Suit.SPADES, Face.FOUR, cardSprites[29]));
        theDeck.add(new Card(Suit.SPADES, Face.FIVE, cardSprites[30]));
        theDeck.add(new Card(Suit.SPADES, Face.SIX, cardSprites[31]));
        theDeck.add(new Card(Suit.SPADES, Face.SEVEN, cardSprites[32]));
        theDeck.add(new Card(Suit.SPADES, Face.EIGHT, cardSprites[33]));
        theDeck.add(new Card(Suit.SPADES, Face.NINE, cardSprites[34]));
        theDeck.add(new Card(Suit.SPADES, Face.TEN, cardSprites[35]));
        theDeck.add(new Card(Suit.SPADES, Face.JACK, cardSprites[36]));
        theDeck.add(new Card(Suit.SPADES, Face.QUEEN, cardSprites[37]));
        theDeck.add(new Card(Suit.SPADES, Face.KING, cardSprites[38]));
        theDeck.add(new Card(Suit.SPADES, Face.ACE, cardSprites[26]));
        
        //HEARTS
        theDeck.add(new Card(Suit.HEARTS, Face.TWO, cardSprites[14]));
        theDeck.add(new Card(Suit.HEARTS, Face.THREE, cardSprites[15]));
        theDeck.add(new Card(Suit.HEARTS, Face.FOUR, cardSprites[16]));
        theDeck.add(new Card(Suit.HEARTS, Face.FIVE, cardSprites[17]));
        theDeck.add(new Card(Suit.HEARTS, Face.SIX, cardSprites[18]));
        theDeck.add(new Card(Suit.HEARTS, Face.SEVEN, cardSprites[19]));
        theDeck.add(new Card(Suit.HEARTS, Face.EIGHT, cardSprites[20]));
        theDeck.add(new Card(Suit.HEARTS, Face.NINE, cardSprites[21]));
        theDeck.add(new Card(Suit.HEARTS, Face.TEN, cardSprites[22]));
        theDeck.add(new Card(Suit.HEARTS, Face.JACK, cardSprites[23]));
        theDeck.add(new Card(Suit.HEARTS, Face.QUEEN, cardSprites[24]));
        theDeck.add(new Card(Suit.HEARTS, Face.KING, cardSprites[25]));
        theDeck.add(new Card(Suit.HEARTS, Face.ACE, cardSprites[13]));
        
        //CLUBS
        theDeck.add(new Card(Suit.CLUBS, Face.TWO, cardSprites[1]));
        theDeck.add(new Card(Suit.CLUBS, Face.THREE, cardSprites[2]));
        theDeck.add(new Card(Suit.CLUBS, Face.FOUR, cardSprites[3]));
        theDeck.add(new Card(Suit.CLUBS, Face.FIVE, cardSprites[4]));
        theDeck.add(new Card(Suit.CLUBS, Face.SIX, cardSprites[5]));
        theDeck.add(new Card(Suit.CLUBS, Face.SEVEN, cardSprites[6]));
        theDeck.add(new Card(Suit.CLUBS, Face.EIGHT, cardSprites[7]));
        theDeck.add(new Card(Suit.CLUBS, Face.NINE, cardSprites[8]));
        theDeck.add(new Card(Suit.CLUBS, Face.TEN, cardSprites[9]));
        theDeck.add(new Card(Suit.CLUBS, Face.JACK, cardSprites[10]));
        theDeck.add(new Card(Suit.CLUBS, Face.QUEEN, cardSprites[11]));
        theDeck.add(new Card(Suit.CLUBS, Face.KING, cardSprites[12]));
        theDeck.add(new Card(Suit.CLUBS, Face.ACE, cardSprites[0]));
        
        //Diamonds
        theDeck.add(new Card(Suit.DIAMONDS, Face.TWO, cardSprites[40]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.THREE, cardSprites[41]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.FOUR, cardSprites[42]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.FIVE, cardSprites[43]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.SIX, cardSprites[44]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.SEVEN, cardSprites[45]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.EIGHT, cardSprites[46]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.NINE, cardSprites[47]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.TEN, cardSprites[48]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.JACK, cardSprites[49]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.QUEEN, cardSprites[50]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.KING, cardSprites[51]));
        theDeck.add(new Card(Suit.DIAMONDS, Face.ACE, cardSprites[39]));
    }//end of Hard code cards

        //This method returns a single card from the hard-coded deck
	private Card drawCard(){
		Card c = theDeck.get(0);
		theDeck.remove(0);
		return c;
//        //Ensure the card hasn't already been taken:
//        boolean gotACard = false;
//        
//        //randCard will store the random number in the deck we chose
//        int randCard = 0;  
//        
//        //creates a random number that determines which card we'll draw from the hard-coded deck
//        randCard = myRandom.nextInt(theDeck.length - 0);
//        
//        //This checks to make sure we get a valid card
//        while(gotACard == false){
//        //generate random numbers
//        randCard = myRandom.nextInt(theDeck.length);
//        
//        if(theDeck[randCard].returnTaken() == false){
//                //breaks the WHILE loop since we found a valid card
//                gotACard = true;
//                //updates the card so we don't pick it again
//                theDeck[randCard].cardIsTaken();
//                }      
//            }
//            return theDeck[randCard];
	}//End of drawCard method

    public void shuffle(){
//        hardCodeCards();
//        Collections.shuffle(theDeck);
//        shuffledDeck = new Card[52];
//        for(int i = 0; i < 52; i++){
//            shuffledDeck[i] = drawCard();
//        }
    	Collections.shuffle(theDeck);
    }//end of shuffle() method
    
    //We may want to change this later.  
    //Basically this method just returns an 
    //array of 21 cards drawn from the top of the shuffled deck.
    public Card[] random21(){
        twentyOneCards = new Card[21];
        for(int i = 0; i < 21; i++){
//            twentyOneCards[i] = shuffledDeck[i];
        	twentyOneCards[i] = drawCard();
        }
        return twentyOneCards;
    }
    
}
