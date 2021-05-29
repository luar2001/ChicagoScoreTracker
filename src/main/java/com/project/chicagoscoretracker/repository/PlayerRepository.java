package com.project.chicagoscoretracker.repository;
import com.project.chicagoscoretracker.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Lukas Aronsson
 * Date: 18/05/2021
 * Time: 16:22
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
@Repository
public interface PlayerRepository extends MongoRepository<Player, Integer>{
    Player findPlayerByName(String name);
}
