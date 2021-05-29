package com.project.chicagoscoretracker.service;

import com.project.chicagoscoretracker.model.Player_sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

/**
 * Created by Lukas Aronsson
 * Date: 29/05/2021
 * Time: 22:48
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
@Service
public class SequenceGenerator {

    @Autowired
    private MongoOperations mongoOperations;

    public long getSequenceNumber(String sequenceName){
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq",1);

        Player_sequence counter = mongoOperations.findAndModify(query,update, options().returnNew(true).upsert(true),Player_sequence.class);

        return !isNull(counter) ? counter.getSeq() : 1;

    }
}
