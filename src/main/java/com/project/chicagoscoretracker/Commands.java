package com.project.chicagoscoretracker;
import com.project.chicagoscoretracker.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukas Aronsson
 * Date: 23/05/2021
 * Time: 16:56
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
public class Commands {

    Game game = new Game();
    Settings settings = new Settings();

    static private final List<String> commands = new ArrayList<>();

    private void help(){
        System.out.println("\nCommands:");
        for(String cmd : commands){
            System.out.println(cmd);
        }
    }

    private void pair(Player player){
        game.addPoints(player,Hand.points(Hand.Poker.PAIR));
    }

    private void twoPair(Player player){
        game.addPoints(player,Hand.points(Hand.Poker.TWO_PAIRS));
    }

    private void threeOFAKind(Player player){
        game.addPoints(player,Hand.points(Hand.Poker.THREE_OF_A_KIND));
    }

    private void straight(Player player){
        game.addPoints(player,Hand.points(Hand.Poker.STRAIGHT));
    }

    private void flush(Player player){
        game.addPoints(player,Hand.points(Hand.Poker.FLUSH));
    }

    private void fullHouse(Player player){
        game.addPoints(player,Hand.points(Hand.Poker.FULL_HOUSE));
    }

    private void fourOfAKind(Player player){
        game.addPoints(player,Hand.points(Hand.Poker.FOUR_OF_A_KIND));
    }

    private void straightFlush(Player player){
        game.addPoints(player,Hand.points(Hand.Poker.STRAIGHT_FLUSH));
    }

    private void royalFlush(Player player){
        game.addPoints(player,Hand.points(Hand.Poker.ROYAL_FLUSH));
    }

    private void win(Player player){game.addPoints(player,settings.getWinScore());}


    private void commandHandler(String input){
        boolean twoCommands = false;
        String command;
        Player player;
        String error1 = "\nERROR:1: You have to put in a player name after the command! ";
        String error2 = "\nERROR:2: You can't have a name with that command! ";
        String playerName="";
        if(input.contains(":")){
            twoCommands = true;
            String[] split = input.toLowerCase().split(":"); //split on the first space (" ")
            command = split[0];
            playerName = split[1];
            player = game.getPlayer(playerName);
        } else {
            command = input.toLowerCase();
            player = null;
        }

        if (commands.contains(command)) {
            switch (command) {
                case "pair" -> {
                    if (twoCommands) {
                        pair(player);
                    }else{
                        System.out.println(error1);
                    }
                }
                case "2pair" -> {
                    if (twoCommands) {
                        twoPair(player);
                    }else{
                        System.out.println(error1);
                    }
                }
                case "3ofakind" -> {
                    if (twoCommands) {
                        threeOFAKind(player);
                    }else{
                        System.out.println(error1);
                    }
                }
                case "straight" -> {
                    if (twoCommands) {
                        straight(player);
                    }else{
                        System.out.println(error1);
                    }
                }
                case "flush" -> {
                    if (twoCommands) {
                        flush(player);
                    }else{
                        System.out.println(error1);
                    }
                }
                case "fullhouse" -> {
                    if (twoCommands) {
                        fullHouse(player);
                    }else{
                        System.out.println(error1);
                    }
                }
                case "4ofakind" -> {
                    if (twoCommands) {
                        fourOfAKind(player);
                    }else{
                        System.out.println(error1);
                    }
                }
                case "straightflush" -> {
                    if (twoCommands) {
                        straightFlush(player);
                    }else{
                        System.out.println(error1);
                    }
                }
                case "royalFlush" -> {
                    if (twoCommands) {
                        royalFlush(player);
                    }else{
                        System.out.println(error1);
                    }
                }
                case "addplayer" -> {
                    if (twoCommands) {
                        game.addPlayer(playerName);
                    }else{
                        System.out.println(error1);
                    }
                }
                case "newplayer" ->{
                    if(twoCommands){
                        game.newPlayer(playerName);
                    }
                }
                case "help" -> {
                    if (twoCommands) {
                        System.out.println(error2);
                    }else{
                        help();
                    }
                }
                case "startgame" -> {
                    if (twoCommands) {
                        System.out.println(error2);
                    }else{
                        game.startGame();
                    }
                }
                case "listallplayers" -> {
                    if (twoCommands) {
                        System.out.println(error2);
                    }else{
                        System.out.println("\nPlayers: "+game.listAllPlayers());
                    }
                }
                case "listplayers" -> {
                    if (twoCommands) {
                        System.out.println(error2);
                    }else{
                        System.out.println("\nPlayers in game: "+game.listPlayers());
                    }
                }
                case "endgame" -> {
                    if (twoCommands) {
                        System.out.println(error2);
                    }else{
                        game.endGame();
                    }

                }
                case "win" -> {
                    if (twoCommands) {
                        win(player);
                    }else{
                        System.out.println(error1);
                    }

                }
            }
        } else{
            System.out.println("\n"+ command +" is not a command! ");
        }
    }

    private void addCommands(){
        //TODO: 23/05/2021 do with enum or something this is just bad
        commands.add("help"); // list these commands
        commands.add("startgame");// starts the game, asks the user to add 2 players
        commands.add("endgame"); // player with most points win, if a draw give no one points

        commands.add("listallplayers"); // list all players in current game
        commands.add("listplayers"); // list all players in the database

        commands.add("addplayer"); // adds player to the game
        commands.add("newplayer"); // creates a new player and adds it to the database

        commands.add("pair"); // 1 point
        commands.add("2pair"); // 2 points
        commands.add("3ofakind"); // 3 points
        commands.add("straight"); // 4 points
        commands.add("flush"); // 5 points
        commands.add("fullhouse"); // 6 points
        commands.add("4ofakind"); // 7 points
        commands.add("straightflush"); // 8 points
        commands.add("royalFlush"); // 52 points
        commands.add("win"); //a round win not a game win
    }

}

