package com.project.chicagoscoretracker.controller;

import com.project.chicagoscoretracker.model.Player;
import com.project.chicagoscoretracker.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Lukas Aronsson
 * Date: 23/05/2021
 * Time: 13:20
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
@RequiredArgsConstructor
@RestController
public class PlayerController {

    private final PlayerService service;

    @GetMapping("/player")
    public List<Player> getPlayers(){return service.getPlayers();}

    @PostMapping("/player")
    public void savePlayer(@RequestBody Player player){service.savePlayer(player);}

    @GetMapping("/player/name")
    public Player getPlayerByName(String name){return service.getPlayerByName(name);}

    @PostMapping("/player/delete")
    public void deletePlayer(@RequestBody Long id){service.deletePlayer(id);}

}
