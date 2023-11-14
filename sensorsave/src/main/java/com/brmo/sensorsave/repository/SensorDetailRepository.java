package com.brmo.sensorsave.repository;

import com.brmo.sensorsave.domain.SensorDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorDetailRepository extends MongoRepository<SensorDetail, String> {
}
