package com.brmo.clients.repository;

import com.brmo.clients.domain.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor,String> {

    Sensor findTopByWmo(String codWMO);
    List<Sensor> findAllByOrderByWmoDesc();

}
