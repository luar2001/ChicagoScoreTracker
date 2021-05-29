package com.project.chicagoscoretracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Lukas Aronsson
 * Date: 29/05/2021
 * Time: 22:38
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
@Document(collection = "player_sequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player_sequence {
    @Id
    private String  id;
    private long seq;
}
