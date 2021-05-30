package com.project.chicagoscoretracker;

import com.project.chicagoscoretracker.model.Player;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Lukas Aronsson
 * Date: 20/05/2021
 * Time: 16:48
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
class GameTest {
    Game game = new Game();
    Settings settings = new Settings();
    GetAndPost getandpost = new GetAndPost();
    Player test;

    @BeforeEach
    void setUp() {
        //Creates a player names test
        game.newPlayer("test");
    }

    @Disabled
    @DisplayName("endGame test")
    @Test
    void endGame(){
        game.gameStarted = true;
        game.addPlayer("test");
        test = game.getPlayer("test");
        game.newPlayer("test2");
        game.addPlayer("test2");
        Player test2 = game.getPlayer("test2");
        test.setScore(10);
        int tpw = test.getWins();
        test2.setScore(5);
        int tp2w = test2.getWins();

        game.endGame(); //ends the Game ?

        assertTrue(test.getWins() > tpw); // testPlayer gained a win hopefully
        assertFalse( test2.getWins() > tp2w);
        assertEquals( test2.getWins(), tp2w);

        getandpost.deletePlayer(test2.getId()); //Removes testPlayer2
    }
    @Disabled
    @DisplayName("listPlayers test")
    @Test
    void listPlayers(){
        game.newPlayer("test1"); //test1 is new
        game.gameStarted = true; //sets the game state to started so that players can be added
        game.addPlayer("test1");
        game.addPlayer("test"); //test is from the database

        assertTrue(game.listPlayers().size() > 0);
        assertEquals(game.listPlayers().get(0).getName() ,"test1");
        assertEquals(game.listPlayers().get(1).getName() ,"test");

        getandpost.deletePlayer(game.getPlayer("test1").getId()); //Removes the test1 player
    }

    @DisplayName("listAllPlayers test")
    @Test
    void listAllPlayers(){
        assertTrue(game.listAllPlayers().size()>0);
        assertEquals(game.listAllPlayers().get(game.listAllPlayers().size()-1).getName(), "test"); //Checks if the latest Player has the name test
    }

    @DisplayName("newPlayer test")
    @Test
    void newPlayer(){
        game.newPlayer("test3");
        assertEquals(game.getPlayer("test3").getName(), "test3"); //checks if a Player with that name exists
        getandpost.deletePlayer(game.getPlayer("test3").getId());
    }

    @DisplayName("checkScore test")
    @Test
    void checkScore(){
        test = game.getPlayer("test");
        test.setScore(settings.getWinScore());
        assertTrue(game.checkScore(test));
        game.getPlayer("test").setScore(0);
        assertFalse(game.checkScore(test));
        game.getPlayer("test").setScore(-57);
        assertFalse(game.checkScore(test));
        game.getPlayer("test").setScore(0); //reset just in case
    }

    @AfterEach
    void tearDown() {
        getandpost.deletePlayer(game.getPlayer("test").getId()); //Removes the test player
    }
}