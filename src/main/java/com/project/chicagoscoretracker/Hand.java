package com.project.chicagoscoretracker;

/**
 * Created by Lukas Aronsson
 * Date: 18/05/2021
 * Time: 15:02
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
public class Hand {
    Poker hand;

    /**
     * Poker hands thant one can get
     */
    public enum Poker {
        PAIR, TWO_PAIRS,THREE_OF_A_KIND,STRAIGHT,FLUSH,FULL_HOUSE,FOUR_OF_A_KIND,STRAIGHT_FLUSH,ROYAL_FLUSH
    }

    /**
     * constructor that asks for the a players poker hand.
     * @param hand the players hand.
     */
    public Hand(Poker hand) {
        this.hand = hand;
    }

    /**
     * the points the player gets for wining a round with this hand.
     * @return The points of the players current hand
     */
    public int points(){

        int points = 0;

        switch(hand){
            case PAIR: points = 1;
            break;
            case TWO_PAIRS: points = 2;
            break;
            case THREE_OF_A_KIND: points = 3;
            break;
            case STRAIGHT: points = 4;
            break;
            case FLUSH: points = 5;
            break;
            case FULL_HOUSE: points = 6;
            break;
            case FOUR_OF_A_KIND: points = 7;
            break;
            case STRAIGHT_FLUSH: points = 8;
            break;
            case ROYAL_FLUSH: points = 52;

        }
        return points;
    }


}
