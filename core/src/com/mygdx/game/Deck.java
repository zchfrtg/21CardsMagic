package com.mygdx.game;
import com.mygdx.game.Card;
import com.mygdx.game.Card.Suit;
import com.mygdx.game.Card.Face;
import java.util.Random;

public class Deck {
    private Card[] theDeck;
    private Card[] shuffledDeck;
    private Card[] twentyOneCards;
    private Random myRandom;

    //This creates an unshuffled deck of cards.
    private void hardCodeCards(){
        //Create the deck with 52 spots
        theDeck = new Card[52];
        //SPADES
        theDeck[0] = new Card(Suit.SPADES, Face.TWO);
        theDeck[1] = new Card(Suit.SPADES, Face.THREE);
        theDeck[2] = new Card(Suit.SPADES, Face.FOUR);
        theDeck[3] = new Card(Suit.SPADES, Face.FIVE);
        theDeck[4] = new Card(Suit.SPADES, Face.SIX);
        theDeck[5] = new Card(Suit.SPADES, Face.SEVEN);
        theDeck[6] = new Card(Suit.SPADES, Face.EIGHT);
        theDeck[7] = new Card(Suit.SPADES, Face.NINE);
        theDeck[8] = new Card(Suit.SPADES, Face.TEN);
        theDeck[9] = new Card(Suit.SPADES, Face.JACK);
        theDeck[10] = new Card(Suit.SPADES, Face.QUEEN);
        theDeck[11] = new Card(Suit.SPADES, Face.KING);
        theDeck[12] = new Card(Suit.SPADES, Face.ACE);
        
        //HEARTS
        theDeck[13] = new Card(Suit.HEARTS, Face.TWO);
        theDeck[14] = new Card(Suit.HEARTS, Face.THREE);
        theDeck[15] = new Card(Suit.HEARTS, Face.FOUR);
        theDeck[16] = new Card(Suit.HEARTS, Face.FIVE);
        theDeck[17] = new Card(Suit.HEARTS, Face.SIX);
        theDeck[18] = new Card(Suit.HEARTS, Face.SEVEN);
        theDeck[19] = new Card(Suit.HEARTS, Face.EIGHT);
        theDeck[20] = new Card(Suit.HEARTS, Face.NINE);
        theDeck[21] = new Card(Suit.HEARTS, Face.TEN);
        theDeck[22] = new Card(Suit.HEARTS, Face.JACK);
        theDeck[23] = new Card(Suit.HEARTS, Face.QUEEN);
        theDeck[24] = new Card(Suit.HEARTS, Face.KING);
        theDeck[25] = new Card(Suit.HEARTS, Face.ACE);
        
        //CLUBS
        theDeck[26] = new Card(Suit.CLUBS, Face.TWO);
        theDeck[27] = new Card(Suit.CLUBS, Face.THREE);
        theDeck[28] = new Card(Suit.CLUBS, Face.FOUR);
        theDeck[29] = new Card(Suit.CLUBS, Face.FIVE);
        theDeck[30] = new Card(Suit.CLUBS, Face.SIX);
        theDeck[31] = new Card(Suit.CLUBS, Face.SEVEN);
        theDeck[32] = new Card(Suit.CLUBS, Face.EIGHT);
        theDeck[33] = new Card(Suit.CLUBS, Face.NINE);
        theDeck[34] = new Card(Suit.CLUBS, Face.TEN);
        theDeck[35] = new Card(Suit.CLUBS, Face.JACK);
        theDeck[36] = new Card(Suit.CLUBS, Face.QUEEN);
        theDeck[37] = new Card(Suit.CLUBS, Face.KING);
        theDeck[38] = new Card(Suit.CLUBS, Face.ACE);
        
        //Diamonds
        theDeck[39] = new Card(Suit.DIAMONDS, Face.TWO);
        theDeck[40] = new Card(Suit.DIAMONDS, Face.THREE);
        theDeck[41] = new Card(Suit.DIAMONDS, Face.FOUR);
        theDeck[42] = new Card(Suit.DIAMONDS, Face.FIVE);
        theDeck[43] = new Card(Suit.DIAMONDS, Face.SIX);
        theDeck[44] = new Card(Suit.DIAMONDS, Face.SEVEN);
        theDeck[45] = new Card(Suit.DIAMONDS, Face.EIGHT);
        theDeck[46] = new Card(Suit.DIAMONDS, Face.NINE);
        theDeck[47] = new Card(Suit.DIAMONDS, Face.TEN);
        theDeck[48] = new Card(Suit.DIAMONDS, Face.JACK);
        theDeck[49] = new Card(Suit.DIAMONDS, Face.QUEEN);
        theDeck[50] = new Card(Suit.DIAMONDS, Face.KING);
        theDeck[51] = new Card(Suit.DIAMONDS, Face.ACE);
        }//end of Hard code cards

        //This method returns a single card from the hard-coded deck
        private Card drawCard(){
        //Ensure the card hasn't already been taken:
        boolean gotACard = false;
        
        //randCard will store the random number in the deck we chose
        int randCard = 0;  
        
        //creates a random number that determines which card we'll draw from the hard-coded deck
        randCard = myRandom.nextInt(theDeck.length - 0);
        
        //This checks to make sure we get a valid card
        while(gotACard == false){
        //generate random numbers
        randCard = myRandom.nextInt(theDeck.length);
        
        if(theDeck[randCard].returnTaken() == false){
                //breaks the WHILE loop since we found a valid card
                gotACard = true;
                //updates the card so we don't pick it again
                theDeck[randCard].cardIsTaken();
                }      
            }
            return theDeck[randCard];
        }//End of drawCard method

    public void shuffle(){
        hardCodeCards();
        shuffledDeck = new Card[52];
        for(int i = 0; i < 52; i++){
            shuffledDeck[i] = drawCard();
        }
    }//end of shuffle() method
    
    //We may want to change this later.  
    //Basically this method just returns an 
    //array of 21 cards drawn from the top of the shuffled deck.
    public Card[] random21(){
        twentyOneCards = new Card[21];
        for(int i = 0; i < 21; i++){
            twentyOneCards[i] = shuffledDeck[i];
        }
        return twentyOneCards;
    }
    
}
