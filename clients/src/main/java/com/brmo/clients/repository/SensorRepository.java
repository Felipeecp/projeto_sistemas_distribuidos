package com.brmo.clients.repository;

import com.brmo.clients.domain.Sensor;
import com.brmo.clients.dto.MapInfo;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor,String> {

    Sensor findTopByWmo(String codWMO);
    List<Sensor> findAllByOrderByWmoDesc();


    @Aggregation(pipeline = {
            "{ $group: {_id: '$uf', region: { $first: '$region'}, totalSensor: {$sum: 1}, cdg: {$addToSet: '$wmo'}}}",
            "{ $lookup: {" +
                "from: 'sensorDetail'," +
                 "localField: 'cdg'," +
                 "foreignField: 'codWMO'," +
                 "pipeline : [{" +
                    "$group: {" +
                        "_id: '0', 'avgTemp': {$avg: '$data.temperatura'}, 'avgUmd': {$avg: '$data.umidade'}" +
                    "}" +
                 "}], " +
                 "as: 'detail'" +
            "}}",
            "{ $project: {detail: '$detail', totalSensor: '$totalSensor', region: $region}}"
    })
    List<MapInfo> findTest();
}
