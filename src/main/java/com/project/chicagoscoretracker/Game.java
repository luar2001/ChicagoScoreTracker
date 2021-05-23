package com.project.chicagoscoretracker;

import com.project.chicagoscoretracker.controller.PlayerController;
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

    //TODO: can't actually use things form these without getting errors, will fix later, probably change to a get call or something
    private static PlayerController controller;

    /**
     * the game settings
     */
    private static Settings settings;

    /**
     * a boolean that represent if a game of Chicago is started or not
     */
    private static Boolean gameStarted = false;

    /**
     * List of Players in the current game of Chicago
     */
    private static List<Player> players = new ArrayList<>();

    /**
     * Scanner to let the user type in the names of players.
     */
    private static final Scanner scan = new Scanner(System.in);

    /**
     * lists all players in the database
     * @return list of players
     */
    public static List<Player> listAllPlayers() {
        return controller.getPlayers();
    }

    /**
     * Starts the game and asks for 2 players, then the user have a choice to add more.
     */
    public static void startGame() {
        gameStarted =true;
        boolean dune = false;

        System.out.println("\n CHICAGO \n Add 2 players to start");
        for(int i = 0; dune ;i++){ //TODO: fix this mess later
            System.out.println("\nadd a Player: ");
            addPlayer(scan.nextLine());
            if(i >=2){
                System.out.println("\nDo you wanna add a Player? ");
                dune = scan.nextBoolean();
            } else if(i == 10){
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
    public static void addPlayer(String name) {
        Player temp;
        if(name == null){
            System.out.println("\nNull is not an option!");
        }
        if(controller.getPlayerByName(name) == null){
            temp = new Player(name); //Creates a new Player
            controller.savePlayer(temp); //adds the new player to the database
        }else{
            temp = controller.getPlayerByName(name);
        }
        if(gameStarted){ //if a game is started add the player to the list of players in the game
            players.add(temp);
        }
        System.out.println("\nPlayer:"+temp.getName()+ " was added");
    }

    /**
     * Lists the players in the current game of chicago
     * <br>
     * empty if a game is not started
     * @return list of players
     */
    public static List<Player> listPlayers() {
        return players;
    }

    /**
     * Checks if player has reached the win condition
     * @param player player to check
     * @return if player has won
     */
    public static boolean checkScore(Player player) {
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
    public static boolean checkChicago(Player player) {
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
    public static void endGame() {
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
    private static boolean playerExists(Player player){
        if(player == null){
            return false;
        }
        return controller.getPlayers().contains(player);
    }

    /**
     * adds points to player
     * @param player player that gets the points
     * @param points the points
     */
    public static void addPoints(Player player, int points){
        player.setScore(player.getScore() + points);
    }
}
