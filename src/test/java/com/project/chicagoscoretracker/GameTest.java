package com.project.chicagoscoretracker;

import com.project.chicagoscoretracker.model.Player;
import com.project.chicagoscoretracker.service.PlayerService;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Lukas Aronsson
 * Date: 20/05/2021
 * Time: 16:48
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
class GameTest {
    PlayerService service;
    Player testPlayer;
    Settings settings;
    Game game;

    @BeforeEach
    void setUp() {
        testPlayer = new Player("test"); //Creates a player names test
    }

    @Disabled
    @DisplayName("startGame test")
    @Test
    void startGame(){
        game.startGame();


    }

    @DisplayName("endGame test")
    @Test
    void endGame(){
        game.addPlayer("test2"); //test2 is new
        Player testPlayer2 = service.getPlayerByName("test2");
        testPlayer.setScore(10);
        int tpw = testPlayer.getWins();
        testPlayer2.setScore(5);
        int tp2w = testPlayer2.getWins();

        game.endGame(); //ends the Game ?

        assertTrue(testPlayer.getWins() > tpw); // testPlayer gained a win
        assertFalse(testPlayer2.getWins() > tp2w);
        assertEquals(testPlayer2.getWins(), tp2w);

        //TODO: test that endGame calls win ?

        service.deletePlayer(testPlayer2.getId()); //Removes testPlayer2
    }

    @DisplayName("listPlayers test")
    @Test
    void listPlayers(){
        game.addPlayer("test1"); //test1 is new
        game.addPlayer("test"); //test is from the database

        List<Player> testList = game.listPlayers();

        assertTrue(testList.size() > 0);
        assertEquals(testList.get(0).getName() ,"test1");
        assertEquals(testList.get(1).getName() ,"test");

        service.deletePlayer(service.getPlayerByName("test1").getId()); //Removes the test1 player
    }

    @DisplayName("listAllPlayers test")
    @Test
    void listAllPlayers(){
        List<Player> testList2 = game.listAllPlayers();
        assertTrue(testList2.size()>0);
        assertEquals(testList2.get(testList2.size()-1).getName(), "test"); //Checks if the latest Player has the name test
    }

    @DisplayName("addPlayer test")
    @Test
    void addPlayer(){
        game.addPlayer("test3");

        assertEquals(service.getPlayerByName("test3").getName(), "test3"); //checks if a Player with that name exists

        service.deletePlayer(service.getPlayerByName("test3").getId());
    }

    @Disabled
    @DisplayName("win test")
    @Test
    void win(){}

    @DisplayName("checkScore test")
    @Test
    void checkScore(){
        testPlayer.setScore(settings.getWinScore());
        assertTrue(game.checkScore(testPlayer));
        testPlayer.setScore(0);
        assertFalse(game.checkScore(testPlayer));
        testPlayer.setScore(-57);
        assertFalse(game.checkScore(testPlayer));
        testPlayer.setScore(0); //reset just in case
    }


    @DisplayName("checkChicago test")
    @Test
    void checkChicago(){
        testPlayer.setScore(settings.getScoreToChicago());
        assertTrue(game.checkChicago(testPlayer));
        testPlayer.setScore(0);
        assertFalse(game.checkChicago(testPlayer));
        testPlayer.setScore(-57);
        assertFalse(game.checkChicago(testPlayer));
        testPlayer.setScore(0); //reset just in case
    }

    @AfterEach
    void tearDown() {
        service.deletePlayer(service.getPlayerByName("test").getId()); //Removes the test player
    }
}