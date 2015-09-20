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
    private Card[] twentyOneCards;
    
    //holds the spriteSheet for cards
    private final Sprite[] cardSprites;

    public Deck(Texture cardTexture){
    	cardSprites = new Sprite[52];
    	int next = 0;
    	TextureRegion[][] tmp = TextureRegion.split(cardTexture, cardTexture.getWidth()/13, cardTexture.getHeight()/4);
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 13; j++){
				cardSprites[next] = new Sprite(tmp[i][j], 1, 1, cardTexture.getWidth()/13-1, cardTexture.getHeight()/4-1);
				next++;
			}
		}
		hardCodeCards();
    }
    //This creates an unshuffled deck of cards.
    private void hardCodeCards(){
        theDeck = new ArrayList<Card>();

        //Cards are in the array in the order A-K and Clubs/Spades/Heart/Diamon
        int nextSprite = 0;
        for(int i = 0; i < 4; i++) //0-Clubs 1-Spades 2-heart 3-Diamond
        {
        	Card.Suit nextSuit;
        	switch(i){
        	case 0: nextSuit = Card.Suit.CLUBS; break;
        	case 1: nextSuit = Card.Suit.SPADES; break;
        	case 2: nextSuit = Card.Suit.HEARTS; break;
        	default: nextSuit = Card.Suit.DIAMONDS; break;
        	}
        	for(int j = 0; j < 13; j++) // 0=ace 12=King ect
        	{
        		Card.Face nextFace;
        		switch(j){
        		case 0: nextFace = Card.Face.ACE; break;
        		case 1: nextFace = Card.Face.TWO; break;
        		case 2: nextFace = Card.Face.THREE; break;
        		case 3: nextFace = Card.Face.FOUR; break;
        		case 4: nextFace = Card.Face.FIVE; break;
        		case 5: nextFace = Card.Face.SIX; break;
        		case 6: nextFace = Card.Face.SEVEN; break;
        		case 7: nextFace = Card.Face.EIGHT; break;
        		case 8: nextFace = Card.Face.NINE; break;
        		case 9: nextFace = Card.Face.TEN; break;
        		case 10: nextFace = Card.Face.JACK; break;
        		case 11: nextFace = Card.Face.QUEEN; break;
        		default: nextFace = Card.Face.KING; break;
        		}        		
        		//add the card
        		theDeck.add(new Card(nextSuit, nextFace, cardSprites[nextSprite]));
        		
        		nextSprite++;
        	}
        }
    }//end of Hard code cards

        //This method returns a single card from the hard-coded deck
	private Card drawCard(){
		Card c = theDeck.get(0);
		theDeck.remove(0);
		return c;
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
