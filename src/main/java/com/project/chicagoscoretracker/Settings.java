package com.project.chicagoscoretracker;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Lukas Aronsson
 * Date: 18/05/2021
 * Time: 14:26
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
@Getter
@Setter
@NoArgsConstructor
public class Settings {

    /**
     * score win condition, when a player reaches this score that player wins the game
     * <br>
     * default value = 52
     */
    private int winScore = 52;

    /**
     * the score where the player in question is no longer allowed to change there cards.
     * <br>
     * default value = 41
     */
    private int buyStop = 41;

    /**
     * the score for wining a round
     * <br>
     * default value = 5
     */
    private int round = 5;

    /**
     * score for wining a round with a 2
     * <br>
     * default value = 15
     */
    private int endWith2 = 15;

    /**
     * score threshold where the player is allowed to chicago
     * <br>
     * default value = 15
     */
    private int scoreToChicago = 15;

    /**
     * score for wining a chicago
     * <br>
     * default value = 15
     */
    private int chicago = 15;

    /**
     * score the player losses when the player losses after saying chicago
     * <br>
     * default value = -15
     */
    private int chicagoLoss = -15;

}
