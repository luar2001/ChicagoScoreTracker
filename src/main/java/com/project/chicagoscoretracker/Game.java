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
    private Boolean gameStarted = false;

    /**
     * List of Players in the current game of Chicago
     */
    static private final List<Player> players = new ArrayList<>();

    /**
     * Scanner to let the user type in the names of players.
     */
    private final Scanner scan = new Scanner(System.in);

    /**
     * lists all players in the database
     * @return list of players
     */
    public List<Player> listAllPlayers() {
        return connection.getPlayers();
    }

    /**
     * Starts the game and asks for 2 players, then the user have a choice to add more.
     */
    public void startGame() {
        gameStarted =true;
        boolean dune = false;

        System.out.println("\n CHICAGO \n Add 2 players to start");
        for(int i = 0; !dune ;i++){ //TODO: 23/05/2021 fix this mess later
            System.out.println("\nadd a Player: ");
            addPlayer(scan.nextLine());
            if(i >=2){
                System.out.println("\nDo you wanna add a Player? ");
                dune = scan.hasNext();
            }
            if(i >= 10){
                dune = true;
            }
        }
        System.out.println("\nPlayers in game are: \n" + listPlayers());
        System.out.println("\ncom.project.chicagoscoretracker.Game Started!");
    }

    /**
     * Adds a player to the game if one is running
     * <br>
     * if a user by the inputted name douse not exit it creates a new user in the database and adds that to the game
     * <br>
     * if a game is not running it just adds the user to the database if the user dose not already exist
     *  @param name a player name.
     */
    public void addPlayer(String name) {
        Player temp;
        if(name == null){
            System.out.println("\nNull is not an option!");
        }
        if(connection.getPlayerByName(name) == null){
            temp = new Player(name); //Creates a new Player
            connection.savePlayer(temp); //adds the new player to the database

        }else{
            temp = connection.getPlayerByName(name);
        }
        if(gameStarted){ //if a game is started add the player to the list of players in the game
            players.add(temp);
            System.out.println("\nNew player: " + temp + " was added to the game ");
        } else{
            System.out.println("\nGame was not started, so player was not added to it");
        }
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
     * Checks if player has reached the win condition
     * @param player player to check
     * @return if player has won
     */
    public boolean checkScore(Player player) {
        if(playerExists(player)) {
            return player.getScore() >= settings.getWinScore();
        }
        System.out.println("\nPlayer did not exist");
        return false;
    }

    /**
     * checks if player has reached the condition for chicago
     * @param player player to check
     * @return if player can chicago
     */
    public boolean checkChicago(Player player) {
        if(playerExists(player)){
            return player.getScore() >= settings.getChicago();
        }
        System.out.println("player did not exist");
       return false;
    }

    /**
     * ends the game and gives the player with most score the win.
     * <br>
     * clears the player list and sets all the players scorers to 0
     */
    public void endGame() {
        int largest = 0;
        Player winner = players.get(0);

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
     * checks if player exists
     * @param player player to check
     * @return if the player exists
     */
    public boolean playerExists(Player player){
        if(player == null){
            return false;
        }
        return connection.getPlayers().contains(player);
    }

    /**
     * adds points to player
     * @param player player that gets the points
     * @param points the points
     */
    public void addPoints(Player player, int points){
        player.setScore(player.getScore() + points);
    }

    public void win() {
    }

    /**
     * gets a Player from the database by name!
     * @param name name of player
     * @return Player
     */
    public Player getPlayer(String name){
        Player player = new Player();
        try{
            player = connection.getPlayerByName(name);
        } catch (Exception e){
            e.printStackTrace();
        }
        return player;
    }
}
