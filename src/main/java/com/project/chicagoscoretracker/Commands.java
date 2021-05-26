package com.project.chicagoscoretracker;

import com.project.chicagoscoretracker.controller.PlayerController;
import com.project.chicagoscoretracker.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.project.chicagoscoretracker.Game.*;

/**
 * Created by Lukas Aronsson
 * Date: 23/05/2021
 * Time: 16:56
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
public class Commands {
    private static final Scanner scan = new Scanner(System.in);

    private static final List<String> commands = new ArrayList<>();

    static PlayerController controller;

    private static boolean game = true;

    public static void main(String[] args) {
        System.out.println("CHICAGO!");
        addCommands();
        do {
            System.out.println("\nInput command! (help for a list of commands)");
            commandHandler(scan.nextLine());

            for (Player player : listPlayers()) {
                if(checkScore(player)){
                    game = false;
                }
                //TODO: 23/05/2021 add chicago checker here!
            }
        } while (game);
    }
    private static void help(){
        System.out.println("\nCommands:");
        for(String cmd : commands){
            System.out.println(cmd +" : TEMP description ");

        }
    }

    private static void pair(Player player){
        addPoints(player,Hand.points(Hand.Poker.PAIR));
    }

    private static void twoPair(Player player){
        addPoints(player,Hand.points(Hand.Poker.TWO_PAIRS));
    }

    private static void threeOFAKind(Player player){
        addPoints(player,Hand.points(Hand.Poker.THREE_OF_A_KIND));
    }

    private static void straight(Player player){
        addPoints(player,Hand.points(Hand.Poker.STRAIGHT));
    }

    private static void flush(Player player){
        addPoints(player,Hand.points(Hand.Poker.FLUSH));
    }

    private static void fullHouse(Player player){
        addPoints(player,Hand.points(Hand.Poker.FULL_HOUSE));
    }

    private static void fourOfAKind(Player player){
        addPoints(player,Hand.points(Hand.Poker.FOUR_OF_A_KIND));
    }

    private static void straightFlush(Player player){
        addPoints(player,Hand.points(Hand.Poker.STRAIGHT_FLUSH));
    }

    private static void royalFlush(Player player){
        addPoints(player,Hand.points(Hand.Poker.ROYAL_FLUSH));
    }


    private static void commandHandler(String input){
        boolean twoCommands = false;
        String command;
        Player player;
        String error1 = "\nyou have to put in a player name after the command! ";
        String error2 = "\nyou can't have a name with that command! ";
        String playerName="";
        if(input.contains(":")){
            twoCommands = true;
            String[] split = input.toLowerCase().split(":"); //split on the first space (" ")
            command = split[0];
            playerName = split[1];
            player = controller.getPlayerByName(playerName);
        } else {
            command = input.toLowerCase();
            player = null;
        }

        if (commands.contains(command)) {
            switch (command) {
                case "pair" -> {
                    if (twoCommands) {
                        pair(player);
                    }
                    System.out.println(error1);
                }
                case "2pair" -> {
                    if (twoCommands) {
                        twoPair(player);
                    }
                    System.out.println(error1);
                }
                case "3ofakind" -> {
                    if (twoCommands) {
                        threeOFAKind(player);
                    }
                    System.out.println(error1);
                }
                case "straight" -> {
                    if (twoCommands) {
                        straight(player);
                    }
                    System.out.println(error1);
                }
                case "flush" -> {
                    if (twoCommands) {
                        flush(player);
                    }
                    System.out.println(error1);
                }
                case "fullhouse" -> {
                    if (twoCommands) {
                        fullHouse(player);
                    }
                    System.out.println(error1);
                }
                case "4ofakind" -> {
                    if (twoCommands) {
                        fourOfAKind(player);
                    }
                    System.out.println(error1);
                }
                case "straightflush" -> {
                    if (twoCommands) {
                        straightFlush(player);
                    }
                    System.out.println(error1);
                }
                case "royalFlush" -> {
                    if (twoCommands) {
                        royalFlush(player);
                    }
                    System.out.println(error1);
                }
                case "addplayer" -> {
                    if (twoCommands) {
                        addPlayer(playerName);
                    }
                    System.out.println(error1);
                }
                case "help" -> {
                    if (twoCommands) {
                        System.out.println(error2);
                    }
                    help();
                }
                case "startgame" -> {
                    if (twoCommands) {
                        System.out.println(error2);
                    }
                    startGame();
                }
                case "listallplayers" -> {
                    if (twoCommands) {
                        System.out.println(error2);
                    }
                    System.out.println(listAllPlayers());
                }
                case "listplayers" -> {
                    if (twoCommands) {
                        System.out.println(error2);
                    }
                    System.out.println(listPlayers());
                }
                case "endgame" -> {
                    if (twoCommands) {
                        System.out.println(error2);
                    }
                    endGame();
                    game = false;
                }
                case "win" -> {
                    if (twoCommands) {
                        System.out.println(error1);
                    }
                    win();
                }
                //TODO: 23/05/2021 add chicago later, if i have time!
            }

        } else{
            System.out.println("\n"+ command +" is not a command! ");
        }
    }

    private static void addCommands(){
        //TODO: 23/05/2021 do with enum or something this is just bad
        commands.add("help");
        commands.add("addplayer");
        commands.add("startgame");
        commands.add("endgame");
        commands.add("listallplayers");
        commands.add("listplayers");
        commands.add("pair");
        commands.add("2pair");
        commands.add("3ofakind");
        commands.add("straight");
        commands.add("flush");
        commands.add("fullhouse");
        commands.add("4ofakind");
        commands.add("straightflush");
        commands.add("royalFlush");
        commands.add("win");
    }

}

