package com.project.chicagoscoretracker;

/**
 * Created by Lukas Aronsson
 * Date: 18/05/2021
 * Time: 15:02
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
public class Hand {

    /**
     * Poker hands that one can get
     */
    public enum Poker {
        PAIR, TWO_PAIRS,THREE_OF_A_KIND,STRAIGHT,FLUSH,FULL_HOUSE,FOUR_OF_A_KIND,STRAIGHT_FLUSH,ROYAL_FLUSH
    }

    /**
     * the points the player gets for wining a round with this hand.
     * @return The points of the players current hand
     */
    public static int points(Poker hand){

        return switch (hand) {
            case PAIR -> 1;
            case TWO_PAIRS -> 2;
            case THREE_OF_A_KIND -> 3;
            case STRAIGHT -> 4;
            case FLUSH -> 5;
            case FULL_HOUSE -> 6;
            case FOUR_OF_A_KIND -> 7;
            case STRAIGHT_FLUSH -> 8;
            case ROYAL_FLUSH -> 52;
        };
    }


}
