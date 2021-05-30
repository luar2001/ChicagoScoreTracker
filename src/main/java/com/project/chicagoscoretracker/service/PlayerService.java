package com.project.chicagoscoretracker.service;

import com.project.chicagoscoretracker.model.Player;
import com.project.chicagoscoretracker.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SequenceGenerator seq;

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
    public void savePlayer(Player player){
        player.setId(seq.getSequenceNumber("player_sequence"));
        repository.save(player);}

    /**
     * Finds a player by name and returns that player
     * @param name the name of the player
     * @return the player by that name
     */
    public Player getPlayerByName(String name){return repository.findPlayerByName(name);}

    /**
     * removes a player form the database
     * @param id removes the player with this id
     */
    public void deletePlayer(Long id){
        repository.deleteById(id);
    }



}
