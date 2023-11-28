package com.brmo.clients.repository;

import com.brmo.clients.domain.Sensor;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor,String> {

    Sensor findTopByWmo(String codWMO);
    List<Sensor> findAllByOrderByWmoDesc();

    @Aggregation(pipeline = {
            "{ $sort: { wmo: -1 } }",
            "{ $group: { _id: '$region', sensors: { $push: '$$ROOT' } } }",
            "{ $project: { sensors: { $slice: ['$sensors', 2] } } }",
            "{ $unwind: '$sensors' }",
            "{ $replaceRoot: { newRoot: '$sensors' } }"
    })
    List<Sensor> findTop10ByOrderByWmoDesc();

}
