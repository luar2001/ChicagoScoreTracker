package com.project.chicagoscoretracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * Created by Lukas Aronsson
 * Date: 26/05/2021
 * Time: 16:47
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
@SpringBootApplication
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args); // runs spring!
        Scanner scan = new Scanner(System.in);
        Commands command = new Commands();
        System.out.println("\nCHICAGO");
        command.addCommands();
        while (true){ //program just runs forever until you shout it down.
            System.out.println("\nInput command: ");
            command.commandHandler(scan.nextLine());
        }

    }
}
