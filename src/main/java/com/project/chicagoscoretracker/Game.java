package com.project.chicagoscoretracker;

import com.project.chicagoscoretracker.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lukas Aronsson
 * Date: 20/05/2021
 * Time: 16:47
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
public class Game {

    GetAndPost connection = new GetAndPost();

    /**
     * the game settings
     */
    static Settings settings;

    /**
     * a boolean that represent if a game of Chicago is started or not
     */
    public Boolean gameStarted = false;

    /**
     * List of Players in the current game of Chicago
     */
    static private final List<Player> players = new ArrayList<>();

    /**
     * Scanner to let the user type in the names of players.
     */
    private final Scanner scan = new Scanner(System.in);

    /**
     * gets a Player from the database by name!
     * @param name name of player
     * @return Player
     */
    public Player getPlayer(String name){
        return connection.getPlayerByName(name);
    }

    /**
     * Adds a player to the game
     *  @param name a player name.
     */
    public void addPlayer(String name) {
        Player temp = getPlayer(name);
        players.add(temp);
        System.out.println("\nNew player: " + temp + " was added to the game ");
    }

    /**
     * Creates a new player and adds it to the database
     * @param name name of the player
     */
    public void newPlayer(String name) {
        connection.savePlayer(new Player(name)); //adds the new player to the database
    }

    /**
     * Starts the game and asks for 2 players
     */
    public void startGame() {
        System.out.println("\nCHICAGO \nAdd a Player: ");
        addPlayer(scan.nextLine());
        System.out.println("Add a player: ");
        addPlayer(scan.nextLine());
        System.out.println("Players where added and game will now start! ");
        gameStarted = true;
        System.out.println("\nPlayers that are in game currently are : "+  listPlayers());
    }

    /**
     * ends the game and gives the player with most score the win.
     * <br>
     * clears the player list and sets all the players scorers to 0
     */
    public void endGame() {
        int largest = 0;
        Player winner = new Player();  //temporary player.

        for(Player player : players){
            if(player.getScore() > largest){
                largest = player.getScore();
                winner = player;
            }
        }

        System.out.println("\n"+ winner + "won with a score of " + largest + " !");
        winner.setWins(winner.getWins() + 1);

        players.forEach(player -> player.setScore(0)); //sets all the players in the games score to 0
        players.clear(); //removes all the players form the game
        gameStarted = false; //ends the game
    }

    /**
     * adds points to player
     * @param player player that gets the points
     * @param points the points
     */
    public void addPoints(Player player, int points){
        player.setScore(player.getScore() + points);
    }

    /**
     * Checks if player has reached the win condition
     * @param player player to check
     * @return if player has won
     */
    public boolean checkScore(Player player) {
            return player.getScore() >= settings.getWinScore();
    }

    /**
     * lists all players in the database
     * @return list of players
     */
    public List<Player> listAllPlayers() {
        return connection.getPlayers();
    }

    /**
     * Lists the players in the current game of chicago
     * <br>
     * empty if a game is not started
     * @return list of players
     */
    public List<Player> listPlayers() {
        return players;
    }


    /**
     * used to update players win by replacing it
     * @param player player to update
     */
    public void updatePlayer(Player player){
        connection.savePlayer(player);
    }


}
