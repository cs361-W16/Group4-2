package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by michaelhilton on 1/25/16.
 */
public class Game {

    public java.util.List<Card> deck = new ArrayList<>();

    public java.util.List<java.util.List<Card>> cols = new ArrayList<>();

    public int score;


    public Game(){
        cols.add(new ArrayList<Card>());
        cols.add(new ArrayList<Card>());
        cols.add(new ArrayList<Card>());
        cols.add(new ArrayList<Card>());
        score = 0;
    }










    public void buildDeck() {
        String name;
        for(int i = 2; i < 15; i++){
            if (i == 14) name = "Ace ";
            else if (i == 13) name = "King ";
            else if (i == 12) name = "Queen ";
            else if (i == 11) name = "Jack ";
            else name = Integer.toString(i) + " ";
            deck.add(new Card(i,Suit.Clubs, name));
            deck.add(new Card(i,Suit.Hearts, name));
            deck.add(new Card(i,Suit.Diamonds, name));
            deck.add(new Card(i,Suit.Spades, name));
        }
    }

    public void buildSpanishDeck() {
        String name;
        //1 for Ace, 8 for Jack, 9 for Knight, 10 for King
        for(int i = 1; i < 11; i++){
            if (i == 10) name = "King ";
            else if (i == 9) name = "Knight ";
            else if (i == 8) name = "Jack ";
            else if (i == 1) name = "Ace ";
            else name = Integer.toString(i) + " ";
            deck.add(new Card(i,Suit.Swords, name));
            deck.add(new Card(i,Suit.Clubs, name));
            deck.add(new Card(i,Suit.Cups, name));
            deck.add(new Card(i,Suit.Coins, name));
        }
    }







    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));
    }

    public void dealFour() {
        for(int i = 0; i < 4; i++){
            cols.get(i).add(deck.get(deck.size()-1));
            deck.remove(deck.size()-1);
        }
    }

    //customDeal to setup game for testing purposes
    public void customDeal(int c1, int c2, int c3, int c4) {
        cols.get(0).add(deck.get(c1));
        deck.remove(c1);
        cols.get(1).add(deck.get(c2));
        deck.remove(c2);
        cols.get(2).add(deck.get(c3));
        deck.remove(c3);
        cols.get(3).add(deck.get(c4));
        deck.remove(c4);
    }

    public void remove(int columnNumber) {
        if(colHasCards(columnNumber)) {
            Card c = getTopCard(columnNumber);
            boolean removeCard = false;
            for (int i = 0; i < 4; i++) {
                if (i != columnNumber) {
                    if (colHasCards(i)) {
                        Card compare = getTopCard(i);
                        if (compare.getSuit() == c.getSuit()) {
                            if (compare.getValue() > c.getValue()) {
                                removeCard = true;
                            }
                        }
                    }
                }
            }
            if (removeCard) {
                this.cols.get(columnNumber).remove(this.cols.get(columnNumber).size() - 1);
                score += 1;
            }
        }
    }

    private boolean colHasCards(int colNumber) {
        if(this.cols.get(colNumber).size()>0){
            return true;
        }
        return false;
    }

    private Card getTopCard(int columnNumber) {
        return this.cols.get(columnNumber).get(this.cols.get(columnNumber).size()-1);
    }


    public void move(int colFrom, int colTo) {
        if (!colHasCards(colTo)) {
            Card cardToMove = getTopCard(colFrom);
            this.removeCardFromCol(colFrom);
            this.addCardToCol(colTo, cardToMove);
        }
    }

    private void addCardToCol(int colTo, Card cardToMove) {
        cols.get(colTo).add(cardToMove);
    }

    private void removeCardFromCol(int colFrom) {
        this.cols.get(colFrom).remove(this.cols.get(colFrom).size()-1);

        //I think that should conflict...
        //Did it work yet?

    }
}
