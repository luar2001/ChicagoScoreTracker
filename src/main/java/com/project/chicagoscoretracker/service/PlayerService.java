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

    /**
     * Finds player by Id and returns it
     * @param id id of the player
     * @return the player with that id
     */
    public Player getPlayerById(Integer id){return repository.findPlayerById(id);}

    /**
     * gets all players with a set amount of wins
     * @param wins the amount of wins the player in the list should have
     * @return a list of players with that many wins
     */
    public List<Player> getPlayerByWins(int wins){return repository.findPlayerByWins(wins);}

    /**
     * removes a player form the database
     * @param id removes the player with this id
     */
    public void deletePlayer(Integer id){repository.deleteById(id);}


}
