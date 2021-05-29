package com.project.chicagoscoretracker.service;

import com.project.chicagoscoretracker.model.Player;
import com.project.chicagoscoretracker.repository.PlayerRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lukas Aronsson
 * Date: 18/05/2021
 * Time: 16:31
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
@Service
@RequiredArgsConstructor
public class PlayerService {

    /**
     * PlayerRepository
     */
    private final PlayerRepository repository;

    /**
     * gets all registered players from the database
     * @return a list of all the players in the database
     */
    public List<Player> getPlayers(){return repository.findAll();}

    /**
     * Saves a player to the database
     * @param player player that will be saved to the database
     */
    public void savePlayer(Player player){repository.save(player);}

    /**
     * Finds a player by name and returns that player
     * @param name the name of the player
     * @return the player by that name
     */
    public Player getPlayerByName(String name){return repository.findPlayerByName(name);}

}
