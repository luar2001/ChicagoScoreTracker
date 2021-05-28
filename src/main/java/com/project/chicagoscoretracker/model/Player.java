package com.project.chicagoscoretracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Created by Lukas Aronsson
 * Date: 18/05/2021
 * Time: 16:04
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    /**
     * player id
     */
    private Integer id;

    /**
     * Name of the player
     */
    private String name;

    /**
     * Number of times this player has won
     */
    private int wins = 0;

    /**
     * Players score in current game
     * <br>
     * resets to 0 when game is complete
     */
    private int score = 0 ;


    /**
     * Constructor for Player
     * @param name Player name
     */
    public Player(String name){
        this.name =name;
    }
}
