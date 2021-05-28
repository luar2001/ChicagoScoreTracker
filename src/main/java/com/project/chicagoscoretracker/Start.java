package com.project.chicagoscoretracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        Commands commands = new Commands();
        commands.start();

    }
}
